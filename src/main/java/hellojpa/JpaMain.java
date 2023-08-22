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
            Member member = new Member();
            member.setUsername("C");

            System.out.println("======================");
            entityManager.persist(member);
            System.out.println("member.getId() = " + member.getId());
            //identity 전략에서는 pk값을 알아야 하기 때문에 commit 시점이 아닌 persist 시점에 insert query가 나간다.
            System.out.println("======================");

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
