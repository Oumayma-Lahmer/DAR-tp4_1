package tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	 public static void main(String[] args) {

	        try {
	        	System.out.println("Je suis un client non conncté");
	        	/* La classe DatagramPacket encapsule une adresse internet,
	        	 *  un port et les données qui sont échangées grâce à un objet de type DatagramSocket. 
	        	 *  Elle possède plusieurs constructeurs pour encapsuler des paquets émis ou reçus.
	        	 */
	            DatagramSocket clientSocket = new DatagramSocket();
	         // InetAddress: Renvoyer l'adresse du serveur
	            InetAddress serverAddress = InetAddress.getByName("localhost");
	        	System.out.println("Je suis un client conncté");

	            String request = "Demande de date ";
	            byte[] sendData = request.getBytes();

	            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 124);
	            clientSocket.send(sendPacket);

	            byte[] receiveData = new byte[1024];
	            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	            clientSocket.receive(receivePacket);

	            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
	          //getData(): Renvoyer les données contenues dans le paquet
	          //getLength (): Renvoyer la taille des données contenues dans le paquet

	            System.out.println("Date et heure fournies par le serveur: " + response);
	            clientSocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}