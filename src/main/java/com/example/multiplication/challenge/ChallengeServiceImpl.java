package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        // Check if the attempt is correct
        boolean isCorrect = attemptDTO.guess() == attemptDTO.factorA() * attemptDTO.factorB();
        User user = new User(null, attemptDTO.userAlias());
        return new ChallengeAttempt(
                null,
                user,
                attemptDTO.factorA(),
                attemptDTO.factorB(),
                attemptDTO.guess(),
                isCorrect
        );
    }
}
