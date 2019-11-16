/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudpclient;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Cuomo Alessandro
 */
public class GuiUDP extends JFrame implements ActionListener {
    
    String IP_address;
    String username;
    DatagramSocket socket;
    InetAddress indirizzo;
    int porta;
    
    JFrame frame = new JFrame();
    JPanel pannello = new JPanel();

    JMenuBar barra = new JMenuBar();
    JMenu menu = new JMenu("Opzioni");
    JMenuItem item1 = new JMenuItem("Username");
    JMenuItem item2 = new JMenuItem("Indirizzo IP Server");
    JTextArea area = new JTextArea();
    JScrollPane scroll = new JScrollPane(area);
    JTextField field1 = new JTextField("Messaggio");
    JButton button1 = new JButton("Invio");

    public GuiUDP() {
        this.setTitle("ChatUDP");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 2));

        frame.add(pannello);
        barra.add(menu);
        menu.add(item1);
        menu.add(item2);
        this.setJMenuBar(barra);

        pannello.setLayout(new GridLayout(2, 3));
        pannello.setBorder(new EmptyBorder(40, 10, 40, 10));
        field1.setBorder(new EmptyBorder(12, 12, 12, 12));
        pannello.add(field1);
        pannello.add(button1);
        area.setEditable(false);
        area.setBorder(new EmptyBorder(15, 15, 15, 15));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
        this.add(pannello);
        
        pack();
        
        //bottone invia
//        button1.addActionListener(new ActionListener(){
//                        if(username == null && IP_address == null){ 
//                    JOptionPane.showMessageDialog(null, "Username o Indirizzo IP sbagliati");
//                }else{
//                    invio(field1.getText(),username);
//                    field1.setText("");
//          
//        });
        //Inserisci IP
        item2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                IP_address = JOptionPane.showInputDialog("Inserisci l'IP del server");
                if(IP_address != null){ 
                  area.append("L'indirizzo IP " + "''" + IP_address + "''"+ " è stato inserito correttamente!");
                  area.append("\n");            
                }
            }
        });
        
        //Inserisci Username
        item1.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent ae) {
                username = JOptionPane.showInputDialog("Inserisci un username");
                if(username != null){ 
                  area.append(" Il tuo username: " + "''" + username + "''"+ " è stato inserito correttamente!");
                  area.append("\n");  
            }
            
        }

    });
 }
    
    Runnable r = new Runnable() {
        @Override
        public void run() {
            new GuiUDP().setVisible(true);
        }
    };

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
//    
//    private void invio(String messaggio, String username) {
//        byte[] array;
//        DatagramPacket datagram;
//      
//         try {
//            //Concateno l'username e l'ora con il messaggio(questa fase si ripete per ogni messaggio)
//            String messaggio = username.concat(messaggio); 
//
//                //Trasformo in array di byte la stringa che voglio inviare
//                array = messaggio.getBytes("UTF-8");
//
//                // Costruisco il datagram (pacchetto UDP) di richiesta 
//                // specificando indirizzo e porta del server a cui mi voglio collegare
//                // e il messaggio da inviare che a questo punto si trova nel buffer
//                datagram = new DatagramPacket(array, array.length, IP_address, porta);
//                // spedisco il datagram
//                socket.send(datagram);
//        } catch (IOException ex) {
//            Logger.getLogger(GuiUDP.class.getName()).log(Level.SEVERE, null, ex);
//        }            }
    
}
