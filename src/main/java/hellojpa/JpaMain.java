package hellojpa;

import java.util.List;
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

            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA2");

            //영속
            System.out.println("=== BEFORE ===");
            entityManager.persist(member);
            System.out.println("=== AFTER ===");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }


        entityManager.close();

        emf.close();


    }

}
