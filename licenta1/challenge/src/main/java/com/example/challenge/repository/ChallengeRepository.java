package com.example.challenge.repository;

import com.example.challenge.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {

    ChallengeEntity save(ChallengeEntity challenge);

    ChallengeEntity findById(int id);
    ChallengeEntity findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Timestamp currentDate, Timestamp currentDate1);
}
