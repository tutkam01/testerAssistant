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
import tutka.mateusz.tester.utils.testerAssistant.methods.ShowAllAliases;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;
/**
 * This app helps generating testing data which is copied to the clipboard and may be reused in tests.
 * For help, hit F1 after launching app's console.
 * @author Mateusz Tutka
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException, IOException{
    	
    	Application application = Application.getInstance()
				 							 .withHeight(Application.DEFAULT)
				 							 .withLength(900)
				 							 .withApplicationConsoleWelcomeText("Test data generator..")
				 							 .withHelpText("--To add command generating string 'John' with timestamp of format MMddyyy and sequential suffix call:\n"
				 									 + " set John as alias firstname timestamp true sequence true date format MMddyyyu \n"
				 									 + " New command will be saved as 'firstname' and each time you call 'firstname', the requested string will be co pied to the clipboard.\n"
				 									 + " For example calling firstname produces John10212016_1\n"
				 									 + " If provided date format is invalid the default 'ddMMyy' will be applied\n"
				 									 + "--To increase sequence call: next \n" 
				 									 + "--To manually update sequence to the new value, let's say 100 call: update sequence to 100\n"
				 									 + "--To add command generating random string based on a regular expression, let's say {\\d}12 call:\n"
				 									 + " add regex {\\d}12 as alias dozendigits\n"
				 									 + " Now each time you call 'dozendigits' the requested random string matching provided regex will be copied to the clipboard\n"
				 									 + "-- To remove command stored as alias just call: remove alias\n"
				 									 + " for example 'remove dozendigits'\n"
				 									 + "--To show all saved commands call: show all aliases\n\n"
				 									 + "Please remember that you may hit 'tab' button to hint the keyword");
    	
        
    	application.getApplicationCommandBuilder().withKeyWord("set")
		  										  .withKeyWord("as alias")
		  										  .withKeyWord("timestamp")
		  										  .withKeyWord("sequence")
		  										  .withKeyWord("date format")
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
    	application.getApplicationCommandBuilder().withKeyWord("show all aliases")
    											  .withMethod(new ShowAllAliases())
    											  .build();

    	
    	for(AliasEntity entity: DAO.retieveAllAliases()){
    		if(entity instanceof MaskEntity){
    			application.getApplicationCommandBuilder()
    				.withKeyWord(entity.getAlias())
				  	.withMethod(new HandleGeneratingSequentialTimestampedString(((MaskEntity)entity).getConstantPart(), entity.getAlias(), ((MaskEntity)entity).isTimestamped(), ((MaskEntity)entity).isSequential(), ((MaskEntity)entity).getDateFormat()))
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
    		System.exit(0);
    	});
    	
    	application.run();
    	
    }
    
}
