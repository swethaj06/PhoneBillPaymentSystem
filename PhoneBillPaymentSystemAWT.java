import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PhoneBillPaymentSystemAWT extends Frame implements ActionListener {
    // Components for the phone bill system
    Label lblTitle, lblName, lblPhoneNumber, lblMinutesUsed, lblRatePerMinute, lblBillAmount, lblPayment;
    TextField txtName, txtPhoneNumber, txtMinutesUsed, txtRatePerMinute, txtBillAmount, txtPayment;
    Button btnGenerateBill, btnMakePayment, btnClear, btnSignUp, btnLogin;

    // User details display
    TextArea txtUserDetails;

    // Login/SignUp components
    Label lblUsername, lblPassword;
    TextField txtUsername;
    TextField txtPassword;

    // Variables for managing users and bill amount
    HashMap<String, String> users = new HashMap<>();
    double amountDue = 0;

    String currentUsername = "";

    public PhoneBillPaymentSystemAWT() {
        showLoginPage();

        // Frame close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void showLoginPage() {
        clearAllComponents();
        
        setTitle("Phone Bill Payment System - Login");
        setSize(400, 300);
        setLayout(null);

        lblTitle = new Label("Login to Your Account", Label.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(50, 50, 300, 30);

        lblUsername = new Label("Username:");
        lblUsername.setBounds(50, 100, 100, 20);
        txtUsername = new TextField();
        txtUsername.setBounds(150, 100, 200, 20);

        lblPassword = new Label("Password:");
        lblPassword.setBounds(50, 130, 100, 20);
        txtPassword = new TextField();
        txtPassword.setBounds(150, 130, 200, 20);
        txtPassword.setEchoChar('*');

        btnLogin = new Button("Login");
        btnLogin.setBounds(150, 200, 100, 30);
        btnLogin.addActionListener(this);

        btnSignUp = new Button("Sign Up");
        btnSignUp.setBounds(260, 200, 100, 30);
        btnSignUp.addActionListener(this);

        add(lblTitle);
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnSignUp);

        setVisible(true);
    }

    private void showPhoneBillSystem() {
        clearAllComponents();

        setTitle("Phone Bill Payment System");
        setSize(600, 450);
        setLayout(null);

        // Title
        lblTitle = new Label("Phone Bill Payment System", Label.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 50, 400, 30);

        // Input fields
        lblName = new Label("Customer Name:");
        lblName.setBounds(50, 100, 120, 20);
        txtName = new TextField();
        txtName.setBounds(180, 100, 200, 20);

        lblPhoneNumber = new Label("Phone Number:");
        lblPhoneNumber.setBounds(50, 130, 120, 20);
        txtPhoneNumber = new TextField();
        txtPhoneNumber.setBounds(180, 130, 200, 20);

        lblMinutesUsed = new Label("Minutes Used:");
        lblMinutesUsed.setBounds(50, 160, 120, 20);
        txtMinutesUsed = new TextField();
        txtMinutesUsed.setBounds(180, 160, 200, 20);

        lblRatePerMinute = new Label("Rate per Minute:");
        lblRatePerMinute.setBounds(50, 190, 120, 20);
        txtRatePerMinute = new TextField();
        txtRatePerMinute.setBounds(180, 190, 200, 20);

        lblBillAmount = new Label("Total Bill:");
        lblBillAmount.setBounds(50, 220, 120, 20);
        txtBillAmount = new TextField();
        txtBillAmount.setBounds(180, 220, 200, 20);
        txtBillAmount.setEditable(false);

        lblPayment = new Label("Enter Payment:");
        lblPayment.setBounds(50, 250, 120, 20);
        txtPayment = new TextField();
        txtPayment.setBounds(180, 250, 200, 20);

        // User details display area
        txtUserDetails = new TextArea();
        txtUserDetails.setBounds(400, 100, 180, 250);
        txtUserDetails.setEditable(false);
        txtUserDetails.setFont(new Font("Arial", Font.PLAIN, 12));

        // Buttons
        btnGenerateBill = new Button("Generate Bill");
        btnGenerateBill.setBounds(100, 290, 100, 30);
        btnGenerateBill.addActionListener(this);

        btnMakePayment = new Button("Make Payment");
        btnMakePayment.setBounds(210, 290, 100, 30);
        btnMakePayment.addActionListener(this);

        btnClear = new Button("Clear");
        btnClear.setBounds(320, 290, 100, 30);
        btnClear.addActionListener(this);

        // Add components to frame
        add(lblTitle);
        add(lblName);
        add(txtName);
        add(lblPhoneNumber);
        add(txtPhoneNumber);
        add(lblMinutesUsed);
        add(txtMinutesUsed);
        add(lblRatePerMinute);
        add(txtRatePerMinute);
        add(lblBillAmount);
        add(txtBillAmount);
        add(lblPayment);
        add(txtPayment);
        add(txtUserDetails);
        add(btnGenerateBill);
        add(btnMakePayment);
        add(btnClear);

        setVisible(true);
    }

    private void clearAllComponents() {
        // Remove all components before showing a new page
        for (Component comp : getComponents()) {
            remove(comp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLogin) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUsername = username;
                showSuccessDialog("Login successful!");
                clearLoginFields();
                showPhoneBillSystem();
            } else {
                showErrorDialog("Invalid username or password!");
            }
        } else if (ae.getSource() == btnSignUp) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Please fill all fields!");
            } else if (users.containsKey(username)) {
                showErrorDialog("Username already exists! Please log in.");
            } else {
                users.put(username, password);
                showSuccessDialog("Sign-up successful! You can now log in.");
                clearLoginFields();
            }
        } else if (ae.getSource() == btnGenerateBill) {
            try {
                String name = txtName.getText();
                String phoneNumber = txtPhoneNumber.getText();
                int minutes = Integer.parseInt(txtMinutesUsed.getText());
                double rate = Double.parseDouble(txtRatePerMinute.getText());
                amountDue = minutes * rate;
                txtBillAmount.setText(String.format("%.2f", amountDue));
                txtUserDetails.setText("Customer Name: " + name + "\nPhone Number: " + phoneNumber +
                        "\nMinutes Used: " + minutes + "\nAmount Due: $" + String.format("%.2f", amountDue));
                showSuccessDialog("Bill generated successfully.");
            } catch (NumberFormatException e) {
                txtBillAmount.setText("");
                showErrorDialog("Please enter valid input!");
            }
        } else if (ae.getSource() == btnMakePayment) {
            try {
                double payment = Double.parseDouble(txtPayment.getText());
                if (payment >= amountDue) {
                    amountDue = 0;
                    showSuccessDialog("Payment successful! No outstanding amount.");
                } else {
                    amountDue -= payment;
                    showSuccessDialog("Partial payment received. Remaining amount due: $" + String.format("%.2f", amountDue));
                }
                txtBillAmount.setText(String.format("%.2f", amountDue));
                // Update user details area
                txtUserDetails.append("\nPayment Amount: $" + payment + "\nRemaining Amount Due: $" + String.format("%.2f", amountDue));
            } catch (NumberFormatException e) {
                showErrorDialog("Please enter valid payment amount!");
            }
        } else if (ae.getSource() == btnClear) {
            txtName.setText("");
            txtPhoneNumber.setText("");
            txtMinutesUsed.setText("");
            txtRatePerMinute.setText("");
            txtBillAmount.setText("");
            txtPayment.setText("");
            txtUserDetails.setText("");
            amountDue = 0;
        }
    }

    private void clearLoginFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void showSuccessDialog(String message) {
        Dialog dialog = new Dialog(this, "Success", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 150);
        Label lblMessage = new Label(message);
        Button btnClose = new Button("OK");
        btnClose.addActionListener(e -> dialog.dispose());
        dialog.add(lblMessage);
        dialog.add(btnClose);
        dialog.setVisible(true);
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog(this, "Error", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 150);
        Label lblMessage = new Label(message);
        Button btnClose = new Button("OK");
        btnClose.addActionListener(e -> dialog.dispose());
        dialog.add(lblMessage);
        dialog.add(btnClose);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new PhoneBillPaymentSystemAWT();
    }
}
