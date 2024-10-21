package org.zerock.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 이 클래스의 하위 entity 클래스에 대해서 table을 자동 생성할 수 있게 함.
@MappedSuperclass
/*
'AuditingEntityListener'는 entity 객체의 생성 및 변경을 감지하는 역할을 담당한다.
이를 사용하면 이 entity의 생성 시간과 수정 시간을 자동으로 관리하도록 만들 수 있다.
그리고 이 entity를 생성하거나 수정한 사용자의 정보 또한 자동으로 관리하도록 만들 수 있다.
세부적으로 아래 네 가지 동작을 지시할 수 있다.
- @CreatedDate: 엔티티가 생성될 때 시간을 자동으로 설정.
- @LastModifiedDate: 엔티티가 수정될 때마다 시간을 자동으로 업데이트.
- @CreatedBy: 엔티티 생성자 정보를 자동으로 주입(추가 설정 필요).
- @LastModifiedBy: 엔티티 수정자 정보를 자동으로 주입(추가 설정 필요).
참고: main() 메소드를 가진 엔트리 클래스에 @EnableJpaAuditing 걸어줘야 사용 가능.
 */
@EntityListeners(value={AuditingEntityListener.class})
@Getter
/*
이 class는 추상 클래스이므로, 이것으로 직접 entity 객체를 생성할 수는 없다.
대신 이를 상속한 entity 객체에게 이 클래스에 설정된 모든 것을 상속해줄 수 있다.
 */
abstract class BaseEntity {

    // JPA에서 entity의 생성 시간을 이 column에 자동 입력하도록 함
    @CreatedDate
    /*
    insertable: 이 entity 객체를 DB에 insert할 때 해당 column 값 입력 여부 설정.
    updatable: 이 entity 객체를 DB에 update할 때 해당 column 값 변경 여부 설정.
     */
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    // JPA에서 entity의 최종 수정 시간을 이 column에 자동 입력하도록 함
    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;
}
