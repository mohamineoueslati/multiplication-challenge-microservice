package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 */

@Entity
@Table(name = "challenge_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
