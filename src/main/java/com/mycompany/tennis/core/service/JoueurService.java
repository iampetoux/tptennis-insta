package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;

import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
    private final MatchRepositoryImpl matchRepository = new MatchRepositoryImpl();

    public void createJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(long id) {

        return joueurRepository.getById(id);
    }

    public List<Joueur> all() {
        return joueurRepository.list();
    }

    public void updateJoueur(Joueur joueur) {
        joueurRepository.update(joueur);
    }

    public void deleteJoueur(long id) {
        joueurRepository.delete(id);
    }

    public Joueur getVainqueur(Match match) {
        return joueurRepository.getById(match.getVainqueur().getId());
    }

    public Joueur getFinaliste(Match match) {
        return joueurRepository.getById(match.getFinaliste().getId());
    }
}
