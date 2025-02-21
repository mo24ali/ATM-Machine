/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmachine1;
import java.util.*;


/**
 *
 * @author alich
 */
public class User {
    private String username;
    private String password;
    private double  balance;
     List<Transaction> transactionHistory;
    //constructeur
    public User(String name, String pass, double bl) {
        this.username = name;
        this.password = pass;
        this.balance = bl;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Création de compte", bl);
    }

    //L'authentification
    public boolean authenticate(String pass1){
        return this.password.equals(pass1);
    }
    //Déposer de l'argent
    public void deposit(double montant){
        if(montant >= 0.0){
            balance += montant;
            addTransaction("Depot du montant : " + montant + " DH" , montant);
        }else{
            System.out.println("le montant doit etre positif");
        }
    }
    public void withdraw(double montant) {
        if (montant > 0 && balance >= montant) {
            balance -= montant;
            addTransaction("Retrait", montant);
            System.out.println("Retrait réussi ! Nouveau solde : " + balance + " DH.");
        } else {
            System.out.println("Fonds insuffisants ou montant invalide.");
        }
    }   
   // Ajouter une transaction à l'historique
    private void addTransaction(String type, double amount) {
        Transaction transaction = new Transaction(username, type, amount);
        transactionHistory.add(transaction);
    }
    public boolean transferMoney(User receiver, double amount) {
    if (amount > 0 && balance >= amount) {
        this.balance -= amount;
        receiver.balance += amount;

        // Ajouter les transactions pour l'expéditeur et le destinataire
        this.addTransaction("Transfert envoyé à " + receiver.getUsername(), -amount);
        receiver.addTransaction("Transfert reçu de " + this.getUsername(), amount);

        System.out.println("Transfert réussi ! Nouveau solde : " + this.balance + " DH.");
        return true;
    } else {
        System.out.println("Transfert échoué : montant invalide ou fonds insuffisants.");
        return false;
    }
}

    // Afficher l'historique des transactions
    public void printTransactionsHistory() {
        System.out.println("\nHistorique des transactions pour " + username + " :");
        if (transactionHistory.isEmpty()) {
            System.out.println("Aucune transaction enregistrée.");
        } else {
            for (Transaction tr : transactionHistory) {
                tr.printTransaction();
            }
        }
    }
    //affichage nom
    public String getUsername(){
        return username;
    }
    //Affichage de solde
   
    public double getBalance(){
        return balance;
    }
   /* public void printTransactionsHistory(){
        System.out.println("L'historique de transactions : ");
        for(String tr : transactionHistory){
            System.out.println(tr);
        }
    }*/  
    /*public void addTransaction(String transaction){
        this.transactionHistory.add(transaction);
    }*/
    
}
