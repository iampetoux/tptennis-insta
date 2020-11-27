package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;

import java.util.List;

public class MatchService {
    private final MatchRepositoryImpl matchRepository = new MatchRepositoryImpl();

    public void createMatch(Match match) {
        matchRepository.create(match);
    }

    public Match getMatch(long id) {
        return matchRepository.getById(id);
    }

    public List<Match> all() {
        return matchRepository.list();
    }


}
