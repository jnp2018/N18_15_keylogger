package Server;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileIO {
	public static void File_Writer(byte[] bytes) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmssddMMyyyy");
		String fileName =  "Client-" + dateFormat.format(Calendar.getInstance().getTime()) + ".txt";
		try(FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			ByteBuffer buff = ByteBuffer.wrap(bytes);
			channel.write(buff);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static byte[] File_InputStream(File file) {
		try (FileInputStream fis = new FileInputStream(file);
				FileChannel channel = fis.getChannel()) {
				ByteBuffer buff = ByteBuffer.allocate((int) channel.size());
				channel.read(buff);
				if(buff == null) return null;
				buff.rewind();
				return buff.array();
			}catch(IOException e) {
				System.err.println("File khong ton tai!");
				return null;
			}
	}
	
	public static void File_Reader(String fileName) {
			try {
				File file = new File(fileName);
				Desktop.getDesktop().open(file);
			} catch (IOException|IllegalArgumentException e) {
				System.err.println("File khong ton tai!");
			}
	}
		
	
	
}
