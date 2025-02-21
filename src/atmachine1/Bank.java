package atmachine1;

import java.util.*;

public class Bank {

    private Map<String, User> users; // Stocke les utilisateurs (username -> User)

    // Constructeur
    public Bank() {
        users = new HashMap<>();
    }

    // Ajouter un utilisateur
    public void registerUser(String username, String password, double initialBalance) {
        if (!userExists(username)) { // Vérifier si l'utilisateur existe déjà
            User u = new User(username, password, initialBalance);
            users.put("client" + users.size(), u); // Générer une clé unique
            System.out.println("Utilisateur " + username + " enregistré avec succès !");
        } else {
            System.out.println("Erreur : Cet utilisateur existe déjà !");
        }
    }

    // Authentifier un utilisateur
    public User authenticateUser(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null; // Si l'authentification échoue
    }

    // Vérifier si un utilisateur existe
    public boolean userExists(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public void transfer(String senderUsername, String receiverUsername, double amount) {
    User sender = users.get(senderUsername);
    User receiver = users.get(receiverUsername);

    if (sender == null) {
        System.out.println("Erreur : L'expéditeur " + senderUsername + " n'existe pas.");
    } else if (receiver == null) {
        System.out.println("Erreur : Le destinataire " + receiverUsername + " n'existe pas.");
    } else {
        sender.transferMoney(receiver, amount);
    }
}


    // Afficher tous les utilisateurs enregistrés 
    public void listUsers() {
        System.out.println("\nListe des utilisateurs enregistrés :");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("Utilisateur : " + user.getUsername() + " | Solde : " + user.getBalance()+ "DH");
        }
    }
}
