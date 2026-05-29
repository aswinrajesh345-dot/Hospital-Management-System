import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class HospitalSystem extends JFrame {
    JTextField nameField = new JTextField(15);
    JTextField ageField = new JTextField(5);
    JTextField diseaseField = new JTextField(15);
    JTextArea displayArea = new JTextArea(10, 30);

    public HospitalSystem() {
        setTitle("Hospital Management System");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Disease:"));
        add(diseaseField);

        JButton addButton = new JButton("Add Patient");
        add(addButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> addPatient());

        setSize(400, 350);
        setVisible(true);
        refreshList();
    }

    private void addPatient() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO patients (name, age, disease) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nameField.getText());
            pstmt.setInt(2, Integer.parseInt(ageField.getText()));
            pstmt.setString(3, diseaseField.getText());
            pstmt.executeUpdate();
            refreshList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void refreshList() {
        displayArea.setText("Patients List:\n");
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients")) {
            while (rs.next()) {
                displayArea.append(
                        rs.getString("name") + " | " + rs.getInt("age") + " | " + rs.getString("disease") + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HospitalSystem();
    }
}