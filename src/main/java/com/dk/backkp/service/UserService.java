package com.dk.backkp.service;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity getUserById(Long id)  {
        UserEntity user = userRepository.findById(id).
                orElseThrow(() -> new BadRequestException(id.toString()));

        return user;
    }
}
