package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.util.Collections;
import java.util.List;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;

public class RemoveAlias implements Method {

	@Override
	public String execute(String... args) {
		List<MaskEntity> masks = DAO.findMaskByAlias(args[0].trim());
		for(MaskEntity entity: masks){
			DAO.removeMaskEntity(entity);
		}
		return message(masks);
	}
	
	private String message(List<MaskEntity> masks){
		if(masks.isEmpty()){
			return "Provided alias not found..";
		}
		StringBuilder sb = new StringBuilder();
		for(MaskEntity mask: masks){
			sb.append(mask)
			  .append(" removed successfully \n");
		}
		
		return sb.toString();
	}

}
