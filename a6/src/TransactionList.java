import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransactionList extends JFrame {
    private JList<Transaction> transactionList;
    private DefaultListModel<Transaction> listModel;

    public TransactionList(int accountId) {
        listModel = new DefaultListModel<>();
        transactionList = new JList<>(listModel);
        loadTransactions(accountId);
        setupUI(accountId);
    }

    private void setupUI(int accountId) {
        setTitle("Transactions for Account #" + accountId);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Transaction) {
                    Transaction transaction = (Transaction) value;
                    setText(formatTransactionDetails(transaction));
                }
                return this;
            }
        });
        add(new JScrollPane(transactionList), BorderLayout.CENTER);
    }

    private String formatTransactionDetails(Transaction transaction) {
        return String.format("Date: %s - Amount: $%.2f - Type: %s - Summary: %s",
                             transaction.getCreateDate(),
                             transaction.getAmount(),
                             transaction.getTranType(),
                             transaction.getSummary());
    }

    private void loadTransactions(int accountId) {
        CustomerDAO dao = new CustomerDAO();
        List<Transaction> transactions = dao.getTransactionsByAccountId(accountId);
        transactions.forEach(listModel::addElement);
    }
}
