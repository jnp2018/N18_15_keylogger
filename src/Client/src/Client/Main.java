package Client;



public class Main {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		EventKeyboard eventKey = new EventKeyboard();
		//FileIO.File_Hidden(); //Gọi hàm ẩn Client
		SocketClient socket = new SocketClient("localhost", 6969);
		Thread threadSocket = new Thread(socket);
		threadSocket.start();
	}
}
