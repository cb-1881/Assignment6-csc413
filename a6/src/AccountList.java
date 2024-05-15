import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AccountList extends JFrame {
    private JList<BankAccount> accountList;
    private DefaultListModel<BankAccount> listModel;
    private JTextArea accountDetailsArea;
    private JButton showDetailsButton;
    private JButton updateButton;

    public AccountList(int customerId) {
        listModel = new DefaultListModel<>();
        accountList = new JList<>(listModel);
        accountDetailsArea = new JTextArea(10, 30);
        accountDetailsArea.setEditable(false);
        showDetailsButton = new JButton("Show Details");
        loadBankAccounts(customerId);
        setupUI();
    }

    private void setupUI() {
        setTitle("Bank Account List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        // Setup account list and its cell renderer
        accountList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BankAccount) {
                    BankAccount account = (BankAccount) value;
                    setText("Account #" + account.getAccountNumber() + " - Balance: $" + account.getBalance());
                }
                return this;
            }
        });
    
        // Initialize buttons and their states
        showDetailsButton = new JButton("Show Details");
        showDetailsButton.setEnabled(false);
        updateButton = new JButton("Update");
        updateButton.setEnabled(false);
    
        // List selection listeners
        accountList.addListSelectionListener(e -> {
            boolean notEmpty = !accountList.isSelectionEmpty();
            showDetailsButton.setEnabled(notEmpty);
            updateButton.setEnabled(notEmpty);
        });
    
        // Button action listeners
        showDetailsButton.addActionListener(e -> displayAccountDetails());
        updateButton.addActionListener(e -> openUpdateWindow());
    
        // Detail panel for displaying account information
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(new JScrollPane(accountList), BorderLayout.CENTER);
        accountDetailsArea = new JTextArea(10, 40);
        accountDetailsArea.setEditable(false);
        detailsPanel.add(new JScrollPane(accountDetailsArea), BorderLayout.SOUTH);
    
        // Button panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showDetailsButton);
        buttonPanel.add(updateButton);  // Add the update button next to the show details button
    
        // Adding components to the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(detailsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // Method to open the update window
    private void openUpdateWindow() {
        if (!accountList.isSelectionEmpty()) {
            BankAccount selectedAccount = accountList.getSelectedValue();
            AccountUpdateWindow updateWindow = new AccountUpdateWindow(selectedAccount, new CustomerDAO());
            updateWindow.setVisible(true);
        }
    }
    

    private void loadBankAccounts(int customerId) {
        CustomerDAO dao = new CustomerDAO();
        List<BankAccount> accounts = dao.findBankAccountsByCustomerId(customerId);
        for (BankAccount account : accounts) {
            listModel.addElement(account);
        }
    }

    private void displayAccountDetails() {
        BankAccount selectedAccount = accountList.getSelectedValue();
        if (selectedAccount != null) {
            accountDetailsArea.setText("Account Number: " + selectedAccount.getAccountNumber() +
                                       "\nCustomer ID: " + selectedAccount.getCustomerId() +
                                       "\nBalance: $" + selectedAccount.getBalance() +
                                       "\nCreated On: " + selectedAccount.getCreateDate() +
                                       "\nLast Updated: " + selectedAccount.getLastUpdateDate() +
                                       "\nAccount Type: " + selectedAccount.getAccountType() +
                                       "\nOverdraft Limit: $" + selectedAccount.getOverdraftLimit() +
                                       "\nInterest Rate: " + selectedAccount.getInterestRate() + "%");
        }
    }



}




/*
 * 
    private void loadBankAccounts(int customerId) {
        CustomerDAO dao = new CustomerDAO();
        List<BankAccount> accounts = dao.findBankAccountsByCustomerId(customerId);
        for (BankAccount account : accounts) {
            listModel.addElement(account);
        }
    }
 * 
 */