package com.example.multiplication.challenge.service;

import com.example.multiplication.challenge.domain.ChallengeAttempt;
import com.example.multiplication.challenge.dto.ChallengeAttemptDTO;

import java.util.List;

public interface ChallengeService {
    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * @return the resulting ChallengeAttempt object
     */
    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO);

    /**
     * Gets the statistics for a given user.
     * @param userAlias the user's alias
     * @return a list of the last 10 {@link ChallengeAttempt}
     * objects created by the user.
     */
    List<ChallengeAttempt> getStatsForUser(String userAlias);
}
