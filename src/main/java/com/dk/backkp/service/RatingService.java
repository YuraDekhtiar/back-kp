package com.dk.backkp.service;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.RatingEntity;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.repository.RatingRepository;
import com.dk.backkp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserService userService;
    @Autowired
    MyTaskService taskService;

    public boolean setRating(Long taskId, Byte value, UserPrincipal userPrincipal) {
        MyTaskEntity myTask = taskService.getTaskById(taskId);
        UserEntity user = userService.getUserEntityById(userPrincipal.getId());
        List<RatingEntity> listRating = ratingRepository.findAllByTask_id(taskId);
        Optional<RatingEntity> curUserRating = ratingRepository.findByTask_idAndUser_id(taskId, userPrincipal.getId());

        if (curUserRating.isEmpty()) {
            RatingEntity ratingEntity = new RatingEntity();
            ratingEntity.setTask(myTask);
            ratingEntity.setValue(value > 5 ? 5 : value);
            ratingEntity.setUser(user);

            ratingRepository.save(ratingEntity);
        }
        else {
            curUserRating.get().setValue( value > 5 ? 5 : value );
            ratingRepository.save(curUserRating.get());
        }
        myTask.setAverageRating(averageRating(listRating));
        taskService.save(myTask);

        return true;
    }

    private byte averageRating(List<RatingEntity> listRating ) {
        Double sum = 0d;

        for (RatingEntity item: listRating) {
            sum += item.getValue();
        }

        if(listRating.size() != 0)
            return (byte) Math.round(sum / listRating.size());
        return (byte) 0;
    }
}
