package tutka.mateusz.tester.utils.testerAssistant.daolike;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tutka.mateusz.tester.utils.testerAssistant.domain.AliasEntity;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;

public class DAO {
	
	public static EntityManager em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
	
	public static List<AliasEntity> retieveAllAliases(){
			TypedQuery<AliasEntity> query = em.createQuery("SELECT al FROM AliasEntity al", AliasEntity.class);
			return query.getResultList();
	}
	
	public static List<AliasEntity> findMaskByAlias(String alias){
		TypedQuery<AliasEntity> query = em.createQuery("SELECT al FROM AliasEntity al where al.alias = :alias", AliasEntity.class);
		return query.setParameter("alias", alias).getResultList();
	}
	
	public static void removeMaskEntity(AliasEntity maskEntity){
			em.getTransaction().begin();
			em.remove(maskEntity);
			em.getTransaction().commit();
	}
	
	public static void saveNewEntity(AliasEntity entity){
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
	}

}
