package com.example.multiplication.challenge.event;

public record ChallengeSolvedEvent(
        long attemptId,
        boolean correct,
        int factorA,
        int factorB,
        long userId,
        String userAlias
) {
}
