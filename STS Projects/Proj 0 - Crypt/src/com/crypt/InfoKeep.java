import java.nio.charset.Charset;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class InfoKeep {
	
	private static byte[][] convertToBytes(String[] strings) {
	    byte[][] data = new byte[strings.length][];
	    for (int i = 0; i < strings.length; i++) {
	        String string = strings[i];
	        data[i] = string.getBytes(Charset.defaultCharset()); // you can chose charset
	    }
	    return data;
	}
	
	public static void setUserPassInfo(List<String> data) {
		byte[][] buffer = convertToBytes(data.toArray(new String[data.size()]));
		FileOutputStream out = new FileOutputStream("UserPassInfo");
		out.write(buffer);
	}
	
	public static byte[] getUserPassInfo() {
		
	}
	
	
}
