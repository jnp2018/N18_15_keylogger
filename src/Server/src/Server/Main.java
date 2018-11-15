package Server;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void chuc_nang() {
		System.out.println("1. Chon 1 de xem file da nhan.");
		System.out.println("2. Chon 2 de gui goi tin.");
	}

	public static void xem_file() {
		String root = System.getProperty("user.dir");
		Path path = Paths.get(root);
		try (DirectoryStream<Path> direct = Files.newDirectoryStream(path, "*.{txt}")) {
			direct.forEach(System.out::println);
			System.out.println("Nhap ten file ban muon xem va an enter:");
			@SuppressWarnings("resource")
			String fileName = new Scanner(System.in).nextLine();
			FileIO.File_Reader(fileName);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void gui_file(SocketServer socket) {
		System.out.println("Nhap duong dan file muon gui:");
		@SuppressWarnings("resource")
		String fileName = new Scanner(System.in).nextLine();
		socket.SendData(fileName);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SocketServer ss = new SocketServer();
		Thread thread = new Thread(ss);
		thread.start();
		chuc_nang();
		short chon;
		while (true) {
			try {
				chon = new Scanner(System.in).nextShort();
			}catch(Exception e) {
				chon = 0;
			}
			switch (chon) {
			case 1:
				xem_file();
				chuc_nang();
				break;
			case 2:
				gui_file(ss);
				chuc_nang();
				break;
			default: 
				System.out.println("Nhap khong hop le!");
				chuc_nang();
			}
		}
	}
}
