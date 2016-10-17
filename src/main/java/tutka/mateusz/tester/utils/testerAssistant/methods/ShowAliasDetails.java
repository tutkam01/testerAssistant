package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.util.List;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.AliasEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.RegexEntity;

public class ShowAliasDetails implements Method {

	@Override
	public String execute(String... args) {
		List<AliasEntity> aliases = DAO.findAlias(args[0].trim());
		return message(aliases);

	}

	private String message(List<AliasEntity> aliases) {
		if (aliases.isEmpty()) {
			return " Provided alias not found..";
		}
		StringBuilder sb = new StringBuilder();
		for (AliasEntity alias : aliases) {
			sb.append(" ALIAS: "+ alias.getAlias() +"\n")
			  .append((alias instanceof MaskEntity)?" CONSTANT PART: " + ((MaskEntity)alias).getConstantPart()+ "\n":
					  								" REGEX: " + ((RegexEntity)alias).getRegex())
			  .append((alias instanceof MaskEntity)?" TIMESTAMP: " + ((MaskEntity)alias).isTimestamped()+ "\n":"")
			  .append((alias instanceof MaskEntity)?" SEQUENCE: " + ((MaskEntity)alias).isSequential() + "\n":"")
			  .append((alias instanceof MaskEntity)?" DATE FORMAT: " + ((MaskEntity)alias).getDateFormat():"");
		}

		return sb.toString();
	}
}
