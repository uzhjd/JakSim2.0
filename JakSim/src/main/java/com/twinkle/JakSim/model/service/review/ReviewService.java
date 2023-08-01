package com.twinkle.JakSim.model.service.review;

import com.twinkle.JakSim.model.dao.review.ReviewDao;
import com.twinkle.JakSim.model.dao.trainer.TrainerDao;
import com.twinkle.JakSim.model.dto.review.ReviewDto;
import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public List<ReviewRequestDto> showReviewAll(int page, int pageSize, int filter, String trainerId) {
        return reviewDao.getTrainerReviewAll(page, pageSize, filter, trainerId);
    }

    @Transactional
    public ReviewRequestDto getStarAvgAndCnt(String trainerId) {
        return reviewDao.getStarAvgAndCnt(trainerId);
    }

    @Transactional
    public List<ReviewRequestDto> showMyReview(String userId, int reviewIdx) {
        return reviewDao.getMyReview(userId, reviewIdx);
    }

    @Transactional
    public Optional<List<ReviewRequestDto>> showMyReviewForMyPage(String userId) {
        return reviewDao.getMyReviewForMyPage(userId);
    }

    @Transactional
    public void editReview(ReviewRequestDto review, String userId) {
        reviewDao.editReview(review, userId);
    }

    @Transactional
    public void deleteReview(String userId) {
        reviewDao.deleteReview(userId);
    }

//    public List<ReviewDto> getReviewItems(String username, int page) {
//        return reviewDao.getReviewItems(username, page);
//    }
}
