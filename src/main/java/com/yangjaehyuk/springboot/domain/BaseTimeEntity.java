package com.yangjaehyuk.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    @MappedSuperclass
     - JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식

    @EntityListeners(AuditiongEntityListener.class)
     - BaseTimeEntity 클래스에 Auditing 기능을 포함

    @CreatedDate
     - Entity가 생성되어 저장될 때 시간이 자동 저장

    @LastModifiedDate
     - 조회된 Entity의 값을 변경할 떄 시간이 자동 저장
*/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
