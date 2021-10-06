package com.dk.backkp.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "tasks_table")
public class MyTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private String category;
    private String tags;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<AnswerEntity> answers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<ImgUrlEntity> imgUrls;
}
