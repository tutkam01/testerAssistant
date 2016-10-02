package tutka.mateusz.tester.utils.testerAssistant.persistance;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDatabaseFactory {
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null || !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("$objectdb/db/aliases.odb");
		}
		
		return emf;
	}
	

}
