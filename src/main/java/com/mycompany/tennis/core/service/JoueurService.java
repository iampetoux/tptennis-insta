package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();

    public void createJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(long id) {

        return joueurRepository.getById(id);
    }

    public List<Joueur> all()
    {
        return joueurRepository.list();
    }

    public void updateJoueur(Joueur joueur)
    {
        joueurRepository.update(joueur);
    }

    public void deleteJoueur(long id)
    {
        joueurRepository.delete(id);
    }
}
