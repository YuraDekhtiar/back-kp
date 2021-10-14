package com.dk.backkp.service;

import com.dk.backkp.dto.User;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MyTaskService myTaskService;
    @Autowired
    UserAnswerService userAnswerService;

    public UserEntity getUserEntityById(Long id)  {
        return userRepository.findById(id).
                orElseThrow(() -> new BadRequestException(id.toString()));
    }

    public User getUserById(Long id)  {
        UserEntity userEntity = userRepository.findById(id).
                orElseThrow(() -> new BadRequestException(id.toString()));

        return new User(userEntity, myTaskService.getAllByUserId(id).size(),
                userAnswerService.getTaskCompletedCount(id));
    }
}
