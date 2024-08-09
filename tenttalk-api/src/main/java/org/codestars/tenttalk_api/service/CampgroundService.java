package org.codestars.tenttalk_api.service;

import org.codestars.tenttalk_api.models.Campground;
import org.codestars.tenttalk_api.models.Review;
import org.codestars.tenttalk_api.models.data.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.codestars.tenttalk_api.models.data.CampgroundRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampgroundService {

    @Autowired
    private CampgroundRepository campgroundRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Campground addWithReviews(Campground campground) {
        List<Review> reviews = campground.getReviews();
        campground.setReviews(new ArrayList<>());

        Campground savedCampground = campgroundRepository.save(campground);

        for(Review review: reviews){
            review.setCampground(savedCampground);
            reviewRepository.save(review);
            savedCampground.getReviews().add(review);
        }
        return campgroundRepository.save(savedCampground);
    }


}
