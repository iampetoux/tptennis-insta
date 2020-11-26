package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class TestDeConnexion {
    public static void main(String[] args) throws Exception {
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();

        Joueur joueur = new Joueur();
        joueur.setNom("Ben Amor");
        joueur.setPrenom("Youssef");
        joueur.setSexe('M');

        //Persiste le model dans la BDD: CREATE
        joueurRepository.create(joueur);

        //test pour savoir si il y a bien une entrée dans la BDD: READ
        Joueur joueurBis = joueurRepository.getById(1);
        if(joueurBis == null) {
            throw new Exception("Aucun joueur retrouvé");
        }
        //Update
        joueur.setPrenom("Adil");
        joueur.setNom("Ouaali");
        joueur.setId(2);
        joueurRepository.update(joueur);
        joueur = joueurRepository.getById(2);
        if(!joueur.getPrenom().equals("Adil") || !joueur.getNom().equals("Ouaali")){
            throw new Exception("Update failed");
        }

        //test pour get une liste de joueurs
        List<Joueur> joueurs = joueurRepository.list();
        System.out.println("Liste des joueurs en BDD");
        for (Joueur j: joueurs) {
            System.out.println(j.getNom() + " " + j.getPrenom());
        }
        //supprimer une entrée à l'id 1
        joueurRepository.delete(1);
        //vérifie si il existe
        Joueur joueurSupprime = joueurRepository.getById(1);
        if(joueurSupprime != null) {
            throw new Exception("Joueur non supprimé");
        }


    }
}
