package com.twinkle.JakSim.model.service.review;

import com.twinkle.JakSim.model.dao.review.ReviewDao;
import com.twinkle.JakSim.model.dao.trainer.TrainerDao;
import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Transactional
    public void insertReview(ReviewRequestDto review, String userId, int trainerIdx) {
        reviewDao.insertReview(review, userId, trainerIdx);

    }
    @Transactional
    public List<ReviewRequestDto> showReview(String trainerId) {
        return reviewDao.getTrainerReview(trainerId);
    }

    @Transactional
    public List<ReviewRequestDto> showMyReview(String userId) {
        return reviewDao.getMyReview(userId);
    }

    @Transactional
    public void editReview(ReviewRequestDto review, String userId) {
        reviewDao.editReview(review, userId);
    }

    @Transactional
    public void deleteReview(String userId) {
        reviewDao.deleteReview(userId);
    }

}
