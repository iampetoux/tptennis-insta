package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(appliesTo = "epreuves")
public class Epreuve {
    private  static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int annee;

    @Column
    private Character typeEpreuve;

    @Column
    private Tournoi tournoi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(char typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
}
