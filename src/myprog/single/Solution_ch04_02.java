package myprog.single;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Solution_ch04_02 {
	private int sum;
	private double average;
	private int count;
	private List<Integer> data = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//for (int i = 0; i < args.length; i++) {
		//	String fileName = args[i];
			String fileName = "bin/badaverage.txt"; //"bin/average.txt";
			Solution_ch04_02 list = new Solution_ch04_02();
			list.cleanData(fileName);
			if (list.getCount() != 0) {
				System.out.println("The cleaned data are: " + list.getData());
				System.out.println("The total number of interger in the file \"" + fileName + "\" is: " + list.getCount());
				System.out.println("The average is: " + list.getAverage());
			}
		//}
	}
	
	public void cleanData(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = br.readLine())!= null) {
				try {
					int num = Integer.parseInt(line);
					data.add(num);
					sum += num;
					count++;
					average = (double) sum / count;
				}
				catch (Exception m) {
					System.out.println(line+" is not a number. Skip this line.");
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Invalid file path!");
		}
	}
	
	public double getAverage() {
		return average;
	}
	
	public int getCount() {
		return count;
	}
	
	public List<Integer> getData(){
		return data;
	}
}
