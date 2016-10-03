package tutka.mateusz.tester.utils.testerAssistant.daolike;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tutka.mateusz.tester.utils.testerAssistant.domain.MaskEntity;
import tutka.mateusz.tester.utils.testerAssistant.persistance.ObjectDatabaseFactory;

public class DAO {
	
	public static List<MaskEntity> retieveAllMasks(){
		EntityManager em = null;
		try {
			em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
			TypedQuery<MaskEntity> query = em.createQuery("SELECT me FROM MaskEntity me", MaskEntity.class);
			return query.getResultList();
		}catch(Exception e){
			System.out.println(e.getMessage());
		} finally {
			em.close();
			ObjectDatabaseFactory.getEntityManagerFactory().close();
		}
		return null;
	}
	
	public static List<MaskEntity> findMaskByAlias(String alias){
		EntityManager em = null;
		em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
		TypedQuery<MaskEntity> query = em.createQuery("SELECT me FROM MaskEntity me where me.alias = :alias", MaskEntity.class);
		return query.setParameter("alias", alias).getResultList();
	}
	
	
	public static void removeMaskEntity(MaskEntity maskEntity){
		EntityManager em = null;
		try{
			em = ObjectDatabaseFactory.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
//			em.merge(maskEntity);
			em.remove(maskEntity);
			em.getTransaction().commit();
		}finally {
			em.close();
			ObjectDatabaseFactory.getEntityManagerFactory().close();
		}
	}

}
