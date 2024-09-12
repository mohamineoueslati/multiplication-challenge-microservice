package com.example.multiplication.challenge.repository;

import com.example.multiplication.challenge.domain.ChallengeAttempt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
    /**
     * @return the last 10 attempts for a given user, identified by their alias.
     */
    @Query("SELECT a FROM ChallengeAttempt a WHERE a.user.alias = :userAlias ORDER BY a.id DESC LIMIT 10")
    List<ChallengeAttempt> lastAttempts(@Param("userAlias") String userAlias);
}
