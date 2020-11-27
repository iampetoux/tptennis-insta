package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(appliesTo = "joueurs")
public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private Character sexe;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Détails du joueur :\nId : " + this.id + "\nNom : " + this.nom + "\nPrénom : " + this.prenom + "\nSexe : " + this.sexe;
    }
}
