package com.example.challenge.service;

import com.example.challenge.entity.ChallengeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.challenge.repository.ChallengeRepository;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeEntity saveChallenge(ChallengeEntity challenge){
        return challengeRepository.save(challenge);
    }

    public ChallengeEntity getCurrentChallenge(Timestamp currentDate){
        return challengeRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate,currentDate);
    }

    public ChallengeEntity getChallengeById(int id){
        return challengeRepository.findById(id);
    }
}
