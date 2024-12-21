package com.exame.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    private String email;
    private String login;
    private String password;
    private Client client;

      // Méthode pour associer un client
      public void associerClient(Client client) {
        this.client = client;
    }

     // Constructeur
     public Compte(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
