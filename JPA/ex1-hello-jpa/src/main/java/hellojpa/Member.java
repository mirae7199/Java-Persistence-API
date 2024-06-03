package hellojpa;

import jakarta.persistence.*;

import java.util.logging.Logger;


@Entity

//@Table(name="MEMBER")
//@Table(name = "MBR") // 엔티티와 매핑할 테이블 지정
//@Table(name = "USER") user라고 테이블을 설정 할 수 있다
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id // 식별자 값
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long Id;
    @Column(name = "USERNAME")
    private String userName;
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 단반향 N : 1
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}





