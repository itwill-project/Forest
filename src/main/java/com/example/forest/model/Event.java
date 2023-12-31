package com.example.forest.model;


import com.example.forest.dto.event.EventUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity  // JPA 엔터티 클래스 - 데이터 베이스 테이블과 매핑되는 클래스.
@Table (name="EVENT") // 엔터티 클래스 이름이 데이터베이스 테이블 이름과 다른경우, 테이블을 이름을 명시.
@SequenceGenerator(name="EVENT_SEQ_GEN", sequenceName = "EVENT_SEQ", allocationSize = 1)
public class Event extends BaseTimeEntity {

    @Id // Primary key 제약조건
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQ_GEN")
    private Long id;
    
    @Column(nullable = false) // Not Null 제약조건
    private String title;
    
    @Column
    private String content;
    
    @Column
    private String author;
    

    
    public Event update(EventUpdateDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        
        return this;
    }
    
}
