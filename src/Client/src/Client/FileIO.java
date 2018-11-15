package Client;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class  FileIO {

	public FileIO() {
		//this.File_Hidden();
	}

	public static void File_Hidden() {
		File file = new File(System.getProperty("user.dir"));
		if (file.exists()) {
			try {
				Runtime.getRuntime().exec("attrib +S +H " + "\"" + file + "\"");
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public static void File_Log_Writer(byte[] bytes, File file) {
		try (FileOutputStream fos = new FileOutputStream(file, true);
			FileChannel channel = fos.getChannel()) {
			ByteBuffer buff = ByteBuffer.wrap(bytes);
			channel.write(buff);
			System.out.println(bytes.length);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static byte[] File_InputStream(File file){
		try (FileInputStream fis = new FileInputStream(file);
			FileChannel channel = fis.getChannel()) {
			ByteBuffer buff = ByteBuffer.allocate((int) channel.size());
			channel.read(buff);
			if(buff == null) return null;
			buff.rewind();
			return buff.array();
			
		}catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}	
}
