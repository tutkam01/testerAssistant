package tutka.mateusz.tester.utils.testerAssistant.methods;

import javax.persistence.EntityManager;

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
		
		application.getApplicationCommandBuilder().withKeyWord(alias)
		  										  .withMethod(new HandleGeneratingSequentialTimestampedString(constantPart, alias, isTimestamped, isSequential))
		  										  .build();
		
	    DAO.saveNewEntity(new MaskEntity(constantPart, alias, isTimestamped, isSequential));
		return "new command saved..";
	}
	
	private void saveNewMask(MaskEntity mask){
		EntityManager em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(mask);
		em.getTransaction().commit();
		em.close();
		ObjectDatabaseFactory.getEntityManagerFactory().close();
	}

}
