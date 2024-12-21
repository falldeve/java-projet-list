package com.exame.entites;

import java.util.Date;
import java.util.List;

import com.exame.enume.Etat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dettes {
     private Date date;
    private double montant;
    private double montantVerse;
    private double montantRestant;
    private Etat etat;
    private List<String> articles; // Liste des articles associés
    private boolean paiementEffectue; // Indique si un paiement a été effectué

    public void effectuerPaiement(double montant) {
        this.montantVerse += montant;
        this.montantRestant -= montant;
        if (this.montantRestant <= 0) {
            this.paiementEffectue = true; 
        }
    }

    public Dettes(Date date, double montant, List<String> articles) {
        this.date = date;
        this.montant = montant;
        this.montantVerse = 0; // Initialisé à 0
        this.montantRestant = montant; // Initialisé au montant total
        this.articles = articles;
        this.paiementEffectue = false; // Initialisé à false
    }

    public Dettes(Date date, double montant, List<String> articles, Etat etat) {
        this.date = date;
        this.montant = montant;
        this.montantVerse = 0; // Initialisé à 0
        this.montantRestant = montant; // Initialisé au montant total
        this.articles = articles;
        this.paiementEffectue = false; // Initialisé à false
        this.etat=Etat.EN_COURS;
    }
    public void annuler() {
        this.etat = Etat.ANNULEE; // Changer l'état à Annulée
    }

}
