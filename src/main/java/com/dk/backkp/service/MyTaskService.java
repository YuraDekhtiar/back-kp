package com.dk.backkp.service;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.model.MyTask;
import com.dk.backkp.repository.TaskRepository;
import com.dk.backkp.repository.UserRepository;
import com.dk.backkp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyTaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public MyTaskEntity addNewTask(MyTaskEntity myTask, UserPrincipal userPrincipal) {

        Optional<UserEntity> userEntity = userRepository.findById(userPrincipal.getId());
        myTask.setAuthor(userEntity.get());

        myTask.getAnswers().stream().forEach(answerEntity -> answerEntity.setTask(myTask));

        return taskRepository.save(myTask);
    }

    public MyTask getTaskById(Long id)  {
        MyTaskEntity myTask = taskRepository.findById(id).get();
        return MyTask.toModel(myTask);
    }
}
