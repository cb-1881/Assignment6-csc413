import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AccountList extends JFrame {
    private JList<BankAccount> accountList;
    private DefaultListModel<BankAccount> listModel;
    private JTextArea accountDetailsArea;
    private JButton showDetailsButton;

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
        accountList.addListSelectionListener(e -> showDetailsButton.setEnabled(!accountList.isSelectionEmpty()));

        showDetailsButton.addActionListener(e -> displayAccountDetails());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(new JScrollPane(accountList), BorderLayout.CENTER);
        detailsPanel.add(new JScrollPane(accountDetailsArea), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showDetailsButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(detailsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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
