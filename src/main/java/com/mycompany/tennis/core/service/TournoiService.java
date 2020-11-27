package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {

    public final TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();

    public Tournoi getTournoi(long id) {
        return tournoiRepository.getById(id);
    }

    public void createTournoi(Tournoi tournoi) {
        tournoiRepository.create(tournoi);
    }

    public void deleteTournoi(Tournoi tournoi) {
        tournoiRepository.delete(tournoi);
    }

}
