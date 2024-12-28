import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Currency;
import javax.imageio.ImageIO;


public class OnlinebankingSystem {

    // Login Class.....................................................................................................................................................//
    static class Login extends JFrame {
        JPasswordField pinField;
        JButton signInButton, registerButton;
        JLabel notificationLabel, background;

        private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinebanking_db?zeroDateTimeBehavior=convertToNull";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

        Login() {
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Online Banking System");
            
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);

            ImageIcon bgIcon = resizeImage("iiio.jpeg", 1920, 1080);
            background = new JLabel(bgIcon, JLabel.CENTER);
            background.setBounds(0, 0, 1920, 1080);

            JLabel titleLabel = new JLabel("WELCOME TO ONLINE BANKING SYSTEM");
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
            titleLabel.setForeground(Color.yellow);
            titleLabel.setBounds(620, 50, 900, 50);
            add(titleLabel);

            JLabel pinLabel = new JLabel("PIN:");
            pinLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
            pinLabel.setForeground(Color.white);
            pinLabel.setBounds(750, 350, 100, 50);
            add(pinLabel);

            pinField = new JPasswordField(60);
            pinField.setBounds(850, 350, 300, 50);
            add(pinField);

            JButton signInButton = new JButton("LOGIN");
            signInButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            signInButton.setBounds(850, 450, 200, 50);
            signInButton.setBackground(Color.BLUE); 
            signInButton.setForeground(Color.WHITE); 
            add(signInButton);

            
            registerButton = new JButton("REGISTER");
            registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            registerButton.setBounds(850, 520, 200, 50);
            registerButton.setBackground(Color.GREEN); 
            registerButton.setForeground(Color.BLACK); 
            add(registerButton);
            
            JButton exitButton = new JButton("EXIT");
            exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            exitButton.setBounds(850, 590, 200, 50);
            exitButton.setBackground(Color.RED);
            exitButton.setForeground(Color.WHITE);
            add(exitButton);


            notificationLabel = new JLabel("");
            notificationLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            notificationLabel.setBounds(850, 650, 500, 50);
            notificationLabel.setForeground(Color.RED);
            add(notificationLabel);


            add(background);

            signInButton.addActionListener(ae -> {
            String pin = new String(pinField.getPassword());

            if (pin.isEmpty()) {
              notificationLabel.setText("PIN field cannot be empty....!");
            } else if (authenticateUser(pin)) {
              new AccountSummary(pin).setVisible(true);
              dispose();
            } else {
                notificationLabel.setText("Invalid PIN....!");
            }
            });


            registerButton.addActionListener(ae -> new Registration().setVisible(true));
            exitButton.addActionListener(ae -> System.exit(0));

            setSize(800, 600);
            setLocationRelativeTo(null);
        }
        

