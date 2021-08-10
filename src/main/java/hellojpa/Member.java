package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
// @Table(name = "MEMBER")   // 테이블 이름을 별도로 지정 안하면 클래스 명이 테이블 명이라고 인식
// @SequenceGenerator(
//         name = "MEMBER_SEQ_GENERATOR",
//         sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//         initialValue = 1, allocationSize = 50)
public class Member {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO - DB 방언에 맞춰 자동생성
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    // @Column(name = "TEAM_ID")
    // private Long teamId;
    @ManyToOne // Member 입장에서 생각
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    // 양방향일 경우 아래처럼 작성
    // @ManyToOne
    // @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)// 읽기만 하도록 지정
    // private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // public Team getTeam() {
    //     return team;
    // }
    //
    // // Tip) 로직이 들어가는 경우 단순 세터의 setTeam 이 아닌 changeTeam 으로 사용해 중요한 역할을 하는구나를 인지할 수 있도록 하자.
    // public void changeTeam(Team team) {
    //     this.team = team;
    //     // 연관관계 편의 메소드 사용
    //     this.team.getMembers().add(this);
    // }
}
