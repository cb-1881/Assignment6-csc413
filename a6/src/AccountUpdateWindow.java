import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AccountUpdateWindow extends JFrame {
    private JTextField balanceField, acctTypeField, odLimitField, intRateField;
    private JButton saveButton;
    private BankAccount account;
    private CustomerDAO dao;

    public AccountUpdateWindow(BankAccount account, CustomerDAO dao) {
        this.account = account;
        this.dao = dao;
        initializeUI();
        setValues();
        setupListeners();
    }

    private void initializeUI() {
        setTitle("Update Bank Account");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Balance:"));
        balanceField = new JTextField(10);
        add(balanceField);

        add(new JLabel("Account Type:"));
        acctTypeField = new JTextField(10);
        add(acctTypeField);

        add(new JLabel("Overdraft Limit:"));
        odLimitField = new JTextField(10);
        add(odLimitField);

        add(new JLabel("Interest Rate (%):"));
        intRateField = new JTextField(10);
        add(intRateField);

        saveButton = new JButton("Save Changes");
        add(saveButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setValues() {
        balanceField.setText(String.valueOf(account.getBalance()));
        acctTypeField.setText(account.getAccountType());
        odLimitField.setText(String.valueOf(account.getOverdraftLimit()));
        intRateField.setText(String.valueOf(account.getInterestRate()));
    }

    private void setupListeners() {
        saveButton.addActionListener(this::updateAccount);
    }

    private void updateAccount(ActionEvent e) {
        try {
            account.setBalance(Double.parseDouble(balanceField.getText()));
            account.setAccountType(acctTypeField.getText());
            account.setOverdraftLimit(Float.parseFloat(odLimitField.getText()));
            account.setInterestRate(Float.parseFloat(intRateField.getText()));

            dao.updateBankAccount(account);
            JOptionPane.showMessageDialog(this, "Account updated successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();  // Close the window after saving changes
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating account: " + ex.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
