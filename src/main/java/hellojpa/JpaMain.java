package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {

            Member memberA = new Member();
            memberA.setUsername("userA");
            entityManager.persist(memberA);

            entityManager.flush();
            entityManager.clear();

            Member reference = entityManager.getReference(Member.class, memberA.getId());
            System.out.println("reference.getClass() = " + reference.getClass());
            reference.getUsername();
            System.out.println("emf = " + emf.getPersistenceUnitUtil().isLoaded(reference));

            
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }


        entityManager.close();

        emf.close();


    }

}
