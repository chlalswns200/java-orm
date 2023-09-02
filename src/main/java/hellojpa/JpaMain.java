package hellojpa;

import java.awt.MenuBar;
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

            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member memberA = new Member();
            memberA.setUsername("userA");
            memberA.setTeam(team);
            entityManager.persist(memberA);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, memberA.getId());
            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());

            System.out.println("=========query=======");
            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
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
