/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmachine1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alich
 */


public class Transaction {
    private String username;
    private String type; // Dépôt, Retrait, Transfert, etc.
    private double amount;
    private String dateTime;

    // Constructeur
    public Transaction(String username, String type, double amount) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.dateTime = getCurrentTime();
    }

    // Obtenir l'heure actuelle sous format lisible
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }

    // Affichage des détails de la transaction
    public void printTransaction() {
        System.out.println("[" + dateTime + "] " + type + " de " + amount + " DH - Utilisateur : " + username);
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateTime() {
        return dateTime;
    }
}
