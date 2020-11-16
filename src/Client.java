/*
Filename: Client.java         
Author: Kaylin Moodley
Created: 21/09/2020
Operating System: Windows 10
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame implements ActionListener
{
    //Variable Declaration
    JPanel panel;
    JLabel lblAppName, lblDev, lblNum1,lblNum2,lblSymbol,lblAnswer;
    JTextField txtNum1, txtNum2;
    JComboBox cmbSymbol;
    JButton btnCalculate;
    String[] symbols ={"+","-","*","/"};
    DefaultListCellRenderer dlcr ; 
    String num1,num2,symbol;
    
    //Constructor
    Client()
    {
        lblAppName = new JLabel();
        lblAppName.setText("Edureka Calculator App v1.0");
        lblDev = new JLabel();
        lblDev.setText("Developer: Kaylin Moodley");
        lblNum1 = new JLabel();
        lblNum1.setText("Input 1st Number:");
        txtNum1 = new JTextField();
        txtNum1.setHorizontalAlignment(JTextField.CENTER);
        lblSymbol = new JLabel();
        lblSymbol.setText("Choose Symbol:");
        cmbSymbol= new JComboBox(symbols);
        dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
        cmbSymbol.setRenderer(dlcr);         
        lblNum2 = new JLabel();
        lblNum2.setText("Input 2nd Number:");
        txtNum2 = new JTextField("",SwingConstants.CENTER);
        txtNum2.setHorizontalAlignment(JTextField.CENTER);
        btnCalculate= new JButton("Calculate");
        lblAnswer = new JLabel("", SwingConstants.CENTER);
        
        panel = new JPanel(new GridLayout(0, 2));
        panel.add(lblAppName);
        panel.add(lblDev);
        panel.add(lblNum1);
        panel.add(txtNum1);
        panel.add(lblSymbol);
        panel.add(cmbSymbol);
        panel.add(lblNum2);
        panel.add(txtNum2);
        panel.add(btnCalculate);
        panel.add(lblAnswer);
        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnCalculate.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Edureka Calculator App");
        setSize(500, 200);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String source = ae.getActionCommand();
        if (source.equals("Calculate")) 
        {
            num1 =txtNum1.getText();
            num2 =txtNum2.getText();
            symbol = cmbSymbol.getSelectedItem().toString();
            int answer;
            
            if(num1.equals("")||num2.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Please Enter Numbers first","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try 
                {
                     Registry registry = LocateRegistry.getRegistry(8080);
                     Calculate stub = (Calculate) registry.lookup("Calculate");
                     if(symbol.equals("+"))
                     {
                         answer = stub.add(Integer.parseInt(num1),Integer.parseInt(num2));
                     }
                     else if(symbol.equals("-"))
                     {
                         answer = stub.subtract(Integer.parseInt(num1),Integer.parseInt(num2));
                     }
                     else if(symbol.equals("*"))
                     {
                         answer = stub.multiply(Integer.parseInt(num1),Integer.parseInt(num2));
                     }
                     else
                     {
                        answer = stub.divide(Integer.parseInt(num1),Integer.parseInt(num2)); 
                     }
                     
                     lblAnswer.setText(String.valueOf(answer));
                            
                     
                     
                }catch (Exception e) 
                {
                    System.out.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            }  
            
        }
    }
    
        public static void main(String[] args) {
        new Client();
    }
}
