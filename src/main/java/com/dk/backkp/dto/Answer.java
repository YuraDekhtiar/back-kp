package com.dk.backkp.dto;

import com.dk.backkp.entity.AnswerEntity;
import lombok.Data;

@Data
public class Answer {
    private String text;

    public static Answer toModel(AnswerEntity answerEntity) {
        Answer model = new Answer();
        model.setText(answerEntity.getText());

        return model;
    }
}
