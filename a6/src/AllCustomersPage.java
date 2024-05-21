import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AllCustomersPage extends JFrame {
    private JList<CustomerDTO> customerList;  // Changed to CustomerDTO
    private DefaultListModel<CustomerDTO> listModel;
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
        List<CustomerDTO> customers = dao.getAllCustomers();
        for (CustomerDTO customer : customers) {
            listModel.addElement(customer);
        }
    }
    

    private void showDetails() {
        CustomerDTO selectedCustomer = customerList.getSelectedValue();
        if (selectedCustomer != null && selectedCustomer.getAddress() != null) {
            detailsArea.setText("Customer ID: " + selectedCustomer.getId() + "\n" +
                                "Name: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + "\n" +
                                "Email: " + selectedCustomer.getEmail() + "\n" +
                                "Phone: " + selectedCustomer.getPhone() + "\n" +
                                "Sex: " + selectedCustomer.getSex() + "\n" +
                                "Birthday: " + selectedCustomer.getBirthday() + "\n" +
                                "Address: " + selectedCustomer.getAddress().getStreetName() + ", " +
                                selectedCustomer.getAddress().getCity() + ", " +
                                selectedCustomer.getAddress().getState() + " " +
                                selectedCustomer.getAddress().getZip());
            JOptionPane.showMessageDialog(this, detailsArea.getText(), "Customer Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No customer selected or no address information available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAccountDetails() {
        CustomerDTO selectedCustomer = customerList.getSelectedValue();
        if (selectedCustomer != null) {
            JFrame frame = new AccountList(selectedCustomer.getId());  // Ensure AccountList accepts IDs as used here
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
