import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AccountList extends JFrame {
    private JList<BankAccount> accountList;
    private DefaultListModel<BankAccount> listModel;
    private JTextArea accountDetailsArea;
    private JButton showDetailsButton, updateButton, showTransactionsButton;

    public AccountList(int customerId) {
        listModel = new DefaultListModel<>();
        accountList = new JList<>(listModel);
        accountDetailsArea = new JTextArea(10, 30);
        accountDetailsArea.setEditable(false);
        showDetailsButton = new JButton("Show Details");
        updateButton = new JButton("Update");
        showTransactionsButton = new JButton("Show Transactions");  // Create the new button
        loadBankAccounts(customerId);
        setupUI();
    }

    private void setupUI() {
        setTitle("Bank Account List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
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
    
        showDetailsButton.setEnabled(false);
        updateButton.setEnabled(false);
        showTransactionsButton.setEnabled(false);  // Initially disable the button
    
        accountList.addListSelectionListener(e -> {
            boolean notEmpty = !accountList.isSelectionEmpty();
            showDetailsButton.setEnabled(notEmpty);
            updateButton.setEnabled(notEmpty);
            showTransactionsButton.setEnabled(notEmpty);  // Enable the button when an account is selected
        });
    
        showDetailsButton.addActionListener(e -> displayAccountDetails());
        updateButton.addActionListener(e -> openUpdateWindow());
        showTransactionsButton.addActionListener(e -> openTransactionsWindow());  // Add listener to handle click
    
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.add(new JScrollPane(accountList), BorderLayout.CENTER);
        detailsPanel.add(new JScrollPane(accountDetailsArea), BorderLayout.SOUTH);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showDetailsButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(showTransactionsButton);  // Add the new button to the panel
    
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(detailsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openUpdateWindow() {
        if (!accountList.isSelectionEmpty()) {
            BankAccount selectedAccount = accountList.getSelectedValue();
            AccountUpdateWindow updateWindow = new AccountUpdateWindow(selectedAccount, new CustomerDAO());
            updateWindow.setVisible(true);
        }
    }

    private void openTransactionsWindow() {
        if (!accountList.isSelectionEmpty()) {
            BankAccount selectedAccount = accountList.getSelectedValue();
            TransactionList transactionWindow = new TransactionList(selectedAccount.getAccountNumber());
            transactionWindow.setVisible(true);
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
