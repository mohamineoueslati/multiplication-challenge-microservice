package com.example.multiplication.challenge.service;

import com.example.multiplication.challenge.domain.Challenge;

public interface ChallengeGeneratorService {
    /**
     * @return a randomly generated challenge with factors between
     11 and 99
     */
    Challenge randomChallenge();
}
