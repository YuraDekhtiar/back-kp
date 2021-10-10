package com.dk.backkp.service;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.repository.TaskRepository;
import com.dk.backkp.repository.UserRepository;
import com.dk.backkp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyTaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAnswerService userAnswerService;


    public MyTaskEntity addNewTask(MyTaskEntity myTask, UserPrincipal userPrincipal) {
        Optional<UserEntity> userEntity = userRepository.findById(userPrincipal.getId());
        myTask.setAuthor(userEntity.get());
        myTask.getAnswers().stream().forEach(answerEntity -> answerEntity.setTask(myTask));
        myTask.getImages().stream().forEach(imageEntity -> imageEntity.setTask(myTask));

        return taskRepository.save(myTask);
    }

    public MyTaskEntity getTaskById(Long id)  {
        MyTaskEntity myTask = taskRepository.findById(id).
                orElseThrow(() -> new BadRequestException(id.toString()));

        return myTask;
    }

    public boolean compareAnswer(Long taskId, String value, Long userId) {
        return userAnswerService.compare(taskId, value, userId);
    }

    public MyTaskEntity save(MyTaskEntity myTask) {
        return taskRepository.save(myTask);
    }

    public boolean userAnswered(Long id, Long userId) {
        return userAnswerService.userAnswered(id, userId);
    }


}
