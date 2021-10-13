package com.dk.backkp.dto;

import com.dk.backkp.entity.MyTaskEntity;
import lombok.Data;

import java.sql.Array;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MyTask {
    private Long id;
    private String title;
    private String body;
    private String category;
    private String tags;
    private Author author;
    private String created;
    private Byte averageRating;
    private List<Image> images;

    public static MyTask toModel(MyTaskEntity myTask) {
        MyTask model = new MyTask();

        model.setId(myTask.getId());
        model.setTitle(myTask.getTitle());
        model.setBody(myTask.getBody());
        model.setCategory(myTask.getCategory());
        model.setTags(myTask.getTags());
        model.setAuthor(Author.toModel(myTask.getAuthor()));
        model.setCreated(myTask.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        model.setAverageRating(myTask.getAverageRating());
        model.setImages(myTask.getImages().stream().map(Image::toModel).collect(Collectors.toList()));

        return model;
    }

    public static List<MyTask> toModel(Iterable<MyTaskEntity> myTasks) {
        List <MyTask> model = new ArrayList();
        for (MyTaskEntity myTask:myTasks) {
            model.add(MyTask.toModel(myTask));
        }
        return model;
    }
}
