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

            // // JPQL - 객체 지향 쿼리
            // List<Member> result = em.createQuery("select m from Member as m", Member.class)
            //         .setFirstResult(0)  // 페이징
            //         .setMaxResults(10)  // 페이징
            //         .getResultList();
            //
            // for (Member member: result) {
            //     System.out.println(member.getName());
            // }

            // 비영속
            // Member member = new Member();
            // member.setId(101L);
            // member.setName("HelloJPA");

            // 영속
            // System.out.println(" === BEFORE ===");
            // em.persist(member);
            // System.out.println(" === AFTER ===");

            // Member findMember1 = em.find(Member.class, 101L);   // DB에서 조회 후 영속성 컨텍스트 1차 캐시에 등록
            // Member findMember2 = em.find(Member.class, 101L);   // 1차 캐시에서 조회
            //
            // System.out.println("result = " + (findMember1 == findMember2)); // 마치 컬렉션에서 뽑아쓰듯 == 비교가 가능해짐

            // Member member = em.find(Member.class, 150L);
            // member.setName("ZZZZZ");
            //
            // Member member1 = em.find(Member.class, 150L);
            // System.out.println("member1 = " + member1.getName());

            // flush Test
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("=======");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
