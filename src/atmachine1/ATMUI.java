

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



public class ATMUI extends JFrame {
    private User user;
    private Bank bank;
    private JLabel balanceLabel;

    public ATMUI(User user, Bank bank) {
        this.user = user;
        this.bank = bank;

        setTitle("ATM - Opérations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Composants
        JLabel welcomeLabel = new JLabel("Bienvenue, " + user.getUsername(), SwingConstants.CENTER);
        balanceLabel = new JLabel("Solde actuel: " + user.getBalance() + " DH", SwingConstants.CENTER);

        JButton depositButton = new JButton("Déposer de l'argent");
        JButton withdrawButton = new JButton("Retirer de l'argent");
        JButton transferButton = new JButton("Transférer de l'argent");
        JButton historyButton = new JButton("Voir l'historique");
        JButton logoutButton = new JButton("Déconnexion");

        // Ajout des actions aux boutons
        depositButton.addActionListener(e -> depositMoney());
        withdrawButton.addActionListener(e -> withdrawMoney());
        transferButton.addActionListener(e -> transferMoney());
        historyButton.addActionListener(e -> showHistory());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginUI(bank);
        });

        // Layout de la fenêtre
        setLayout(new GridLayout(6, 1));
        add(welcomeLabel);
        add(balanceLabel);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(historyButton);
        add(logoutButton);

        setVisible(true);
    }

    private void depositMoney() {
        String amountStr = JOptionPane.showInputDialog(this, "Montant à déposer :");
        try {
            double amount = Double.parseDouble(amountStr);
            user.deposit(amount);
            balanceLabel.setText("Solde actuel: " + user.getBalance() + " DH");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Montant invalide !");
        }
    }

    private void withdrawMoney() {
        String amountStr = JOptionPane.showInputDialog(this, "Montant à retirer :");
        try {
            double amount = Double.parseDouble(amountStr);
            user.withdraw(amount);
            balanceLabel.setText("Solde actuel: " + user.getBalance() + " DH");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Montant invalide !");
        }
    }

    private void transferMoney() {
        String receiverUsername = JOptionPane.showInputDialog(this, "Nom du destinataire :");
        if (bank.userExists(receiverUsername)) {
            String amountStr = JOptionPane.showInputDialog(this, "Montant à transférer :");
            try {
                double amount = Double.parseDouble(amountStr);
                bank.transfer(user.getUsername(), receiverUsername, amount);
                balanceLabel.setText("Solde actuel: " + user.getBalance() + " DH");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Montant invalide !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Utilisateur introuvable !");
        }
    }

    private void showHistory() {
        StringBuilder history = new StringBuilder();
        for (Transaction tr : user.transactionHistory) {
            history.append(tr.getDateTime()).append(" - ").append(tr.getType()).append(": ").append(tr.getAmount()).append(" DH\n");
        }
        JOptionPane.showMessageDialog(this, history.length() > 0 ? history.toString() : "Aucune transaction.");
    }
}
