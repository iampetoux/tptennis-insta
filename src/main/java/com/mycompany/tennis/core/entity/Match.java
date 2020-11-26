package com.mycompany.tennis.core.entity;

public class Match {

    private long id;
    private Epreuve epreuve;
    private Joueur vainqueur;
    private Joueur finaliste;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }
}
