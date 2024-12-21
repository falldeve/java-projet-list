package com.exame.services.servicesInterfaces;

import java.util.List;

import com.exame.entites.Client;
import com.exame.entites.Compte;
import com.exame.entites.Dettes;

public interface CompteService {
     Compte creerCompteAvecClient(String email, String login, String password, String surname, String telephone, String adresse);
     void afficherComptes();
     void listerClients(boolean withAccounts);
     void rechercherClientParTelephone(String telephone);
     void ajouterDette(String telephone, double montant, List<String> articles);
     void enregistrerPaiement(String telephone, double montant);
     void listerDettesNonSoldees(String telephone);
     void listerDemandesDette(String etatFiltre);
     void afficherDemandeDette(Dettes dette, Client client);
     void validerDette(String telephone);
     void refuserDette(String telephone);
}
