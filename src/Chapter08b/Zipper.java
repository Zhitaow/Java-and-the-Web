package Chapter08b;
/**
 * The Zipper
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/13/2016
 * {@link https://www.mkyong.com/java/how-to-compress-a-file-in-gzip-format/}
 */

import java.io.*;
import java.util.zip.*;

public class Zipper {
	String inputFile = "", outputFile = "";
	String inputPrefix = "", inputFilename = "";
	
	private void processFilename(String inputFile, String outputFile, ZipperLayout zipLayout, String postfix) {
		this.inputFile = inputFile.replace("\\", "/");
		this.outputFile = outputFile.replace("\\", "/");
		String[] strs = this.inputFile.split("/");
		inputFilename = strs[strs.length-1];
		String[] temp = inputFilename.split("\\.");
		inputPrefix = temp[0] + postfix;
		System.out.println(inputFilename);
		if (this.outputFile.charAt(this.outputFile.length()-1) != '/') {
			this.outputFile += "/";
		}
		this.outputFile += inputPrefix; 
		zipLayout.setLogText("Zipped output file: " + this.outputFile + "\n");
	}
	
	
	public void zip(String inputFile, String outputFile, ZipperLayout zipLayout) {
		if (validate(inputFile, outputFile, zipLayout)) {
			processFilename(inputFile, outputFile, zipLayout, ".zip");
			if (fileExist(inputFile)) {
				byte[] buffer = new byte[1024];
				try{
					FileOutputStream outStream = new FileOutputStream(this.outputFile);
					ZipOutputStream zipOutStream = new ZipOutputStream(outStream);
					ZipEntry entry= new ZipEntry(inputFilename);
					zipOutStream.putNextEntry(entry);
					FileInputStream in = new FileInputStream(inputFile);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zipOutStream.write(buffer, 0, len);
					}
					in.close();
					zipOutStream.closeEntry();
					zipOutStream.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			} else {
				zipLayout.setLogText("Input file not exists!");
			}
		}
	}
		
	public void gz(String inputFile, String outputFile, ZipperLayout zipLayout) {
		if (validate(inputFile, outputFile, zipLayout)) {
			processFilename(inputFile, outputFile, zipLayout, ".gz");
			if (fileExist(inputFile)) {
				byte[] buffer = new byte[1024];
				try{
					GZIPOutputStream gzos =
							new GZIPOutputStream(new FileOutputStream(this.outputFile));
					FileInputStream in =
							new FileInputStream(inputFile);
					int len;
					while ((len = in.read(buffer)) > 0) {
						gzos.write(buffer, 0, len);
					}
					in.close();
					gzos.finish();
					gzos.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			} else {
				zipLayout.setLogText("Input file not exists!");
			}
		}
	}
	
	public boolean validate(String inputFile, String outputFile, ZipperLayout zipLayout) {
		if (!(inputFile.equals("") || outputFile.equals("") || zipLayout == null)) {
			return true;
		} else if (zipLayout != null && zipLayout.getClass().equals(ZipperLayout.class)) {
			zipLayout.setLogText("Please specify input or output file path!");
		}
		return false;
	}
	
	public boolean fileExist(String inputFile) {
		File f = new File(inputFile);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}
	
}
