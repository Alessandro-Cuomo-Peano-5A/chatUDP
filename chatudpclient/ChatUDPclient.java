package chatudpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import chatudpclient.ReceiveFromServerAndPrint;
import chatudpclient.SendUserInputToServer;

/**
 *
 * @author Cuomo Alessandro
 */
public class ChatUDPclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiUDP().setVisible(true);
            }
        });

        String IP_address = "127.0.0.1";
        InetAddress address = InetAddress.getByName(IP_address);
        int UDP_port = 1077;
        
        

        DatagramSocket socket;
        try {

            socket = new DatagramSocket();
            

            //creo il thread che riceve i messaggi dal server e scrive su schermo i messaggi ricevuti
            Thread receiveAndPrint = new Thread(new ReceiveFromServerAndPrint(socket));
            receiveAndPrint.start();
            System.out.println("sono in ascolto...");

            //creo il thread che invia il messaggio digitato da utente verso il server
            Thread sendUserInput = new Thread(new SendUserInputToServer(socket, address, UDP_port));
            sendUserInput.start();
            System.out.println("utente e' invitato di inserire un messaggio da inviare al server...");


            System.out.println("connessione server riuscita");

            //mi metto in attesa che utente voglia terminare digitando "quit" e quindi chiudo baracca e burattini
            sendUserInput.join(); //mi metto in attesa il thread finisca
            receiveAndPrint.interrupt(); //interrompo anche il receive thread
            receiveAndPrint.join();  //aspetto che anche questo thread finisca
            socket.close(); //ora posso chiudere il socket in modo pulito

        } catch (SocketException ex) {
            System.out.println("ERROR: connessione server non riuscita");
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatUDPclient.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    
}
