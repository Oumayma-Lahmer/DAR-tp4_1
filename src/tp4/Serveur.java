package tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Serveur extends Thread{

	public static void main(String[] args) {
		new Serveur().start();
		}
	public void run() {
		try {
			System.out.println("lancement du Serveur");
			DatagramSocket serverSocket = new DatagramSocket(124);
			while(true) {
				new ClientProcess(serverSocket).start();
			}
			}
	 catch (IOException e) {e.printStackTrace();}
			}
	public class ClientProcess extends Thread {
		DatagramSocket socket;
		public ClientProcess(DatagramSocket serverSocket) {
			super();
			this.socket = serverSocket;
			}
		public void run() {
			try {
				byte[] receiveData = new byte[1024];        
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				System.out.println("Client d'adresse :"+receivePacket.getAddress()+" demande la date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = dateFormat.format(new Date());
				byte[] sendData = currentTime.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
				System.out.println("Envoie terminé pour "+receivePacket.getAddress());
				}
			catch (IOException  e) {e.printStackTrace();
}
}
}
}