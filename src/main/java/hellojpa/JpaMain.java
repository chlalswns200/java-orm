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
            Member member1 = entityManager.find(Member.class, 150L);
            member1.setName("ZZZZ");
            Member member2 = new Member(300L, "Test123");

            System.out.println("======================"); //  구분선 이후  tx.commit()에서 쿼리가 나간다
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
