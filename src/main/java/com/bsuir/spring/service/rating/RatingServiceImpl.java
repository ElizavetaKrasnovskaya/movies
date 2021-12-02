package com.bsuir.spring.service.rating;

import com.bsuir.spring.model.Rating;
import com.bsuir.spring.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
