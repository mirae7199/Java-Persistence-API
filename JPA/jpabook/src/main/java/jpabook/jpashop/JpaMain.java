package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        // 데이터베이스 트랜잭션 시작
        tx.begin();

        Order order = em.find(Order.class, 1L);
        Long memberId = order.getMemberId();


        try {
            tx.commit();
        } catch(Exception e){
            // 보류중인 모든 데이터 변경사항을 폐기. 현재 트랜잭션 종료, 직전 커밋 직후의 단계로 회귀(되돌아가기)
            // 전체 트랜잭션을 롤백함
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}




