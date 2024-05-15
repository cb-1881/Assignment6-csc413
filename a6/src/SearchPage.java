import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchPage extends JFrame {
    private JTextField cityField;
    private JButton searchButton, detailsButton;
    private JList<CustomerDTO> resultList;
    private JTextArea resultsArea;
    private DefaultListModel<CustomerDTO> listModel;

    public SearchPage() {
        createView();
        setTitle("Customer Search by City");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Increased size for better layout
        setLocationRelativeTo(null); // Center the frame
    }

    private void createView() {
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        JPanel inputPanel = new JPanel();
        cityField = new JTextField(20);
        cityField.setText("Please enter a city");
        searchButton = new JButton("Search");
        detailsButton = new JButton("Details");
        detailsButton.setEnabled(false); // Disable until a selection is made

        inputPanel.add(cityField);
        inputPanel.add(searchButton);
        inputPanel.add(detailsButton);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.addListSelectionListener(e -> detailsButton.setEnabled(!resultList.isSelectionEmpty()));

        resultsArea = new JTextArea(10, 40);
        resultsArea.setEditable(false);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultList), BorderLayout.CENTER);
        panel.add(new JScrollPane(resultsArea), BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
        detailsButton.addActionListener(e -> showDetails());
    }

    private void performSearch() {
        String city = cityField.getText().trim();
        if (!city.isEmpty()) {
            try {
                CustomerDAO customerDao = new CustomerDAO();
                List<CustomerDTO> customers = customerDao.findCustomersByCity(city);
                listModel.removeAllElements(); // Clear previous results
                for (CustomerDTO customer : customers) {
                    listModel.addElement(customer);  // Will use toString() for display
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error searching for customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a city name to search.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void showDetails() {
        CustomerDTO selectedCustomer = resultList.getSelectedValue();
        if (selectedCustomer != null) {
            resultsArea.setText("Customer ID: " + selectedCustomer.getId() + "\n" +
                                "Name: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + "\n" +
                                "Email: " + selectedCustomer.getEmail() + "\n" +
                                "Phone: " + selectedCustomer.getPhone() + "\n" +
                                "Sex: " + selectedCustomer.getSex() + "\n" +
                                "Birthday: " + selectedCustomer.getBirthday() + "\n");
                               
        }
    }

    // Include your existing findCustomersByCity method here with necessary adjustments to fit into this class.

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new SearchPage().setVisible(true);
    //         }
    //     });
    // }
}
