package com.exame.repos.reposImpl;

import java.util.ArrayList;
import java.util.List;

import com.exame.entites.Compte;
import com.exame.entites.Client;


public class CompteRepoImpl {
     private List<Compte> comptes = new ArrayList<>();

    public void save(Compte compte) {
        comptes.add(compte);
    }

    public List<Compte> findAll() {
        return comptes;
    }

    public List<Client> findClientsWithOrWithoutAccounts(boolean withAccounts) {
        List<Client> clients = new ArrayList<>();
        for (Compte compte : comptes) {
            if (withAccounts && compte.getClient() != null) {
                clients.add(compte.getClient());
            } else if (!withAccounts && compte.getClient() == null) {
                clients.add(new Client("Aucun", "Aucun", "Aucun", 0)); 
            }
        }
        return clients;
    }

    public Client findClientByTelephone(String telephone) {
        for (Compte compte : comptes) {
            if (compte.getClient() != null && compte.getClient().getTelephone().equals(telephone)) {
                return compte.getClient();
            }
        }
        return null; // Retourne null si aucun client n'est trouvé
    }
}
