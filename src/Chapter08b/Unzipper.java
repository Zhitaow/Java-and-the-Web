package Chapter08b;

import java.io.*;
import java.util.zip.*;

/**
 * The Unzipper
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/13/2016
 * {@link https://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/}
 */
public class Unzipper {
	private String inputFile;
	private String outputFolder;
	private String inputFilename;

	public void unzip(String inputFile, String outputFile, ZipperLayout zipLayout) {
		if (validate(inputFile, outputFile, zipLayout)) {
			if (inputFile.contains(".gz") || inputFile.contains(".zip")) {
				this.inputFile = inputFile;
				this.outputFolder = outputFile;
				byte[] buffer = new byte[1024];
				try{
					//create output directory is not exists
					System.out.println("OutputFolder: " + this.outputFolder);
					File folder = new File(outputFolder);
					if(!folder.exists()){
						folder.mkdir();
					}
					//get the zip file content
					ZipInputStream zis =
							new ZipInputStream(new FileInputStream(this.inputFile));
					System.out.println("input file: " + this.inputFile);
					//get the zipped file list entry
					ZipEntry ze = zis.getNextEntry();
					while(ze!=null){
						String fileName = ze.getName();
						File newFile = new File(outputFolder + File.separator + fileName);
						zipLayout.setLogText("unzipped output file: "+ newFile.getAbsoluteFile());
						//create all non exists folders
						//else you will hit FileNotFoundException for compressed folder
						new File(newFile.getParent()).mkdirs();
						FileOutputStream fos = new FileOutputStream(newFile);
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
			            fos.close();
			            ze = zis.getNextEntry();
			    	}
			    	zis.closeEntry();
			        zis.close();
			     }catch(Exception ex){
			    	ex.printStackTrace();
			    }
			} else {
				zipLayout.setLogText("Input file is not a valid gz file!");
			}
		}
	}
	public void ungz(String inputFile, String outputFile, ZipperLayout zipLayout) {
		if (validate(inputFile, outputFile, zipLayout)) {
			if (inputFile.contains(".gz")) {
				this.inputFile = inputFile;
				this.outputFolder = outputFile;
				byte[] buffer = new byte[1024];
				try{
					GZIPInputStream gzis =
							new GZIPInputStream(new FileInputStream(this.inputFile));
					String strs[] = inputFile.split(".gz");
					inputFile = strs[0];
					outputFile = outputFile.replace("\\", "/");
					inputFile = inputFile.replace("\\", "/");
					strs = inputFile.split("/");
					inputFilename = strs[strs.length-1];
					FileOutputStream out =
							new FileOutputStream(outputFile + inputFilename);
					int len;
					while ((len = gzis.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					gzis.close();
					out.close();
					String message = "ungzipped output file: "+ outputFile + inputFilename + "\n";
					zipLayout.setLogText(message);
			    }catch(Exception ex){
			    	ex.printStackTrace();
			    }
			} else {
				zipLayout.setLogText("Input file is not a valid gz file!");
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

}
