package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        // 데이터베이스 트랜잭션 시작
        tx.begin();

//        try {
//            // Member findMember = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//
//            for(Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();


        try {
            // 비영속 상태
//           Member member = new Member();
//           member.setId(101L);
//           member.setName("HelloJPA");

           // 영속 상태
           // 엔티티 매니저 안에 있는 영속성 컨텍스트라는 데를 통해서 멤버가 관리가 된다
//            System.out.println("====  BEFORE  ====");
//            em.persist(member); // 영속성 컨텍스트에 저장하기
//            // em.detach(member); 영속성 컨텍스트에서 지우기
//            System.out.println("====  AFTER  ====");

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = " + (findMember1 == findMember2));



            // 엔티티 수정
            //member.setName("mirae Kim");
//
//            Member findMember1 = em.find(Member.class, 20L);
//            findMember1.setAge(26);
//
//
//            System.out.println("=================");
                Team team = new Team();
                team.setName("TeamA");
                em.persist(team);

                Member member = new Member();
                member.setUserName("member1");
                member.setTeam(team);
                em.persist(member);

                em.flush();
                em.clear();

            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUserName() = " + m.getUserName());
            }

            // 보류중인 모든 데이터 변경사항을 영구적으로 적용. 현재 트랜잭션 종료 데이터베이스에 저장
            tx.commit();
        } catch (Exception e) {
            // 보류중인 모든 데이터 변경사항을 폐기. 현재 트랜잭션 종료, 직전 커밋 직후의 단계로 회귀(되돌아가기)
            // 전체 트랜잭션을 롤백함
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
        }

