/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmachine1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alich
 */


public class LoginUI extends JFrame {
    private Bank bank;

    public LoginUI(Bank bank) {
        this.bank = bank;

        setTitle("Connexion - ATM");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Se connecter");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);

        // Gestion du clic sur "Se connecter"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = bank.authenticateUser(username, password);

                if (user != null) {
                    messageLabel.setText("Connexion réussie !");
                    dispose(); // Fermer la fenêtre actuelle
                    new ATMUI(user, bank); // Ouvrir l'interface ATM
                } else {
                    messageLabel.setText("Identifiants incorrects !");
                }
            }
        });

        // Layout de la fenêtre
        setLayout(new GridLayout(4, 2));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel("")); // Espace vide
        add(loginButton);
        add(messageLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.registerUser("Alice", "pass123", 1000);
        bank.registerUser("Bob", "secure456", 500);

        new LoginUI(bank);
    }
}