        private ImageIcon resizeImage(String imagePath, int width, int height) {
            try {
                BufferedImage img = ImageIO.read(new File(imagePath));
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
         

        private boolean authenticateUser(String pin) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM tb_signup WHERE fdPinCode = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, pin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        return rs.next();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    // Registration Class........................................................................................................................................................//
    static class Registration extends JFrame {
        JTextField nameField, nicField, phoneField, dobField, addressField;
        JPasswordField pinField, rePinField;
        JComboBox<String> accountTypeField, genderField;
        JButton createAccountButton, backButton;
        JLabel notificationLabel, background;

        private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinebanking_db?zeroDateTimeBehavior=convertToNull";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

        Registration() {
            setLayout(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setTitle("Create Account");

            ImageIcon bgIcon = resizeImage("2312127.jpg", 800, 600);
            background = new JLabel("", bgIcon, JLabel.CENTER);
            background.setBounds(0, 0, 800, 600);

            JLabel nameLabel = new JLabel("Name:");
            JLabel nicLabel = new JLabel("NIC NO:");
            JLabel phoneLabel = new JLabel("Phone Number:");
            JLabel dobLabel = new JLabel("Date Of Birth (yyyy-mm-dd):");
            JLabel pinLabel = new JLabel("Pin:");
            JLabel rePinLabel = new JLabel("Re-Enter Pin:");
            JLabel accountTypeLabel = new JLabel("Account Type:");
            JLabel addressLabel = new JLabel("Address:");
            JLabel genderLabel = new JLabel("Gender:");

            nameLabel.setForeground(Color.BLUE);
            nicLabel.setForeground(Color.BLUE);
            phoneLabel.setForeground(Color.BLUE);
            dobLabel.setForeground(Color.BLUE);
            pinLabel.setForeground(Color.BLUE);
            rePinLabel.setForeground(Color.BLUE);
            accountTypeLabel.setForeground(Color.BLUE);
            addressLabel.setForeground(Color.BLUE);
            genderLabel.setForeground(Color.BLUE);

            nameField = new JTextField(60);
            nicField = new JTextField(60);
            phoneField = new JTextField(60);
            dobField = new JTextField(60);
            pinField = new JPasswordField(60);
            rePinField = new JPasswordField(60);
            addressField = new JTextField(60);

            String[] accountTypes = {"Saving", "Current"};
            accountTypeField = new JComboBox<>(accountTypes);

            String[] genders = {"Male", "Female", "Other"};
            genderField = new JComboBox<>(genders);

            createAccountButton = new JButton("Create Account");
            createAccountButton.setForeground(Color.WHITE);
            createAccountButton.setBackground(Color.GREEN);
            notificationLabel = new JLabel("");
            notificationLabel.setForeground(Color.RED);

            backButton = new JButton("Back");
            backButton.setBounds(30, 520, 100, 30);

            nameLabel.setBounds(150, 20, 150, 30);
            nicLabel.setBounds(150, 70, 150, 30);
            phoneLabel.setBounds(150, 120, 150, 30);
            dobLabel.setBounds(150, 170, 250, 30);
            pinLabel.setBounds(150, 220, 150, 30);
            rePinLabel.setBounds(150, 270, 150, 30);
            accountTypeLabel.setBounds(150, 320, 150, 30);
            addressLabel.setBounds(150, 370, 150, 30);
            genderLabel.setBounds(150, 420, 150, 30);

            nameField.setBounds(350, 20, 300, 30);
            nicField.setBounds(350, 70, 300, 30);
            phoneField.setBounds(350, 120, 300, 30);
            dobField.setBounds(350, 170, 300, 30);
            pinField.setBounds(350, 220, 300, 30);
            rePinField.setBounds(350, 270, 300, 30);
            accountTypeField.setBounds(350, 320, 300, 30);
            addressField.setBounds(350, 370, 300, 30);
            genderField.setBounds(350, 420, 300, 30);

            createAccountButton.setBounds(350, 470, 150, 30);
            notificationLabel.setBounds(150, 520, 500, 30);

            add(nameLabel);
            add(nicLabel);
            add(phoneLabel);
            add(dobLabel);
            add(pinLabel);
            add(rePinLabel);
            add(accountTypeLabel);
            add(addressLabel);
            add(genderLabel);
            add(nameField);
            add(nicField);
            add(phoneField);
            add(dobField);
            add(pinField);
            add(rePinField);
            add(accountTypeField);
            add(addressField);
            add(genderField);
            add(createAccountButton);
            add(notificationLabel);
            add(backButton);
            add(background);

            createAccountButton.addActionListener(ae -> registerUser());
            backButton.addActionListener(ae -> dispose());

            setSize(800, 600);
            setLocationRelativeTo(null);
        }

        private ImageIcon resizeImage(String imagePath, int width, int height) {
            try {
                BufferedImage img = ImageIO.read(new File(imagePath));
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private void registerUser() {
            String name = nameField.getText();
            String nic = nicField.getText();
            String phoneNumber = phoneField.getText();
            String dob = dobField.getText();
            String pin = new String(pinField.getPassword());
            String rePin = new String(rePinField.getPassword());
            String accountType = (String) accountTypeField.getSelectedItem();
            String address = addressField.getText();
            String gender = (String) genderField.getSelectedItem();

            
            if (name.isEmpty() || nic.isEmpty() || phoneNumber.isEmpty() || dob.isEmpty()) {
                    notificationLabel.setText("Please fill in all required fields....!");
                    return;
                }

                if (pin.isEmpty() || rePin.isEmpty()) {
                    notificationLabel.setText("PIN fields cannot be empty....!");
                    return;
                }

                if (!pin.equals(rePin)) {
                    notificationLabel.setText("PINs do not match! Please try again....!!");
                    return;
                }
                
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO tb_signup (fdName, fd_NIC, fdPhoneNumber, fd_Dob, fdPinCode, fd_AccountType, fd_Address, fd_Gender, fd_Amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, nic);
                    pstmt.setString(3, phoneNumber);
                    pstmt.setString(4, dob);
                    pstmt.setString(5, pin);
                    pstmt.setString(6, accountType);
                    pstmt.setString(7, address);
                    pstmt.setString(8, gender);
                    pstmt.setDouble(9, 0.0); 
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                    dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
                notificationLabel.setText("Error creating account.");
            }
        }
    }

    // AccountSummary Class.........................................................................................................................................................//
    static class AccountSummary extends JFrame {
        JLabel accountIdLabel, accountTypeLabel, nameLabel, nicLabel, balanceLabel, profileLabel;
        JTextArea transactionArea;
        JButton depositButton, withdrawButton, transferButton, viewHistoryButton, profileButton, logoutButton;
        String userPin;

        private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinebanking_db?zeroDateTimeBehavior=convertToNull";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";
        private Object st;

        AccountSummary(String pin) {
            userPin = pin;
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Account Summary");

            ImageIcon bgIcon = resizeImage("2312127.jpg", 800, 600);
            JLabel background = new JLabel("", bgIcon, JLabel.CENTER);
            background.setBounds(0, 0, 800, 600);

            accountIdLabel = new JLabel();
            accountTypeLabel = new JLabel();
            nameLabel = new JLabel();
            nicLabel = new JLabel();
            balanceLabel = new JLabel();
            profileLabel = new JLabel();
            transactionArea = new JTextArea();
            transactionArea.setEditable(false);

            depositButton = new JButton("DEPOSIT");
            withdrawButton = new JButton("WITHDRAW");
            transferButton = new JButton("TRANSFER");
            viewHistoryButton = new JButton("VIEW HISTORY");
            profileButton = new JButton("MY PROFILE");
            logoutButton = new JButton("Logout");

            accountIdLabel.setBounds(50, 20, 300, 30);
            accountTypeLabel.setBounds(50, 60, 300, 30);
            nameLabel.setBounds(50, 100, 300, 30); 
            nicLabel.setBounds(50, 140, 300, 30);  
            balanceLabel.setBounds(50, 180, 300, 30);
            profileLabel.setBounds(50, 220, 300, 30);


            depositButton.setBounds(50, 420, 150, 30);
            withdrawButton.setBounds(220, 420, 150, 30);
            transferButton.setBounds(390, 420, 150, 30);
            viewHistoryButton.setBounds(560, 420, 150, 30);
            profileButton.setBounds(50, 470, 150, 30);
            logoutButton.setBounds(220, 470, 150, 30);
            
            
            depositButton.setBackground(Color.GREEN);
            depositButton.setForeground(Color.WHITE);

            withdrawButton.setBackground(Color.RED);
            withdrawButton.setForeground(Color.WHITE);

            transferButton.setBackground(Color.ORANGE);
            transferButton.setForeground(Color.WHITE);

            viewHistoryButton.setBackground(Color.BLUE);
            viewHistoryButton.setForeground(Color.WHITE);

            profileButton.setBackground(Color.MAGENTA);
            profileButton.setForeground(Color.WHITE);

            logoutButton.setBackground(Color.GRAY);
            logoutButton.setForeground(Color.WHITE);


            add(accountIdLabel);
            add(accountTypeLabel);
            add(nameLabel);
            add(nicLabel);
            add(balanceLabel);
            add(profileLabel);
            add(depositButton);
            add(withdrawButton);
            add(transferButton);
            add(viewHistoryButton);
            add(profileButton);
            add(logoutButton);
            add(background);

            loadAccountSummary();

            depositButton.addActionListener(ae -> processTransaction("Deposit"));
            withdrawButton.addActionListener(ae -> processTransaction("Withdraw"));
            transferButton.addActionListener(ae -> processTransaction("Transfer"));
            viewHistoryButton.addActionListener(ae -> new TransactionHistory(userPin).setVisible(true));
            profileButton.addActionListener(ae -> showProfile());
            logoutButton.addActionListener(ae -> {
                dispose();
                new Login().setVisible(true);
            });

            setSize(800, 600);
            setLocationRelativeTo(null);
        }

        private ImageIcon resizeImage(String imagePath, int width, int height) {
            try {
                BufferedImage img = ImageIO.read(new File(imagePath));
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

       

        private void loadAccountSummary() {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
               String query = "SELECT * FROM tb_signup WHERE fdPinCode = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, userPin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String accountId = rs.getString("fd_AccountId");
                            String accountType = rs.getString("fd_AccountType");
                            String name = rs.getString("fdName"); 
                            String nic = rs.getString("fd_NIC");   
                            double amount = rs.getDouble("fd_Amount");

                             accountIdLabel.setText("Account ID: " + accountId);
                             accountTypeLabel.setText("Account Type: " + accountType);
                             nameLabel.setText("Name: " + name); 
                             nicLabel.setText("NIC: " + nic);  
                             balanceLabel.setText("Balance: " + formatCurrency(amount));

                             Font boldFont = new Font("Times New Roman", Font.BOLD, 16);
                             accountIdLabel.setFont(boldFont);
                             accountTypeLabel.setFont(boldFont);
                             nameLabel.setFont(boldFont);  
                             nicLabel.setFont(boldFont);   
                             balanceLabel.setFont(boldFont);
                         }
                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }


        private void processTransaction(String transactionType) {
            String input = JOptionPane.showInputDialog(this, "Enter amount to " + transactionType.toLowerCase() + ":");
            if (input != null) {
               try {
                    double amount = Double.parseDouble(input);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(this, "Amount must be greater than zero...............!!!");
                        return;
                    }

                     // Verify PIN for Deposit and Withdraw................................................................//
                     String pin = JOptionPane.showInputDialog(this, "Enter Your PIN...!:");
                     if (pin != null && !pin.isEmpty()) {
                         if (!verifyPin(pin)) {
                             JOptionPane.showMessageDialog(this, "Invalid PIN.....!!!");
                             return;
                         }
                     } else {
                         return;
                     }

                     if (transactionType.equals("Withdraw")) {
                         double currentBalance = getCurrentBalance();
                         if (amount > currentBalance) {
                             JOptionPane.showMessageDialog(this, "Insufficient balance.........!!!!");
                             return;
                         }
                     }

                     if (transactionType.equals("Transfer")) {
                         String receiverId = JOptionPane.showInputDialog(this, "Enter receiver's account ID:");
                         if (receiverId != null && !receiverId.isEmpty()) {
                             executeTransfer(amount, receiverId);
                         }
                     } else {
                         executeTransaction(transactionType, amount);
                     }
                 } catch (NumberFormatException e) {
                     JOptionPane.showMessageDialog(this, "Invalid amount format.....!!!");
                 }
             }
         }

         private boolean verifyPin(String pin) {
             try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                 String query = "SELECT * FROM tb_signup WHERE fdPinCode = ?";
                 try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                     pstmt.setString(1, pin);
                     try (ResultSet rs = pstmt.executeQuery()) {
                         return rs.next();
                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return false;
         }

         private double getCurrentBalance() {
             double balance = 0.0;
             try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                 String query = "SELECT fd_Amount FROM tb_signup WHERE fdPinCode = ?";
                 try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                     pstmt.setString(1, userPin);  
                     try (ResultSet rs = pstmt.executeQuery()) {
                         if (rs.next()) {
                             balance = rs.getDouble("fd_Amount");
                         }
                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return balance;
         }


        private void executeTransaction(String transactionType, double amount) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                conn.setAutoCommit(false);
                String accountId = getAccountId();
                if (accountId == null) return;

                String updateQuery = "";
                if (transactionType.equals("Deposit")) {
                    updateQuery = "UPDATE tb_signup SET fd_Amount = fd_Amount + ? WHERE fd_AccountId = ?";
                } else if (transactionType.equals("Withdraw")) {
                    updateQuery = "UPDATE tb_signup SET fd_Amount = fd_Amount - ? WHERE fd_AccountId = ?";
                }

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setDouble(1, amount);
                    pstmt.setString(2, accountId);
                    pstmt.executeUpdate();

                    String transactionQuery = "INSERT INTO tb_transactions (fd_SenderId, fd_RecieverId, fd_Amount, fd_DateTime) VALUES (?, ?, ?, NOW())";
                    try (PreparedStatement transactionPstmt = conn.prepareStatement(transactionQuery)) {
                        transactionPstmt.setString(1, accountId);
                        transactionPstmt.setString(2, accountId);
                        transactionPstmt.setDouble(3, amount);
                        transactionPstmt.executeUpdate();
                    }
                    conn.commit();
                    JOptionPane.showMessageDialog(this, transactionType + " successful!");
                    loadAccountSummary();
                } catch (Exception e) {
                    conn.rollback();
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error processing transaction......!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void executeTransfer(double amount, String receiverId) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                conn.setAutoCommit(false);

                // Check sender account.............................................................................//
                String senderAccountId = getAccountId();
                if (senderAccountId == null) {
                    JOptionPane.showMessageDialog(this, "Sender account not found........!!!");
                    return;
                }

                // Check if sender has sufficient balance...........................................................//
                String checkBalanceQuery = "SELECT fd_Amount FROM tb_signup WHERE fd_AccountId = ?";
                double senderBalance = 0;
                try (PreparedStatement balancePstmt = conn.prepareStatement(checkBalanceQuery)) {
                    balancePstmt.setString(1, senderAccountId);
                    try (ResultSet rs = balancePstmt.executeQuery()) {
                        if (rs.next()) {
                            senderBalance = rs.getDouble("fd_Amount");
                        }
                    }
                }

                if (senderBalance < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance...........!! Transfer cannot be completed!!!");
                    return;
                }

                // Check receiver account..........................................................................//
                String receiverQuery = "SELECT * FROM tb_signup WHERE fd_AccountId = ?";
                try (PreparedStatement receiverPstmt = conn.prepareStatement(receiverQuery)) {
                    receiverPstmt.setString(1, receiverId);
                    try (ResultSet receiverRs = receiverPstmt.executeQuery()) {
                        if (!receiverRs.next()) {
                            JOptionPane.showMessageDialog(this, "Receiver account not found.");
                            return;
                        }
                    }
                }

                // Update sender's balance...........................................................................//
                String updateSenderQuery = "UPDATE tb_signup SET fd_Amount = fd_Amount - ? WHERE fd_AccountId = ?";
                try (PreparedStatement updateSenderPstmt = conn.prepareStatement(updateSenderQuery)) {
                    updateSenderPstmt.setDouble(1, amount);
                    updateSenderPstmt.setString(2, senderAccountId);
                    updateSenderPstmt.executeUpdate();
                }

                // Update receiver's balance.........................................................................//
                String updateReceiverQuery = "UPDATE tb_signup SET fd_Amount = fd_Amount + ? WHERE fd_AccountId = ?";
                try (PreparedStatement updateReceiverPstmt = conn.prepareStatement(updateReceiverQuery)) {
                    updateReceiverPstmt.setDouble(1, amount);
                    updateReceiverPstmt.setString(2, receiverId);
                    updateReceiverPstmt.executeUpdate();
                }

                // Log transaction.....................................................................................................................//
                String transactionQuery = "INSERT INTO tb_transactions (fd_SenderId, fd_RecieverId, fd_Amount, fd_DateTime) VALUES (?, ?, ?, NOW())";
                try (PreparedStatement transactionPstmt = conn.prepareStatement(transactionQuery)) {
                    transactionPstmt.setString(1, senderAccountId);
                    transactionPstmt.setString(2, receiverId);
                    transactionPstmt.setDouble(3, amount);
                    transactionPstmt.executeUpdate();
                }

                conn.commit();
                JOptionPane.showMessageDialog(this, "Transfer successful!!!!");
                loadAccountSummary();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error processing transfer.......!!!");
            }
        }

        private String getAccountId() {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT fd_AccountId FROM tb_signup WHERE fdPinCode = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, userPin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString("fd_AccountId");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private void showProfile() {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM tb_signup WHERE fdPinCode = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, userPin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String profileInfo = "Name: " + rs.getString("fdName") + "\n" +
                                    "NIC NO: " + rs.getString("fd_NIC") + "\n" +
                                    "Phone Number: " + rs.getString("fdPhoneNumber") + "\n" +
                                    "Date Of Birth: " + rs.getString("fd_Dob") + "\n" +
                                    "Account ID: " + rs.getString("fd_AccountId") + "\n" +
                                    "Account Type: " + rs.getString("fd_AccountType") + "\n" +
                                    "Address: " + rs.getString("fd_Address") + "\n" +
                                    "Gender: " + rs.getString("fd_Gender") + "\n" +
                                    "Balance: " + formatCurrency(rs.getDouble("fd_Amount"));

                            JOptionPane.showMessageDialog(this, profileInfo, "My Profile", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String formatCurrency(double amount) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            Currency currency = Currency.getInstance("LKR");
            currencyFormat.setCurrency(currency);
            return currencyFormat.format(amount);
        }
    }

    // TransactionHistory Class..............................................................//
    static class TransactionHistory extends JFrame {
        JTextArea historyArea;
        JButton backButton;
        String userPin;

        private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinebanking_db?zeroDateTimeBehavior=convertToNull";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

        TransactionHistory(String pin) {
            userPin = pin;
            setLayout(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setTitle("Transaction History");

            historyArea = new JTextArea();
            historyArea.setEditable(false);
            backButton = new JButton("Back");

            historyArea.setBounds(50, 20, 700, 400);
            backButton.setBounds(350, 450, 100, 30);

            add(historyArea);
            add(backButton);

            loadTransactionHistory();

            backButton.addActionListener(ae -> dispose());

            setSize(800, 600);
            setLocationRelativeTo(null);
        }

        private void loadTransactionHistory() {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM tb_transactions WHERE fd_SenderId IN (SELECT fd_AccountId FROM tb_signup WHERE fdPinCode = ?) OR fd_RecieverId IN (SELECT fd_AccountId FROM tb_signup WHERE fdPinCode = ?) ORDER BY fd_DateTime DESC";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, userPin);
                    pstmt.setString(2, userPin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        StringBuilder history = new StringBuilder();
                        while (rs.next()) {
                            history.append("Sender ID: ").append(rs.getString("fd_SenderId"))
                                    .append(", Receiver ID: ").append(rs.getString("fd_RecieverId"))
                                    .append(", Amount: ").append(formatCurrency(rs.getDouble("fd_Amount")))
                                    .append(", Date: ").append(rs.getTimestamp("fd_DateTime"))
                                    .append("\n");
                        }
                        historyArea.setText(history.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String formatCurrency(double amount) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            Currency currency = Currency.getInstance("LKR");
            currencyFormat.setCurrency(currency);
            return currencyFormat.format(amount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}