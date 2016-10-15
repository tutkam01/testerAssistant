package tutka.mateusz.tester.utils.testerAssistant;

import java.io.IOException;

import tutka.mateusz.console_application.Application;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.AliasEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.domain.RegexEntity;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleAddingRegex;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleGeneratingRegex;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleGeneratingSequentialTimestampedString;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleSettingMask;
import tutka.mateusz.tester.utils.testerAssistant.methods.IncreaseSequence;
import tutka.mateusz.tester.utils.testerAssistant.methods.RemoveAlias;
import tutka.mateusz.tester.utils.testerAssistant.methods.SetSequence;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;

/**

 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException, IOException{
    	
    	Application application = Application.getInstance()
				 							 .withHeight(Application.DEFAULT)
				 							 .withLength(900)
				 							 .withApplicationConsoleWelcomeText("Test data generator..")
				 							 .withHelpText("To generate string 'John' with timestamp and sequential suffix call:\n"
				 									 + " set John as alias firstname with timestamp true with sequence true \n" +
				 									 " ");
    	
        
    	application.getApplicationCommandBuilder().withKeyWord("set")
		  										  .withKeyWord("as alias")
		  										  .withKeyWord("timestamp")
		  										  .withKeyWord("sequence")
		  										  .withMethod(new HandleSettingMask(application))
		  										  .build();
    	
    	application.getApplicationCommandBuilder().withKeyWord("next")
    											  .withMethod(new IncreaseSequence())
		  										  .build();
    	application.getApplicationCommandBuilder().withKeyWord("remove")
    											  .withMethod(new RemoveAlias())
    											  .build();
    	application.getApplicationCommandBuilder().withKeyWord("update sequence to")
		  										  .withMethod(new SetSequence())
		  										  .build();
    	
    	application.getApplicationCommandBuilder().withKeyWord("add regex")
    											  .withKeyWord("as alias")
    											  .withMethod(new HandleAddingRegex(application))
		  										  .build();

    	
    	for(AliasEntity entity: DAO.retieveAllAliases()){
    		if(entity instanceof MaskEntity){
    			application.getApplicationCommandBuilder()
    				.withKeyWord(entity.getAlias())
				  	.withMethod(new HandleGeneratingSequentialTimestampedString(((MaskEntity)entity).getConstantPart(), entity.getAlias(), ((MaskEntity)entity).isTimestamped(), ((MaskEntity)entity).isSequential()))
				  	.build();
    		}else{
    			application.getApplicationCommandBuilder()
    				.withKeyWord(entity.getAlias())
    				.withMethod(new HandleGeneratingRegex(((RegexEntity)entity).getRegex(), entity.getAlias()))
    				.build();
    		}
    	}
    	
    	application.withCleanupOperations(()->{
    		DAO.em.close();
    		ObjectDatabaseFactory.getEntityManagerFactory().close();
    	});
    	
    	application.run();
    	
    }
    
}
