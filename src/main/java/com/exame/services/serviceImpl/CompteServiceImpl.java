package com.exame.services.serviceImpl;

import java.util.List;
import java.util.Date;

import com.exame.entites.Client;
import com.exame.entites.Compte;
import com.exame.entites.Dettes;
import com.exame.enume.Etat;
import com.exame.repos.reposImpl.CompteRepoImpl;
import com.exame.services.servicesInterfaces.CompteService;

public class CompteServiceImpl implements CompteService{
    private CompteRepoImpl compteRepoImpl = new CompteRepoImpl() ;

    @Override
      public Compte creerCompteAvecClient(String email, String login, String password, String surname, String telephone, String adresse) {
        Client client = new Client(surname, telephone, adresse, 0); // Initialiser le montant dû à 0
        Compte compte = new Compte(email, login, password);
        compte.associerClient(client);
        compteRepoImpl.save(compte);
        return compte;
    }
    
    @Override
    public void afficherComptes() {
        for (Compte compte : compteRepoImpl.findAll()) {
            System.out.println("Email: " + compte.getEmail());
            System.out.println("Login: " + compte.getLogin());
            if (compte.getClient() != null) {
                System.out.println("Client: " + compte.getClient().getSurname());
                System.out.println("Téléphone: " + compte.getClient().getTelephone());
                System.out.println("Adresse: " + compte.getClient().getAdresse());
            } else {
                System.out.println("Aucun client associé.");
            }
            System.out.println("----------");
        }
    }

    @Override
     public void listerClients(boolean withAccounts) {
        List<Client> clients = compteRepoImpl.findClientsWithOrWithoutAccounts(withAccounts);
        for (Client client : clients) {
            System.out.println("Nom: " + client.getSurname());
            System.out.println("Téléphone: " + client.getTelephone());
            System.out.println("Adresse: " + client.getAdresse());
            System.out.println("Montant dû: " + client.getMontantDu());
            System.out.println("----------");
        }
    }

    @Override
    public void rechercherClientParTelephone(String telephone) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null) {
            System.out.println("Client trouvé :");
            System.out.println("Nom: " + client.getSurname());
            System.out.println("Téléphone: " + client.getTelephone());
            System.out.println("Adresse: " + client.getAdresse());
            System.out.println("Montant dû: " + client.getMontantDu());
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }

    @Override
    public void ajouterDette(String telephone, double montant, List<String> articles) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null) {
            Dettes dette = new Dettes(new Date(), montant, articles);
            client.ajouterDette(dette);
            System.out.println("Dette ajoutée pour le client : " + client.getSurname());
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }

    @Override
    public void enregistrerPaiement(String telephone, double montant) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null && !client.getDettes().isEmpty()) {
            for (Dettes dette : client.getDettes()) {
                if (!dette.isPaiementEffectue()) {
                    dette.effectuerPaiement(montant);
                    System.out.println("Paiement de " + montant + " enregistré pour la dette du client : " + client.getSurname());
                    return; 
                }
            }
            System.out.println("Aucune dette à payer pour le client : " + client.getSurname());
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone ou aucune dette existante.");
        }
    }

    @Override
    public void listerDettesNonSoldees(String telephone) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null) {
            List<Dettes> dettesNonSoldees = client.getDettesNonSoldees();
            if (!dettesNonSoldees.isEmpty()) {
                System.out.println("Dettes non soldées pour le client : " + client.getSurname());
                for (Dettes dette : dettesNonSoldees) {
                    System.out.println("Montant restant : " + dette.getMontantRestant());
                    System.out.println("Articles : " + String.join(", ", dette.getArticles()));
                    System.out.println("----------");
                }
            } else {
                System.out.println("Aucune dette non soldée pour le client : " + client.getSurname());
            }
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }

    @Override
     public void listerDemandesDette(String etatFiltre) {
        List<Compte> comptes = compteRepoImpl.findAll();
        for (Compte compte : comptes) {
            Client client = compte.getClient();
            if (client != null) {
                for (Dettes dette : client.getDettes()) {
                    if (etatFiltre.equals("EN_COURS") && dette.getEtat() == Etat.EN_COURS) {
                        afficherDemandeDette(dette, client);
                    } else if (etatFiltre.equals("ANNULEE") && dette.getEtat() == Etat.ANNULEE) {
                        afficherDemandeDette(dette, client);
                    }
                }
            }
        }
    }
    @Override
    public void afficherDemandeDette(Dettes dette, Client client)
    {
        System.out.println("Client : " + client.getSurname());
        System.out.println("Montant : " + dette.getMontant());
        System.out.println("Articles : " + String.join(", ", dette.getArticles()));
        System.out.println("État : " + dette.getEtat());
        System.out.println("----------");
    }

    @Override
    public void validerDette(String telephone) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null) {
            for (Dettes dette : client.getDettes()) {
                if (dette.getEtat() == Etat.EN_COURS) {
                    // Logique pour valider la dette
                    System.out.println("Dette validée pour le client : " + client.getSurname());
                    return;
                }
            }
            System.out.println("Aucune demande de dette en cours pour le client : " + client.getSurname());
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }
    @Override
    public void refuserDette(String telephone) {
        Client client = compteRepoImpl.findClientByTelephone(telephone);
        if (client != null) {
            for (Dettes dette : client.getDettes()) {
                if (dette.getEtat() == Etat.EN_COURS) {
                    dette.annuler(); // Annuler la dette
                    System.out.println("Dette annulée pour le client : " + client.getSurname());
                    return;
                }
            }
            System.out.println("Aucune demande de dette en cours pour le client : " + client.getSurname());
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }




}
