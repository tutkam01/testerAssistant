package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.util.List;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.AliasEntity;

public class RemoveAlias implements Method {

	@Override
	public String execute(String... args) {
		List<AliasEntity> aliases = DAO.findAlias(args[0].trim());
		for(AliasEntity entity: aliases){
			DAO.removeMaskEntity(entity);
		}
		return message(aliases);
	}
	
	private String message(List<AliasEntity> aliases){
		if(aliases.isEmpty()){
			return " Provided alias not found..";
		}
		StringBuilder sb = new StringBuilder();
		for(AliasEntity alias: aliases){
			sb.append(' ')
			  .append(alias.getAlias())
			  .append(" removed successfully \n");
		}
		
		return sb.toString();
	}

}
