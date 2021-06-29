package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

//            em.persist(member);

            // 조회 및 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // JPQL - 객체 지향 쿼리
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)  // 페이징
                    .setMaxResults(10)  // 페이징
                    .getResultList();

            for (Member member: result) {
                System.out.println(member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
