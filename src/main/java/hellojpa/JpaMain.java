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

            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            entityManager.persist(member);

            team.addMember(member);

            entityManager.flush();
            entityManager.clear();

            Team findTeam = entityManager.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member findMember : members) {
                System.out.println("findMember.getUsername() = " + findMember.getUsername());
            }

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
