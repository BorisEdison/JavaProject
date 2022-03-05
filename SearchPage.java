import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.awt.Color;

public class SearchPage extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel nameL, numL, titlenameL, titlenumL, nameMsgL, numMsgL;
    JTextField numTF, nameTF;
    JButton snameBtn,snumBtn, exitBtn, backBtn;

    SearchPage(){
        setTitle("Search Page");
        setSize(300, 355);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // nameMsg
        nameMsgL = new JLabel("");
        nameMsgL.setForeground(Color.RED);
        nameMsgL.setBounds(30, 130, 250, 20);
        add(nameMsgL);

        // Name Search
        titlenameL = new JLabel("To Search by Name");
        titlenameL.setBounds(30,40,150,20);
        add(titlenameL);

        nameL = new JLabel("Enter Name");
        nameL.setBounds(30,70,100,20);
        add(nameL);

        nameTF = new JTextField();
        nameTF.setBounds(120,70,100,20);
        add(nameTF);

        // Search button
        snameBtn = new JButton("Search");
        snameBtn.setBounds(130,105,80,20);
        add(snameBtn);
		snameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(e.getSource() == snameBtn){
                    String name = nameTF.getText();
                    if(!(name.isEmpty())){
                        numMsgL.setText("");
                        nameMsgL.setText("");
                        boolean alphabetCheck = name.matches("[a-zA-Z]+");
                        if(alphabetCheck){
                            numMsgL.setText("");
                            nameMsgL.setText("");
                            File dirPath = new File(".");
                            File filesList[] = dirPath.listFiles((d, fname) -> fname.endsWith(".txt")); 
                            // ^ java Lambda func. Similar to JS ES6 anonymous arrow funcs      
                            String searchTerm = name;
                            ArrayList<String> dataList = new ArrayList<String>();
                            for(File fileName: filesList){
                                try {
                                    Scanner fsc = new Scanner(fileName);
                                    String data = "";
                                    int counter = 0;
                                    while(counter<3){
                                        data = data.concat("\n"+fsc.nextLine());
                                        counter++;
                                    }
                                    if(data.contains(searchTerm)){
                                        dataList.add(data);
                                    }
                                    fsc.close();
                                } catch (Exception err) {
                                    System.out.println(err);
                                }
                            }
                            if (dataList.isEmpty()) {
                                JOptionPane.showMessageDialog(frame,"No Application Found with Specified name");

                            } else {
                            JOptionPane.showMessageDialog(frame,dataList +" Application found");

                            }
                        }
                        else{
                            numMsgL.setText("");
                            nameMsgL.setText("                     *Enter Correct Name");
                        }
                    } else{
                        numMsgL.setText("");
                        nameMsgL.setText("                            *Enter Name");
                    }
                }
			}
		});
        
        // numMsg
        numMsgL = new JLabel("");
        numMsgL.setForeground(Color.RED);
        numMsgL.setBounds(30, 250, 250, 20);
        add(numMsgL);

        // Number Search
        titlenumL = new JLabel("To Search by Number");
        titlenumL.setBounds(30,160,150,20);
        add(titlenumL);

        numL = new JLabel("Enter Number");
        numL.setBounds(30,190,100,20);
        add(numL);

        numTF = new JTextField();
        numTF.setBounds(120,190,100,20);
        add(numTF);

        // Search button
        snumBtn = new JButton("Search");
        snumBtn.setBounds(130,225,80,20);
        add(snumBtn);
		snumBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                if(e.getSource() == snumBtn){
                    String num = numTF.getText();
                    if (!(num.isEmpty())){
                        numMsgL.setText("");
                        nameMsgL.setText("");
                        if(num.matches("[0-9]+")){
                            numMsgL.setText("");
                            nameMsgL.setText("");
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
                            if(numberFile.exists()){
                                JOptionPane.showMessageDialog(frame,numData);
                            }      
                            else{
                                JOptionPane.showMessageDialog(frame,"No Application Found with Specified number");                       
                            }
                        } else {
                            nameMsgL.setText("");
                            numMsgL.setText("                   *Enter Correct Number");
                        }
                    } else {
                        nameMsgL.setText("");
                        numMsgL.setText("                          *Enter Number");
                    }
                }
			}
		});
        backBtn = new JButton("Back");
        backBtn.setBounds(140, 285, 65, 20);
        add(backBtn);
        backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Form fPage = new Form();
				fPage.setVisible(true);
                setVisible(false);
			}
		});

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(210, 285, 60, 20);
        add(exitBtn);
        exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        setVisible(true);
    };

    public void actionPerformed(ActionEvent e){
    }
    public static void main(String[] args) {
        new SearchPage();
    }
}