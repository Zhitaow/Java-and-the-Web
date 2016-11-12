package Chapter05;
import java.awt.Button;
import java.io.*;
/**
 * Exercise 5.1 (b): A polymorphic array of shapes
 * (i) Read a text file that contains descriptions of different shapes from
 * standard input; (ii) Create instances of the shapes described in the input 
 * file and store them in an array of Shapes; (iii) Print the string 
 * representations of all the shapes stored in the array to standard output. 
 * @author Zhitao Wang
 * @version 1.0
 */
public class Ch05Solution01 {
    /**
     * 
     * @param inputFileName file name to be read into by this method
     * @param delimiter a sequence of one or more characters used to 
     * specify the boundary between separate, independent regions in 
     * plain text or other data streams.
     * @return an array of Shape instances.
     */
    public static Shape[] readShapeFile(String inputFileName, String delimiter, int[] canvasPosition, Button btn) {
        Shape[] list = new Shape[100]; 
        int count = 0;
        try {
        	FileInputStream stream = new FileInputStream(inputFileName);
            BufferedReader br = new BufferedReader(new 
                InputStreamReader(stream));
            String line = null;
            Shape shape = null;
            while((line = br.readLine())!= null) {
                try {
                // replace all characters, excluding A-z, 0-9, by a blank space
                	line = line.replaceAll("[^A-z0-9-]+", " ");
                	if (count == 0) {
                		btn.setLabel(line);
                	}
                // split all word characters
		       String[] shapes = line.split(delimiter);
		       String name = shapes[0];
                    // create instance of object based on the keyword name.
                    switch (name) {
                        case "LineSegment":
                            Point startPoint = new Point(Integer.parseInt(shapes[1]) + canvasPosition[0], 
                                Integer.parseInt(shapes[2]) + canvasPosition[1]);
                            Point endPoint = new Point(Integer.parseInt(shapes[4]) + canvasPosition[0], 
                                Integer.parseInt(shapes[5]) + canvasPosition[1]);
                            shape = new LineSegment(startPoint, endPoint);
                            break;
                        case "Rectangle":
                            Point position = new Point(Integer.parseInt(shapes[1]) + canvasPosition[0], 
                                Integer.parseInt(shapes[3]) + canvasPosition[1]);
                            int width = Integer.parseInt(shapes[4]);
                            int height = Integer.parseInt(shapes[6]);
                            shape = new Rectangle(position, width, height);
                            break;
                        case "Circle":
                            Point center = new Point(Integer.parseInt(shapes[1]) + canvasPosition[0], 
                                Integer.parseInt(shapes[2]) + canvasPosition[1]);
                            int radius = Integer.parseInt(shapes[3]);
                            shape = new Circle(center, radius);
                            break;
                        case "Triangle":
                            Point corner1 = new 
                                Point(Integer.parseInt(shapes[1]) + canvasPosition[0], 
                                      Integer.parseInt(shapes[2]) + canvasPosition[1]);
                            Point corner2 = new 
                                    Point(Integer.parseInt(shapes[4]) + canvasPosition[0], 
                                          Integer.parseInt(shapes[5]) + canvasPosition[1]);
                            Point corner3 = new 
                                    Point(Integer.parseInt(shapes[7]) + canvasPosition[0], 
                                          Integer.parseInt(shapes[8]) + canvasPosition[1]);
                            shape = new Triangle(corner1, corner2, corner3);
                            break;
                        default:
                            shape = null;
                            break;
                    }
                    // append the instance to the Shape array.
                    list[count++] = shape;
                } catch (Exception m) {
                    System.out.println(m);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Invalid file path!");
            btn.setLabel(e.toString());
            btn.setBounds(350, 30, 300, 20);
        }
        return list;
    }
}

