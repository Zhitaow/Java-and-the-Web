package myprog.single;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_ch04_03 {
	public String inputFile = "bin/students.txt";
	public String outputFile = "bin/email.txt";
	public static void main(String[] args) {
		Solution_ch04_03 list = new Solution_ch04_03();
		String inputFileName = list.inputFile; //list.setFileName();
		String outputFileName = list.outputFile; //list.setFileName(); 
		genEmail(inputFileName, outputFileName, ":", "se.depaul.edu");
		System.out.println(-2 % 10);
	}
	
	public static void genEmail(String inputFileName, String outputFileName, String delimiter, String postfix) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileName));
			FileWriter out = new FileWriter(outputFileName);
			BufferedWriter bw =new BufferedWriter(out);
			String line;
			while((line = br.readLine())!= null) {
				
				try {
					String[] data = line.split(delimiter);
					String lastName = data[0].substring(0, 1);
					String firstName = data[1].substring(0, 1);
					String ssn;
					if (data[2].length() <= 4) {
						ssn = data[2];
					} else {
						ssn = data[2].substring(data[2].length()-4);
					}
					String email = firstName + lastName + ssn + "@" + postfix;
					email = email.toLowerCase();
					System.out.println(Arrays.toString(data));
					System.out.println(email);
					bw.write(email + "\r\n");
				}
				catch (Exception m) {
					System.out.println(m);
				}
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			System.out.println("Invalid file path!");
		}
	}
	
	public String setFileName(){
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.next();
		scanner.close();
		return filename;
	}
	
}
