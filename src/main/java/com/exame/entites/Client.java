package com.exame.entites;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String surname;
    private String telephone;
    private String adresse;
    private double montantDu;
     private List<Dettes> dettes; // Liste des dettes


  public Client(String surname, String telephone, String adresse, double montantDu) {
        this.surname = surname;
        this.telephone = telephone;
        this.adresse = adresse;
        this.montantDu = montantDu;
        this.dettes = new ArrayList<>(); // Initialiser la liste des dettes
    }

    public void ajouterDette(Dettes dette) {
        this.dettes.add(dette);
        this.montantDu += dette.getMontant(); // Mettre à jour le montant dû
    }

    public List<Dettes> getDettesNonSoldees() {
        List<Dettes> dettesNonSoldees = new ArrayList<>();
        for (Dettes dette : dettes) {
            if (dette.getMontantRestant() != 0) {
                dettesNonSoldees.add(dette);
            }
        }
        return dettesNonSoldees;
    }
}
