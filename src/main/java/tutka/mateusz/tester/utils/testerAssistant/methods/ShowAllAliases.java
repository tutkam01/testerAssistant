package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.AliasEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.RegexEntity;

public class ShowAllAliases implements Method {

	@Override
	public String execute(String... args) {
		List<AliasEntity> aliases = DAO.retieveAllAliases();
		if(aliases.isEmpty()){
			return " No aliases found :-[";
		}
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		try{
		formatter.format(" %1$-10s %2$-15s %3$-30s %4$-10s %5$-10s %6$-15s\n", "ALIAS", "CONSTANT_PART", "REGEX", "TIMESTAMP", "SEQUENCE", "DATE_FORMAT");
			for(AliasEntity alias: aliases){
				if(alias instanceof MaskEntity){
					formatter.format(" %1$-10s %2$-15s %3$-30s %4$-10b %5$-10b %6$-15s\n", alias.getAlias(), truncateTo(15, ((MaskEntity) alias).getConstantPart()), "N/A", ((MaskEntity) alias).isTimestamped(), ((MaskEntity) alias).isSequential(), ((MaskEntity) alias).getDateFormat());
				}else{
					formatter.format(" %1$-10s %2$-15s %3$-30s %4$-10s %5$-10s %6$-15s\n", alias.getAlias(), "N/A", truncateTo(30,((RegexEntity) alias).getRegex()), "N/A", "N/A", "N/A");
				}
			}
		
			return formatter.toString();
		}finally{
			formatter.close();
		}
	}
	
	private String truncateTo(int charactersLength, String tooLongString){
		return (tooLongString.length()>charactersLength)?tooLongString.substring(0, charactersLength):tooLongString;
	}

}
