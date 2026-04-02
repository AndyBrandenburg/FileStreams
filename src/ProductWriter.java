import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter extends JFrame{
    JPanel titlePnl = new JPanel();
    JPanel infoPnl = new JPanel();
    JPanel buttonPnl = new JPanel();
    JTextField nameTF = new JTextField(10);
    JTextField descriptionTF = new JTextField(10);
    JTextField idTF = new JTextField(10);
    JTextField costTF = new JTextField(10);
    JTextArea resultsTA = new JTextArea(10,40);
    JTextField recordCountTF = new JTextField(10);

    int recordCount = 0;
    public ProductWriter(){
        setTitle("Product Writer");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Title Panel//
        JLabel titleLbl = new JLabel("Enter your product information");
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setFont(titleLbl.getFont().deriveFont(24f));
        titlePnl.add(titleLbl);
        add(titlePnl, BorderLayout.NORTH);

        //Info Panel//
        infoPnl.add(new JLabel("ID:"));
        infoPnl.add(idTF);
        infoPnl.setLayout(new GridLayout(5,2));
        infoPnl.add(new JLabel("Name:"));
        infoPnl.add(nameTF);
        infoPnl.add(new JLabel("Description:"));
        infoPnl.add(descriptionTF);
        infoPnl.add(new JLabel("Cost:"));
        infoPnl.add(costTF);
        infoPnl.add(new JLabel("Record Count:"));
        infoPnl.add(recordCountTF);
        recordCountTF.setEditable(false);

        //Results Panel//
        JPanel resultsPnl = new JPanel();
        resultsTA.setEditable(false);
        JScrollPane resultsSP = new JScrollPane(resultsTA);
        resultsPnl.add(resultsSP);

        //Center Panel//
        JPanel CenterPnl = new JPanel();
        CenterPnl.setLayout(new BorderLayout());
        CenterPnl.add(infoPnl, BorderLayout.NORTH);
        CenterPnl.add(resultsPnl, BorderLayout.CENTER);
        add(CenterPnl, BorderLayout.CENTER);

        //Button Panel//
        buttonPnl.setLayout(new GridLayout(1,2));
        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            String id = idTF.getText();
            String name = nameTF.getText();
            String description = descriptionTF.getText();
            double cost = Double.parseDouble(costTF.getText());

            Product product = new Product(id, name, description, cost);

            try {
                RandomAccessFile raf = new RandomAccessFile("products.bin", "rw");
                raf.seek(raf.length());
                raf.writeChars(product.getPaddedID());
                raf.writeChars(product.getPaddedName());
                raf.writeChars(product.getPaddedDescription());
                raf.writeDouble(product.getCost());
                raf.close();
                resultsTA.setText("Item Added!");
            } catch (IOException ex) {
                ex.printStackTrace();
                resultsTA.setText("Error writing to file.");
            }
            recordCount++;
            recordCountTF.setText(String.valueOf(recordCount));
            idTF.setText("");
            nameTF.setText("");
            descriptionTF.setText("");
            costTF.setText("");

        });
        buttonPnl.add(submitBtn);
        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        buttonPnl.add(quitBtn);
        add(buttonPnl, BorderLayout.SOUTH);


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductWriter pw = new ProductWriter();
            pw.setVisible(true);
        });

    }

}
