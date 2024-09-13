package com.example.multiplication.serviceclients;

import com.example.multiplication.challenge.domain.ChallengeAttempt;
import com.example.multiplication.challenge.dto.ChallengeSolvedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GamificationServiceClient {

    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;

    public GamificationServiceClient(
            final RestTemplateBuilder restTemplateBuilder,
            @Value("${service.gamification.host}") final String gamificationHostUrl
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.gamificationHostUrl = gamificationHostUrl;
    }

    public boolean sendAttempt(final ChallengeAttempt attempt) {
        try {
            var challengeSolved = new ChallengeSolvedDTO(
                    attempt.getId(),
                    attempt.isCorrect(),
                    attempt.getFactorA(),
                    attempt.getFactorB(),
                    attempt.getUser().getId(),
                    attempt.getUser().getAlias()
            );
            ResponseEntity<String> response =
                    restTemplate.postForEntity(gamificationHostUrl, challengeSolved, String.class);
            log.info("Gamification service response: {}", response.getBody());
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
            return false;
        }
    }

}
