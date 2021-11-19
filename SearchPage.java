import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SearchPage extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel nameL,numL;
    JTextField numTF, nameTF;
    JButton snameBtn,snumBtn;

    SearchPage(){
        setTitle("Search Page");
        setSize(300, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Name
        nameL = new JLabel("Enter Name");
        nameL.setBounds(30,50,100,20);
        add(nameL);

        nameTF = new JTextField();
        nameTF.setBounds(120,50,100,20);
        add(nameTF);

        // Search button
        snameBtn = new JButton("Search");
        snameBtn.setBounds(130,90,80,20);
        add(snameBtn);
		snameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(e.getSource() == snameBtn){
                    String name = nameTF.getText();
                    
                    File dirPath = new File(".");
                    File filesList[] = dirPath.listFiles((d, fname) -> fname.endsWith(".txt")); 
                    // ^ java Lambda func. Similar to JS ES6 anonymous arrow funcs
        
                    String searchTerm = name;
        
                    ArrayList<String> dataList = new ArrayList<String>();
                    
                    for(File fileName: filesList){
                        // String fName = fileName.getName();
                        try {
                            // File file = new File(fName);
                            Scanner fsc = new Scanner(fileName);
                            // int counter = 0;
                            String data = "";
                            // System.out.println(file.exists());
                            while(fsc.hasNextLine()){
                                data = data.concat("\n"+fsc.nextLine());
                            }
                            if(data.contains(searchTerm)){
                                // System.out.println(data);
                                dataList.add(data);
                            }
                            fsc.close();
                        } catch (Exception err) {
                            System.out.println(err);
                        }
                    }
        
                    // TODO:Handle these in GUI as well
                    if (dataList.isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"No Application Found with Specified name");

                    } else {
                        JOptionPane.showMessageDialog(frame,dataList.size() +" Application found");

                    }
                }
			}
		});
        // Number
        numL = new JLabel("Enter Number");
        numL.setBounds(30,140,100,20);
        add(numL);

        numTF = new JTextField();
        numTF.setBounds(120,140,100,20);
        add(numTF);

        // Search button
        snumBtn = new JButton("Search");
        snumBtn.setBounds(130,180,80,20);
        add(snumBtn);
		snameBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                String num = numTF.getText();
                File numberFile = new File(num+".txt");
                String numData = "";
                try {
                    Scanner fsc2 = new Scanner(numberFile);
                    if(numberFile.exists()){
                        while (fsc2.hasNextLine()) {
                            numData = numData.concat("\n" + fsc2.nextLine());
                        }
                    }
                    fsc2.close();
                } catch (Exception err) {
                    System.out.println(e);
                }
                    
                
			}

		});
    };

    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args) {
        new SearchPage();
    }
}