package tutka.mateusz.tester.utils.testerAssistant;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import tutka.mateusz.console_application.Application;
import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleGeneratingSequentialTimestampedString;
import tutka.mateusz.tester.utils.testerAssistant.methods.HandleSettingMask;
import tutka.mateusz.tester.utils.testerAssistant.methods.IncreaseSequence;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;

/**
 * Hello world!
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
		
    	for(MaskEntity entity: readAllMasksFromDatabase()){
    		application.getApplicationCommandBuilder().withKeyWord(entity.getAlias())
			  										  .withMethod(new HandleGeneratingSequentialTimestampedString(entity.getConstantPart(), entity.getAlias(), entity.isTimestamped(), entity.isSequential()))
			  										  .build();
    	}

    	application.run();
    	
        
        
    }
    
	private static List<MaskEntity> readAllMasksFromDatabase() {
		EntityManager em = null;
		try {
			em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
			TypedQuery<MaskEntity> query = em.createQuery("SELECT me FROM MaskEntity me", MaskEntity.class);
			return query.getResultList();
		} finally {
			em.close();
			ObjectDatabaseFactory.getEntityManagerFactory().close();
		}

	}
}
