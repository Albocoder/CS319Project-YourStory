/*
 * https://www.dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java
 */
package Story_Line;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Image{
  public static void main(String args[])throws IOException{
    BufferedImage image = null;
    File f = null;

    //read image file
    try{
      f = new File("D:\\Image\\Taj.jpg");
      image = ImageIO.read(f);
    }catch(IOException e){
      System.out.println("Error: "+e);
    }

    //write image
    try{
      f = new File("D:\\Image\\Output.jpg");
      ImageIO.write(image, "jpg", f);
    }catch(IOException e){
      System.out.println("Error: "+e);
    }
  }
}
