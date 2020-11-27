package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;

import java.util.List;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepository = new EpreuveRepositoryImpl();

    public void createEpreuve(Epreuve epreuve) {
        epreuveRepository.create(epreuve);
    }

    public Epreuve getEpreuve(long id) {
        return epreuveRepository.getById(id);
    }

    public List<Epreuve> all() {
        return epreuveRepository.list();
    }


}
