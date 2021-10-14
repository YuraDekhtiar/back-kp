package com.dk.backkp.dto;

import com.dk.backkp.entity.AnswerEntity;
import com.dk.backkp.entity.MyTaskEntity;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MyTaskEdit extends MyTask{
    List <Answer> answers;

    public static MyTaskEdit toModel(MyTaskEntity myTask) {
        MyTaskEdit model = new MyTaskEdit();

        model.setId(myTask.getId());
        model.setTitle(myTask.getTitle());
        model.setBody(myTask.getBody());
        model.setCategory(myTask.getCategory());
        model.setTags(myTask.getTags());
        model.setAuthor(Author.toModel(myTask.getAuthor()));
        model.setCreated(myTask.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        model.setAverageRating(myTask.getAverageRating());
        model.setImages(myTask.getImages().stream().map(Image::toModel).collect(Collectors.toList()));
        model.setAnswers(myTask.getAnswers().stream().map(Answer::toModel).collect(Collectors.toList()));

        return model;
    }
}
