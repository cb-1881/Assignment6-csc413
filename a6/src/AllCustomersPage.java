import javax.swing.*;
import java.awt.*;
import java.util.List;
public class AllCustomersPage extends JFrame {
    private JList<Customer> customerList;
    private DefaultListModel<Customer> listModel;
    private JTextArea detailsArea;
    private JButton btnDetails, btnAccountDetails;

    public AllCustomersPage() {
        setTitle("All Customers");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        customerList = new JList<>(listModel);
        detailsArea = new JTextArea(10, 50);
        detailsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        btnDetails = new JButton("Details");
        btnAccountDetails = new JButton("Account Details");
        bottomPanel.add(btnDetails);
        bottomPanel.add(btnAccountDetails);
        add(bottomPanel, BorderLayout.SOUTH);

        loadCustomers();

        btnDetails.addActionListener(e -> showDetails());
        btnAccountDetails.addActionListener(e -> showAccountDetails());
    }

    private void loadCustomers() {
        CustomerDAO dao = new CustomerDAO();
        List<Customer> customers = dao.getAllCustomers();
        for (Customer customer : customers) {
            listModel.addElement(customer);
        }
    }

    private void showDetails() {
        Customer selectedCustomer = customerList.getSelectedValue();
        if (selectedCustomer != null) {
            detailsArea.setText("Customer ID: " + selectedCustomer.getId() +
                                "\nName: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() +
                                "\nEmail: " + selectedCustomer.getEmail());
        }
    }

    private void showAccountDetails() {
        Customer selectedCustomer = customerList.getSelectedValue();
        if (selectedCustomer != null) {
            JFrame frame = new AccountList(Integer.parseInt(selectedCustomer.getId()));
            frame.setVisible(true);
        }
    }
}
