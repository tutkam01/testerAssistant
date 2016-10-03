package tutka.mateusz.tester.utils.testerAssistant;

import java.io.IOException;

import tutka.mateusz.console_application.Application;
import tutka.mateusz.tester.utils.testerAssistant.daolike.DAO;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleGeneratingSequentialTimestampedString;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleSettingMask;
import tutka.mateusz.tester.utils.testerAssistant.methods.IncreaseSequence;
import tutka.mateusz.tester.utils.testerAssistant.methods.RemoveAlias;
import tutka.mateusz.tester.utils.testerAssistant.methods.SetSequence;

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
    	application.getApplicationCommandBuilder().withKeyWord("set sequence")
		  										  .withMethod(new SetSequence())
		  										  .build();
		
    	for(MaskEntity entity: DAO.retieveAllMasks()){
    		application.getApplicationCommandBuilder().withKeyWord(entity.getAlias())
			  										  .withMethod(new HandleGeneratingSequentialTimestampedString(entity.getConstantPart(), entity.getAlias(), entity.isTimestamped(), entity.isSequential()))
			  										  .build();
    	}

    	application.run();
        
    }
    
}
