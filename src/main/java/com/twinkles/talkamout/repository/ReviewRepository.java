package com.twinkles.talkamout.repository;

import com.twinkles.talkamout.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
