package com.dk.backkp.service;

import com.dk.backkp.entity.UserAnswerEntity;
import com.dk.backkp.repository.UserAnswerRepository;
import com.dk.backkp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private MyTaskService taskService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;

    public boolean compare(Long taskId, String value, Long userId) {
        if(!userAnswerRepository.findByUser_idAndTask_id(userId, taskId).isEmpty()) return false;

        if( answerService.getAllAnswerByTaskId(taskId).stream().anyMatch(lang -> lang.getText().toLowerCase(Locale.ROOT)
                .equals(value.toLowerCase(Locale.ROOT))) ) {
            UserAnswerEntity answer = new UserAnswerEntity();

            answer.setTask(taskService.getTaskById(taskId));
            answer.setUser(userService.getUserById(userId));
            userAnswerRepository.save(answer);

            return true;
        }
        return false;
    }

    public boolean userAnswered(Long taskId, Long userId) {
        return !userAnswerRepository.findByUser_idAndTask_id(userId, taskId).isEmpty();
    }
}
