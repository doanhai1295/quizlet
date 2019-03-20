package com.quizlet.core.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {
    
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;
    
    @Column(name="category_name")
    private String name;
    
}
