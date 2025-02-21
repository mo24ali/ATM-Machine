/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmachine1;

import java.util.Scanner;

/**
 *
 * @author alich
 */
public class ATMachine {

    /**
     * @param args the command line arguments
     */
    public static void main_1(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        // Ajout d'utilisateurs pour tester
        bank.registerUser("Alice", "pass123", 1000);
        bank.registerUser("Bob", "secure456", 500);

        // Connexion d'un utilisateur
        System.out.print("\nEntrez votre nom d'utilisateur : ");
        String username = sc.next();
        System.out.print("Entrez votre mot de passe : ");
        String password = sc.next();

        User user = bank.authenticateUser(username, password);

        if (user != null) {
            System.out.println("\nConnexion réussie !");
            boolean running = true; // ✅ Variable declared correctly

            while (running) {
                System.out.println("\nMenu :");
                System.out.println("1. Déposer de l'argent");
                System.out.println("2. Retirer de l'argent");
                System.out.println("3. Voir solde");
                System.out.println("4. Voir historique des transactions");
                System.out.println("5. Transférer de l'argent");
                System.out.println("6. Quitter");
                System.out.print("Choisissez une option : ");
                int choix = sc.nextInt();

                switch (choix) {
                    case 1:
                        System.out.print("Montant à déposer : ");
                        double montantDep = sc.nextDouble();
                        user.deposit(montantDep);
                        break;
                    case 2:
                        System.out.print("Montant à retirer : ");
                        double montantRet = sc.nextDouble();
                        user.withdraw(montantRet);
                        break;
                    case 3:
                        System.out.println("Solde actuel : " + user.getBalance() + " DH");
                        break;
                    case 4:
                        user.printTransactionsHistory();
                        break;
                    case 5:
                        System.out.print("Entrez le nom du destinataire : ");
                        String receiverUsername = sc.next();
                        System.out.print("Montant à transférer : ");
                        double transferAmount = sc.nextDouble();
                        bank.transfer(user.getUsername(), receiverUsername, transferAmount);
                        break;
                    case 6:
                        running = false; // ✅ This exits the loop
                        System.out.println("Déconnexion...");
                        break;
                    default:
                        System.out.println("Option invalide, réessayez.");
                }
            }
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect !");
        }

        sc.close();
    }
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.registerUser("Alice", "pass123", 1000);
        bank.registerUser("Bob", "secure456", 500);

        new LoginUI(bank);
    }

    }
