package atminterface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Students
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ATMFrame extends JFrame {

    private User currentUser;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Panels for each screen
    private JPanel menuPanel, withdrawPanel, depositPanel, historyPanel;

    // Shared fields
    private JTextField withdrawAmountField, depositAmountField;
    private JTextArea historyArea;

    public ATMFrame(User user) {
        this.currentUser = user;
        setTitle("ATM - Welcome " + user.getUserId());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // --- 1. Main Menu Panel ---
        menuPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel welcomeLabel = new JLabel("Welcome, User " + currentUser.getUserId(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuPanel.add(welcomeLabel);

        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnDeposit = new JButton("Deposit");
        JButton btnBalance = new JButton("Check Balance");
        JButton btnHistory = new JButton("Transaction History");
        JButton btnExit = new JButton("Exit");

        menuPanel.add(btnWithdraw);
        menuPanel.add(btnDeposit);
        menuPanel.add(btnBalance);
        menuPanel.add(btnHistory);
        menuPanel.add(btnExit);

        // --- 2. Withdraw Panel ---
        withdrawPanel = new JPanel(new GridBagLayout());
        withdrawPanel.setBorder(BorderFactory.createTitledBorder("Withdraw"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        withdrawPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        withdrawAmountField = new JTextField(10);
        withdrawPanel.add(withdrawAmountField, gbc);

        JButton btnWithdrawOk = new JButton("Withdraw");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        withdrawPanel.add(btnWithdrawOk, gbc);

        JButton btnWithdrawBack = new JButton("Back to Menu");
        gbc.gridy = 2;
        withdrawPanel.add(btnWithdrawBack, gbc);

        // --- 3. Deposit Panel ---
        depositPanel = new JPanel(new GridBagLayout());
        depositPanel.setBorder(BorderFactory.createTitledBorder("Deposit"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        depositPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        depositAmountField = new JTextField(10);
        depositPanel.add(depositAmountField, gbc);

        JButton btnDepositOk = new JButton("Deposit");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        depositPanel.add(btnDepositOk, gbc);

        JButton btnDepositBack = new JButton("Back to Menu");
        gbc.gridy = 2;
        depositPanel.add(btnDepositBack, gbc);

        // --- 4. Transaction History Panel ---
        historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Transaction History"));
        historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnHistoryBack = new JButton("Back to Menu");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHistoryBack);
        historyPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add all panels to cardPanel
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(withdrawPanel, "withdraw");
        cardPanel.add(depositPanel, "deposit");
        cardPanel.add(historyPanel, "history");

        // Set frame content
        getContentPane().add(cardPanel);

        // --- Action Listeners ---
        // Main menu buttons
        btnWithdraw.addActionListener(e -> cardLayout.show(cardPanel, "withdraw"));
        btnDeposit.addActionListener(e -> cardLayout.show(cardPanel, "deposit"));
        btnBalance.addActionListener(e -> showBalance());
        btnHistory.addActionListener(e -> {
            loadHistory();
            cardLayout.show(cardPanel, "history");
        });
        btnExit.addActionListener(e -> exit());

        // Withdraw panel
        btnWithdrawOk.addActionListener(this::performWithdraw);
        btnWithdrawBack.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        // Deposit panel
        btnDepositOk.addActionListener(this::performDeposit);
        btnDepositBack.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        // History panel
        btnHistoryBack.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
    }

    private void performWithdraw(ActionEvent e) {
        try {
            double amount = Double.parseDouble(withdrawAmountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (currentUser.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Please take your cash: $" + amount);
                withdrawAmountField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performDeposit(ActionEvent e) {
        try {
            double amount = Double.parseDouble(depositAmountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentUser.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposited: $" + amount);
            depositAmountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this,
                String.format("Current balance: $%.2f", currentUser.getBalance()));
    }

    private void loadHistory() {
        ArrayList<Transaction> transactions = currentUser.getTransactions();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Transaction t : transactions) {
            sb.append(String.format("%s: $%.2f on %s\n", t.getType(), t.getAmount(), sdf.format(t.getTimestamp())));
        }
        if (transactions.isEmpty()) {
            sb.append("No transactions yet.");
        }
        historyArea.setText(sb.toString());
    }

    private void exit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to log out?", "Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    }
}