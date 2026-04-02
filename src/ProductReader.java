import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader extends JFrame {
private static final int ID_SIZE = Product.ID_LENGTH;
private static final int NAME_SIZE = Product.NAME_LENGTH;
private static final int DESCRIPTION_SIZE = Product.DESCRIPTION_LENGTH;
private static final int RECORD_SIZE = (ID_SIZE + NAME_SIZE + DESCRIPTION_SIZE) * 2 + 8;
private File selectedFile = null;


    public ProductReader() {
        setTitle("Product Reader");
        setSize(1200,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Title Panel//
        JLabel titleLbl = new JLabel("Read Your Product Info Here!");
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setFont(titleLbl.getFont().deriveFont(24f));
        JPanel titlePnl = new JPanel();
        titlePnl.add(titleLbl);
        add(titlePnl, BorderLayout.NORTH);

        //Search Panel//
        JPanel searchPnl = new JPanel();
        JLabel searchLbl = new JLabel("Search:");
        JTextField searchTF = new JTextField(20);
        searchPnl.add(searchLbl);
        searchPnl.add(searchTF);
        //Display Panel//
        JTextArea displayTA = new JTextArea(30,100);
        displayTA.setEditable(false);
        JScrollPane displaySP = new JScrollPane(displayTA);
        JPanel displayPnl = new JPanel();
        displayPnl.add(displaySP);

        //Center Panel//
        JPanel CenterPnl = new JPanel();
        CenterPnl.setLayout(new BorderLayout());
        CenterPnl.add(searchPnl, BorderLayout.NORTH);
        CenterPnl.add(displayPnl, BorderLayout.CENTER);
        add(CenterPnl, BorderLayout.CENTER);


        //Button Panel//
        JPanel buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 3));
        JButton chooseBtn = new JButton("Choose File");
        JButton readBtn = new JButton("Read Product File");
        readBtn.setEnabled(false);

        chooseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                displayTA.setText("Selected file: " + selectedFile.getName());
                readBtn.setEnabled(true);
            } else {
                displayTA.setText("No file selected.");
            }
        });
        buttonPnl.add(chooseBtn);

        readBtn.addActionListener(e -> {
            if (selectedFile == null) {
                displayTA.setText("Please choose a file first.");
                return;
            }

            String searchTerm = searchTF.getText().trim().toLowerCase();
            displayTA.setText("");

            if (searchTerm.isEmpty()) {
                displayTA.setText("Please enter a search term.");
                return;
            }

            try (RandomAccessFile raf = new RandomAccessFile(selectedFile, "r")) {

                long numRecords = raf.length() / RECORD_SIZE;
                boolean foundAny = false;

                for (int i = 0; i < numRecords; i++) {
                    raf.seek(i * RECORD_SIZE);

                    String id = readFixedString(raf, ID_SIZE).trim();
                    String name = readFixedString(raf, NAME_SIZE).trim();
                    String description = readFixedString(raf, DESCRIPTION_SIZE).trim();
                    double cost = raf.readDouble();

                    if (name.toLowerCase().contains(searchTerm)) {
                        foundAny = true;
                        displayTA.append(
                                String.format("ID: %s\n", id) +
                                        String.format("Name: %s\n", name) +
                                        String.format("Description: %s\n", description) +
                                        String.format("Cost: $%.2f\n\n", cost)
                        );

                    }
                }

                if (!foundAny) {
                    displayTA.setText("No matching products found.");
                }

            } catch (IOException ex) {
                displayTA.setText("Error reading file.");
                ex.printStackTrace();
            }


        });
        buttonPnl.add(readBtn);
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
    private String readFixedString(RandomAccessFile raf, int size) throws IOException {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductReader pr = new ProductReader();
            pr.setVisible(true);
        });
    }


}
