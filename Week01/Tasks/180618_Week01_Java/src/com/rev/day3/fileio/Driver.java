package com.rev.day3.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {

	private String FILE_NAME = "myfile.txt";
	private FileOutputStream fos = null;
	private FileInputStream fis = null;

	private FileWriter fw = null;
	private FileReader fr = null;

	private BufferedWriter bw = null;
	private BufferedReader br = null;

	public static void main(String[] args) {
		Driver driver = new Driver();

		try {
			driver.fosExample();
			driver.fisExample();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			driver.fileWriterAndFileReaderExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			driver.bufferedExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("END OF EXEC");

	}

	// file output stream
	public void fosExample() throws IOException {

		try {
			fos = new FileOutputStream(FILE_NAME);

			char rand;

			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 30; j++) {

					rand = (char) (Math.random() * 26 + 'A');

					fos.write((byte) rand);
					fos.write(44);
				}

				fos.write('\n');
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			if (fos != null)
				fos.close();
		}

	}

	public void fisExample() throws IOException {

		try {
			fis = new FileInputStream(FILE_NAME);
			byte in;

			while ((in = (byte) fis.read()) != -1) {
				System.out.print((char) in);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}

	}

	public void fileWriterAndFileReaderExample() throws IOException {

		try {
			fr = new FileReader(FILE_NAME);
			fw = new FileWriter("lower_" + FILE_NAME);

			int in;

			while ((in = fr.read()) != -1) {
				if (in == ',' || in == '\n') {
					fw.write((char) in);
				} else {
					fw.write((char) (in + 32));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fr != null)
				fr.close();

			if (fw != null)
				fw.close();
		}
	}
	
	public void bufferedExample() throws IOException{
		
		
		try {
			br = new BufferedReader(new FileReader(FILE_NAME));
			bw = new BufferedWriter(new FileWriter("chaos_" + FILE_NAME));
			
			String input = "";
			
			while((input = br.readLine()) != null) {
				input = input.replaceAll("A", "lololol");
				input += "\n";
				bw.write(input);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br != null)
				br.close();
			if(bw != null)
				bw.close();
		}
	}

}
