package OracleConnection;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AdminDashboard {

    private JFrame frame;
    private JPanel mainPanel,logOutPanel;
    private Font f1, f2;
    private JTabbedPane tabbedPane;


    public AdminDashboard(JFrame frame) {
        this.frame = frame;
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x7E0AB5));

        f1 = new Font("Arial",Font.PLAIN, 20);
        f2 = new Font("Arial", Font.BOLD, 11);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        int xsize = (int) toolkit.getScreenSize().getWidth();
        int ysize = (int) toolkit.getScreenSize().getHeight();
        frame.setSize(xsize, ysize);


        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, xsize, ysize);
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        tabbedPane.setFont(f1);
        mainPanel.add(tabbedPane);

        Inventory inventory = new Inventory(frame);
        Paybills paybills = new Paybills(frame);
        Report report = new Report(frame);
        Salary salary =new Salary(frame);
        final Register register=new Register(frame);
        logOutPanel= new JPanel();
        logOutPanel.setLayout(null);
        logOutPanel.setBackground(new Color(0xA66BD4));



        tabbedPane.addTab("Inventory", inventory.initComponents(mainPanel));
        tabbedPane.addTab("PayBills", paybills.initComponents(mainPanel));
        tabbedPane.addTab("Salary", salary.initComponents());
        tabbedPane.addTab("Report", report.initComponents());
        tabbedPane.addTab("Register", register.initComponents());
        tabbedPane.addTab("Logout", logOutPanel);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane.getSelectedIndex()==4){
                    register.chooseDesignation();
                }
                else if(tabbedPane.getSelectedIndex()==5){
                    int warningMsg = JOptionPane.showConfirmDialog(frame, "Do you want to Logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if(warningMsg==JOptionPane.YES_OPTION){
                        new LoginPage(frame);
                        mainPanel.setVisible(false);
                    }
                }
            }
        });

        frame.add(mainPanel);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Inventory Management");

    }
}