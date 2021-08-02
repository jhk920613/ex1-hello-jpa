package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")   // 테이블 이름을 별도로 지정 안하면 클래스 명이 테이블 명이라고 인식
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
