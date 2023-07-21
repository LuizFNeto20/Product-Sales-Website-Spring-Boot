package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Review;


public interface ReviewService {
    
    public void deleteReview(Long id);

    public Review getReviewById(Long id);

    public Review saveReview(Review review);

    public void updateReview(Review review);

    public List<Review> getAllReviews(Sort sort);

}
