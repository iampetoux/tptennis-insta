package com.mycompany.tennis.core.controller;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {
    private final TournoiService tournoiService = new TournoiService();

    public void afficherDetailsTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi recherché :");
        long id = scanner.nextLong();
        Tournoi tournoi = tournoiService.getTournoi(id);
        System.out.println("Le tournoi sélectionné s'appelle " + tournoi.getNom());
    }

    public void creerTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du tournoi :");
        String nom = scanner.nextLine();
        System.out.println("Entrez le code du tournoi :");
        String code = scanner.nextLine();
        Tournoi tournoi = new Tournoi();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiService.createTournoi(tournoi);
    }

    public void supprimerTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi à supprimer :");
        long id = scanner.nextLong();
        tournoiService.deleteTournoi(tournoiService.getTournoi(id));
        System.out.println("Le tournoi a été supprimé");
    }
}
