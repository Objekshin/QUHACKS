
package Tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImgLoader {
    public static Image loadImage(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("./res/pics/" + name));
        }
        catch (IOException var2_2) {
            // empty catch block
        }
        return img;
    }
}


