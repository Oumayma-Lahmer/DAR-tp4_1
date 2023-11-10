package tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	final static int PORT = 1234;
	private static byte[] buffer = new byte[1024];
	public static void main(String[] args) {
		System.out.println("Donner votre nom");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		try {
			/* La classe DatagramPacket encapsule une adresse internet,
			 *  un port et les données qui sont échangées grâce à un objet de type DatagramSocket. 
			 *  Elle possède plusieurs constructeurs pour encapsuler des paquets émis ou reçus.
			 */
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(username.getBytes(), username.length(), InetAddress.getByName("localhost"), PORT);
			// InetAddress: Renvoyer l'adresse du serveur
			socket.send(packet);
			
			DatagramPacket receiveData = new DatagramPacket(buffer, buffer.length);
			socket.receive(receiveData);
			
			System.out.println(new String(receiveData.getData(),0,receiveData.getLength()));
			//getData(): Renvoyer les données contenues dans le paquet
			//getLength (): Renvoyer la taille des données contenues dans le paquet
			socket.close();
			
		} catch (IOException e) {e.printStackTrace();}

}
}