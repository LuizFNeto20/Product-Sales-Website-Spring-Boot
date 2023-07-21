package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Review;
import com.productsaleswebsitespringboot.repository.ReviewRepository;

@Service
public class ReviewServiceImplements implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            return review.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Sort sort) {
        List<Review> reviews = reviewRepository.findAll(sort);
        return reviews;
    }

}
