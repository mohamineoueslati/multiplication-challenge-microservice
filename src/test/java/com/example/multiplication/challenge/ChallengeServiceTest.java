package com.example.multiplication.challenge;

import com.example.multiplication.challenge.domain.ChallengeAttempt;
import com.example.multiplication.challenge.dto.ChallengeAttemptDTO;
import com.example.multiplication.challenge.repository.ChallengeAttemptRepository;
import com.example.multiplication.challenge.service.ChallengeService;
import com.example.multiplication.challenge.service.ChallengeServiceImpl;
import com.example.multiplication.user.domain.User;
import com.example.multiplication.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    private ChallengeService challengeService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChallengeAttemptRepository attemptRepository;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(userRepository, attemptRepository);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "amine", 3000);
        given(attemptRepository.save(any())).will(returnsFirstArg());
        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        // then
        then(resultAttempt.isCorrect()).isTrue();
        // verify
        verify(userRepository).save(new User("amine"));
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "amine", 5000);
        given(attemptRepository.save(any())).will(returnsFirstArg());
        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        // then
        then(resultAttempt.isCorrect()).isFalse();
        // verify
        verify(userRepository).save(new User("amine"));
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        User existingUser = new User(1L,"amine");
        given(attemptRepository.save(any())).will(returnsFirstArg());
        given(userRepository.findByAlias("amine")).willReturn(Optional.of(existingUser));
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "amine", 5000);
        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        // then
        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getUser()).isEqualTo(existingUser);
        // verify
        verify(userRepository, never()).save(any());
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void retrieveStatsTest() {
        // given
        User user = new User("amine");
        ChallengeAttempt attempt1 = new ChallengeAttempt(1L, user, 50, 60, 3010, false);
        ChallengeAttempt attempt2 = new ChallengeAttempt(2L, user, 50, 60, 3051, false);
        List<ChallengeAttempt> lastAttempts = List.of(attempt1, attempt2);
        given(attemptRepository.lastAttempts("amine")).willReturn(lastAttempts);
        // When
        List<ChallengeAttempt> lastAttemptsResult = challengeService.getStatsForUser("amine");
        // Then
        then(lastAttemptsResult).isEqualTo(lastAttempts);
    }
}
