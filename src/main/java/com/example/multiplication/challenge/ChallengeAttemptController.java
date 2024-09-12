package com.example.multiplication.challenge;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides a REST API to POST the attempts from users.
 */

@RestController
@RequestMapping("attempts")
@RequiredArgsConstructor
@Slf4j
public class ChallengeAttemptController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO attemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDTO));
    }

    @GetMapping
    public ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(challengeService.getStatsForUser(alias));
    }

}
