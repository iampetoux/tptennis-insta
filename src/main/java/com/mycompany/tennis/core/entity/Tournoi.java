package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(appliesTo = "tournois")
public class Tournoi implements Serializable {
    private  static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String nom;

    @Column
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
