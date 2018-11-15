package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SocketServer implements Runnable {
	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public void SendData(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.err.println("File khong ton tai!");
		} else {
			try {
				byte[] bytes = FileIO.File_InputStream(file);
				dos.write(bytes);
				dos.flush();
				System.err.println("Gui thanh cong!");
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public void RecData() {
		try {
			byte[] bytes = new byte[dis.available()];
			dis.read(bytes);
			FileIO.File_Writer(bytes);
			JOptionPane.showMessageDialog(null, "Da nhan duoc goi tin tu Client !");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(6969);
			socket = server.accept();
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				if (dis.readByte() != 0) {
					RecData();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
