package OracleConnection;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Salary {
    private JFrame frame;
    private JPanel panel;
    private JLabel designationName, designationId, amount, updateLabel, addLabel, updateDesignation, updateAmount;
    private JTextField tfDesignationId, tfAmount, tfDesignationName, tfUpdateAmount;
    private Font f1,f2;
    private JButton addButton, updateButton;
    private JComboBox designationComboBox; //salary comboBox
    private JTable salaryTable;
    private DefaultTableModel salaryModel;
    private JScrollPane salaryScrollPane;

    private String[] salaryColumns = {"Id", "Designation", "Amount (taka)"};
    private String[] salaryRows = new String[3];

    public Salary(JFrame frame) {
        this.frame = frame;
    }

    public JPanel initComponents() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xD9B9F2));

        f1 = new Font("Arial",Font.BOLD,15);
        f2 = new Font("Arial",Font.BOLD,11);

        updateLabel = new JLabel("Update Salary Amount");
        updateLabel.setBounds(280, 140, 200, 60);
        updateLabel.setFont(f1);
        panel.add(updateLabel);

        updateDesignation = new JLabel("Designation : ");
        updateDesignation.setBounds(200,200,150,50);
        updateDesignation.setFont(f1);
        panel.add(updateDesignation);

        designationComboBox = new JComboBox();
        designationComboBox.setBounds(350, 210, 250, 30);
        panel.add(designationComboBox);
        designationComboBox.setEditable(false);

        updateAmount = new JLabel("Amount : ");
        updateAmount.setBounds(200,250,150,50);
        updateAmount.setFont(f1);
        panel.add(updateAmount);

        tfUpdateAmount = new JTextField();
        tfUpdateAmount.setBounds(350,260,250,30);
        tfUpdateAmount.setFont(f1);
        panel.add(tfUpdateAmount);

        updateButton =new JButton("Update"); // add an alert later
        updateButton.setBounds(390,320,90,25);
        updateButton.setBackground(new Color(0x7E0AB5));
        updateButton.setFont(f2);
        panel.add(updateButton);

        //add

        addLabel = new JLabel("Add New Designation");
        addLabel.setBounds(780, 140, 200, 60);
        addLabel.setFont(f1);
        panel.add(addLabel);

        designationId = new JLabel("Designation Id : ");
        designationId.setBounds(700,200,150,50);
        designationId.setFont(f1);
        panel.add(designationId);

        designationName = new JLabel("Designation Name : ");
        designationName.setBounds(700,250,150,50);
        designationName.setFont(f1);
        panel.add(designationName);

        amount = new JLabel("Amount : ");
        amount.setBounds(700,300,150,50);
        amount.setFont(f1);
        panel.add(amount);

        tfDesignationId = new JTextField();
        tfDesignationId.setBounds(850,210,250,30);
        tfDesignationId.setFont(f1);
        panel.add(tfDesignationId);

        tfDesignationName = new JTextField();
        tfDesignationName.setBounds(850,260,250,30);
        tfDesignationName.setFont(f1);
        panel.add(tfDesignationName);

        tfAmount = new JTextField();
        tfAmount.setBounds(850,310,250,30);
        tfAmount.setFont(f1);
        panel.add(tfAmount);

        addButton =new JButton("Save");
        addButton.setBounds(900,380,70,25);
        addButton.setBackground(new Color(0x7E0AB5));
        addButton.setFont(f2);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfDesignationId.setText("");
                tfDesignationName.setText("");
                tfAmount.setText("");
                try {
                    OracleConnection oc = new OracleConnection();

                    String sql1 = "insert into SALARY (SAL_ID, DESIGNATION, AMOUNT) values(?, ?, ?)";



                    PreparedStatement ps1 = oc.conn.prepareStatement(sql1);

                    ps1.setInt(1, Integer.parseInt(tfDesignationId.getText()));
                    ps1.setString(2, tfDesignationName.getText());
                    ps1.setInt(3, Integer.parseInt(tfAmount.getText()));
                    int x = ps1.executeUpdate();

                    tfDesignationId.setText("");
                    tfDesignationName.setText("");
                    tfAmount.setText("");
                    tfDesignationId.requestFocus();

                    if (x < 0) {

                        JOptionPane.showMessageDialog(frame, "input valid info");
                    }
                } catch (Exception d) {
                    System.out.println(d);
                }
            }
        });
        panel.add(addButton);

        frame.add(panel);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Inventory Management");

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        int xsize = (int) toolkit.getScreenSize().getWidth();
        int ysize = (int) toolkit.getScreenSize().getHeight();
        frame.setSize(xsize,ysize);


        // table

        salaryTable = new JTable();
        salaryModel = new DefaultTableModel();
        salaryScrollPane = new JScrollPane(salaryTable);
        salaryModel.setColumnIdentifiers(salaryColumns);
        salaryTable.setModel(salaryModel);
        salaryTable.setFont(f1);
        salaryTable.setBackground(Color.WHITE);
        salaryTable.setSelectionBackground(Color.GRAY);
        salaryTable.setRowHeight(30);

        salaryScrollPane.setBounds(150, 510, 1000, 300);
        panel.add(salaryScrollPane);

        return panel;

    }

    private void prodName() {
        try {
            String sql = "select * from PRODUCT";
            OracleConnection oc1=new OracleConnection();
        PreparedStatement    ps = oc1.conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
            designationComboBox.removeAllItems();
            while (rs.next()) {
                designationComboBox.addItem(new Salary.designation(rs.getString(2)));
            }


        } catch (Exception c) {
            System.out.println(c);
        }
    }

    public class designation {
        String name;

        public designation(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }


}