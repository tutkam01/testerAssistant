package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import tutka.mateusz.console_application.Application;
import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;

public class HandleSettingMask implements Method {
	private Application application;
	
	public HandleSettingMask(Application application){
		this.application = application;
	}
	
	public String execute(String... args){
		String constantPart = args[0];
		String alias = args[1];
		boolean isTimestamped = Boolean.parseBoolean(args[2]);
		boolean isSequential = Boolean.parseBoolean(args[3]);
		String dateFormat = StringUtils.isNotBlank(args[4])?args[4]:null;
		
		application.getApplicationCommandBuilder().withKeyWord(alias)
		  										  .withMethod(new HandleGeneratingSequentialTimestampedString(constantPart, alias, isTimestamped, isSequential, verifyProvidedDateFormat(dateFormat)))
		  										  .build();
		
	    DAO.saveNewEntity(new MaskEntity(constantPart, alias, isTimestamped, isSequential, dateFormat));
		return "new command saved..";
	}
	
    private String verifyProvidedDateFormat(String format){
    	try{
    		new SimpleDateFormat(format);
    		return format;
    	}catch(Exception e){
    		return "ddMMyy";
    	}
	}
	
	
}
