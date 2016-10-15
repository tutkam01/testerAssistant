package tutka.mateusz.tester.utils.testerAssistant.methods;

import tutka.mateusz.console_application.Application;
import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.RegexEntity;

public class HandleAddingRegex implements Method {
	private Application application;
	
	public HandleAddingRegex(Application application){
		this.application = application;
	}
	
	@Override
	public String execute(String... args) {
		String regex = args[0];
		String alias = args[1];
		
		application.getApplicationCommandBuilder().withKeyWord(alias)
												  .withMethod(new HandleGeneratingRegex(regex, alias))
												  .build();
		
		DAO.saveNewEntity(new RegexEntity(alias, regex));
		
		return " new command saved..";
	}

}
