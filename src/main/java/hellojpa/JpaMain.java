package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // DB 당 하나씩만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml 의 unit name

        // EntityManager 은 트랜잭션의 단위마다 생성해야 한다고 생각
        // 쓰레드 간에 절대 공유되면 안됨
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member memberA = new Member();
            memberA.setUsername("A");

            Member memberB = new Member();
            memberB.setUsername("B");

            Member memberC = new Member();
            memberC.setUsername("C");

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            System.out.println("memberA.id = " + memberA.getId());
            System.out.println("memberB.id = " + memberB.getId());
            System.out.println("memberC.id = " + memberC.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
