import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {
    private static ImageSaver ourInstance = new ImageSaver();

    public static ImageSaver getInstance() {
        return ourInstance;
    }

    private ImageSaver() {
    }

    void saveImage(Vector3[] pixelData, int width, int height, String fileName){
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = (y * width) + x;
                if(pixelData[currentPixel].getX() > 255){
                    pixelData[currentPixel].setX(255);
                }
                if(pixelData[currentPixel].getY() > 255){
                    pixelData[currentPixel].setY(255);
                }
                if(pixelData[currentPixel].getZ() > 255){
                    pixelData[currentPixel].setZ(255);
                }
                Color color = new Color((int)pixelData[currentPixel].getX(), (int)pixelData[currentPixel].getY(), (int)pixelData[currentPixel].getZ());
                image.setRGB(x, y, color.getRGB());
            }
        }
        try {
            File outputfile = new File(fileName);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
