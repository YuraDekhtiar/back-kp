package com.dk.backkp.service;

import com.dk.backkp.entity.AnswerEntity;
import com.dk.backkp.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<AnswerEntity> getAllAnswerByTaskId(Long task_id) {
        return answerRepository.findAllByTask_id(task_id);
    }
}
