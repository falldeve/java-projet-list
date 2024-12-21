// src/main/java/com/exame/Main.java
package com.exame;

import com.exame.services.serviceImpl.CompteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CompteServiceImpl compteService = new CompteServiceImpl();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("Menu :");
            System.out.println("1. Créer un compte avec client");
            System.out.println("2. Afficher les comptes");
            System.out.println("3. Lister les clients");
            System.out.println("4. Rechercher un client par téléphone");
            System.out.println("5. Créer une dette pour un client");
            System.out.println("6. Enregistrer un paiement pour une dette");
            System.out.println("7. Lister les dettes non soldées d'un client");
            System.out.println("8. Lister les demandes de dette");
            System.out.println("9. Valider une demande de dette");
            System.out.println("10. Refuser une demande de dette");




            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                // Création d'un compte avec un client
                System.out.print("Email : ");
                String email = scanner.nextLine();
                System.out.print("Login : ");
                String login = scanner.nextLine();
                System.out.print("Mot de passe : ");
                String password = scanner.nextLine();
                System.out.print("Nom : ");
                String surname = scanner.nextLine();
                System.out.print("Téléphone : ");
                String telephone = scanner.nextLine();
                System.out.print("Adresse : ");
                String adresse = scanner.nextLine();

                compteService.creerCompteAvecClient(email, login, password, surname, telephone, adresse);
                System.out.println("Compte créé avec succès !");
                break;


                case 2:
                    // Affichage des comptes
                    compteService.afficherComptes();
                    break;

                case 3:
                    // Lister les clients
                    System.out.print("Afficher les clients avec comptes (1) ou sans comptes (0) ? : ");
                    int filterChoice = scanner.nextInt();
                    compteService.listerClients(filterChoice == 1);
                    break;

                    case 4:
                    // Rechercher un client par téléphone
                    System.out.print("Entrez le numéro de téléphone : ");
                    String phoneToSearch = scanner.nextLine();
                    compteService.rechercherClientParTelephone(phoneToSearch);
                    break;

                     case 5:
                    // Créer une dette pour un client
                    System.out.print("Entrez le numéro de téléphone du client : ");
                    String phoneForDebt = scanner.nextLine();
                    System.out.print("Montant de la dette : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.print("Articles (séparés par des virgules) : ");
                    String articlesInput = scanner.nextLine();
                    List<String> articles = new ArrayList<>();
                    for (String article : articlesInput.split(",")) {
                        articles.add(article.trim());
                    }
                    compteService.ajouterDette(phoneForDebt, montant, articles);
                    break;

                    case 6:
                    // Enregistrer un paiement pour une dette
                    System.out.print("Entrez le numéro de téléphone du client : ");
                    String phoneForPayment = scanner.nextLine();
                    System.out.print("Montant du paiement : ");
                    double paymentAmount = scanner.nextDouble();
                    compteService.enregistrerPaiement(phoneForPayment, paymentAmount);
                    break;

                    case 7:
                    // Lister les dettes non soldées d'un client
                    System.out.print("Entrez le numéro de téléphone du client : ");
                    String phoneForDebts = scanner.nextLine();
                    compteService.listerDettesNonSoldees(phoneForDebts);
                    break;

                    case 8:
                    // Lister les demandes de dette
                    System.out.print("Filtrer par état (EN_COURS ou ANNULEE) : ");
                    String etatFiltre = scanner.nextLine();
                    compteService.listerDemandesDette(etatFiltre);
                    break;

                case 9:
                    // Valider une demande de dette
                    System.out.print("Entrez le numéro de téléphone du client : ");
                    String phoneForValidation = scanner.nextLine();
                    compteService.validerDette(phoneForValidation);
                    break;

                case 10:
                    // Refuser une demande de dette
                    System.out.print("Entrez le numéro de téléphone du client : ");
                    String phoneForRefusal = scanner.nextLine();
                    compteService.refuserDette(phoneForRefusal);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 0);

        scanner.close();
    }
}