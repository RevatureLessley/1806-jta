package com.revature.day3.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Whenn naming classes, we start every word in teh name with a capital letter.
//This is called PascalCasing  <-- written as such.
public class Driver {
	private String FILE_NAME = "myfile.csv";
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedWriter bw = null;
	private BufferedReader br = null;
	
	
	//Method names, should ALWAYS start with a lowercase letter. Then every word's first 
	// letter after the first word should be upper case.
	//IE camelCasing
	public static void main(String[] args) {
		/*
		 * Note: When specifying directories, if your directory starts with a
		 * forward slash (/) you are specifying absolute path. (Meaning you place the
		 * full path starting from drive, or root folder of your OS).
		 * If you do not start with a forward slash, you are using relative paths, which 
		 * start from current folder, or a different predetermined local space. In Eclipse,
		 * this local space starts with the project folder, just before 'src'.
		 */
		Driver d = new Driver();
		try{
			
			
			d.fosExample();
			d.fisExample();
			d.fileWriterAndFileReaderExample();
			d.bufferedExample();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("END OF EXECUTION");
	}
	/*
	 * FileInput/FileOutputStreams
	 * -These streams are able to write data/read data one BYTE at a time.
	 */
	public void fosExample() throws IOException{
		try {
			fos = new FileOutputStream(FILE_NAME);
			char rand;
			
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 30; j++){
					if(j==16){
						fos.write(1252);
					}else{
						rand = (char)((Math.random() * 26) + 'A');
						fos.write((byte) rand);
					}
					if(j<29){
						fos.write(44);
					}
				}
				fos.write('\n');
			}
			/*
			 * Since Input/Output stream reads in only bytes, it is limited to
			 * generic ascii table of characters that you can write. If you write
			 * anything that is greater than 127 in value, you will
			 * overflow into negative space and get wierd characters in return.
			 */
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			if(fos != null){
				fos.close();
				//ALWAYS CLOSE STREAMS!
				/*
				 * You will risk memory leaks if you don't close streams that you open.
				 * Java tries its darndest to close them for you, but is not reliable.
				 */
			}
		}
	}
	
	public void fisExample() throws IOException{
		try{
			fis = new FileInputStream(FILE_NAME);
			byte in;
			//-1 means you have reach the end of the file
			while((in = (byte)fis.read())!= -1){
				System.out.print((char)in);
			}
			System.out.println();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			fis.close();
		}
	}
	
	/*
	 * FileReader/FileWriters
	 * -The underlying difference between a filereader/wrtier and an input/output stream
	 * is that the reader and writer cater to the streams one character at a time as opposed
	 * to one byte at a time.
	 * This handles at double the speed. (Essentially)
	 */
	public void fileWriterAndFileReaderExample() throws IOException{
		try{
			fr = new FileReader(FILE_NAME);
			fw = new FileWriter(FILE_NAME.split("\\.")[0] + 
								"_lowerCase" + "." +
								FILE_NAME.split("\\.")[1]);
			
			int in;
			/*
			 * Every time I invoke the read() method, I grab a letter, and move my cursor
			 * that is reading the stream to the next letter. So I can only call read()
			 * once per letter.
			 */
			while((in = fr.read()) != -1){
				if(in == 10 || in == 44){
					fw.write((char)in);
				}else{
					fw.write((char)(in + 32));
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			if(fr != null){
				fr.close();
			}
			if(fw != null){
				fw.close();
			}
		}
	}
	
	/*
	 * Buffered readers/wrtiers
	 * -These classes are especially useful for reading and writing files
	 * since they use a buffer to read and write with. What this means is you can set
	 * the amount of characters you read/write at a time.
	 * By default, you read by entire lines at a time.
	 * Or you can write entire strings at a time.
	 */
	public void bufferedExample() throws IOException{
		try{
			//Note, we wrap a bufferedReader around a fileReader
			br = new BufferedReader(new FileReader(FILE_NAME));
			bw = new BufferedWriter(new FileWriter(
													FILE_NAME.split("\\.")[0] +
													"_chaos." +
													FILE_NAME.split("\\.")[1]
													));
			String input = "";
			
			while((input = br.readLine())!=null){
				input = input.replaceAll("A", "Hey, I found an A over here");
				input += '\n';
				bw.write(input);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			if(bw != null){
				bw.close();
			}
			if(br != null){
				br.close();
			}
		}
	}

}
