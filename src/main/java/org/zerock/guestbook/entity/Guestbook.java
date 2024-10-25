package org.zerock.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

// 이 클래스가 entity를 위한 클래스이며, 해당 클래스의 인스턴스들은 JPA가 관리해야 하는 엔티티 객체임을 명시
@Entity
// entity는 불변객체로 생성한다.
@Getter
@Builder
@AllArgsConstructor
// 기본 생성자는 라이브러리 충돌 문제를 피하기 위해 정의해두는 편이 좋다.
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{

    // Primary Key(PK)에 해당하는 특정 필드에 대해서 반드시 @Id를 지정해줘야 한다.
    @Id
    /*
    JPA에게 키 생성 전략을 명시하는 것.
    새로운 레코드가 기록될 때마다 다른 번호를 갖는 PK를 자동으로 생성하고자 할 때 사용.
    default인 AUTO는 JPA 구현체가 생성 방식을 결정함. Spring Boot에서는 Hibernate가 결정.
    주로 사용하는 IDENTITY는 사용하는 DB가 키 생성을 결정함.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    /*
    PK 외에 추가적인 column을 명시하기 위해 사용.
    주로 nullable, name, length 속성을 사용한다.
    columnDefinition 속성을 이용하면 SQL 형식으로 column의 속성을 지정할 수 있다.
     */
    @Column(length=100, nullable=false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length=50, nullable = false)
    private String writer;
}
