package com.example.multiplication.challenge.dto;

public record ChallengeSolvedDTO(
        long attemptId,
        boolean correct,
        int factorA,
        int factorB,
        long userId,
        String userAlias
) {
}
