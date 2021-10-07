package com.dk.backkp.model;

import com.dk.backkp.entity.AnswerEntity;
import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MyTask {
    private Long id;
    private String title;
    private String body;
    private String category;
    private String tags;
  //  private UserEntity author;
    private List<Answer> answers;

    public static MyTask toModel(MyTaskEntity myTask) {
        MyTask model = new MyTask();
        model.setId(myTask.getId());
        model.setTitle(myTask.getTitle());
        model.setBody(myTask.getBody());
        model.setCategory(myTask.getCategory());
        model.setTags(myTask.getTags());
        model.setAnswers(myTask.getAnswers().stream().map(Answer::toModel).collect(Collectors.toList()));

        return model;
    }
}
