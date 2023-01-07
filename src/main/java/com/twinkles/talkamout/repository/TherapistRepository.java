package com.twinkles.talkamout.repository;

import com.twinkles.talkamout.model.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    List<Therapist> findTherapistByAddress_State(String address_state);
}
