package com.dk.backkp.entity;

import lombok.Data;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tasks_table")
@Indexed
public class MyTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    private String title;
    @Field
    private String body;
    @Field
    private String category;
    @Field
    private String tags;

    private LocalDateTime created;
    private byte averageRating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<AnswerEntity> answers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<ImageEntity> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<RatingEntity> ratingEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<UserAnswerEntity> userAnswerEntities;

    public MyTaskEntity() {
        LocalDateTime date = LocalDateTime.now();
        this.created = date;
    }
}
