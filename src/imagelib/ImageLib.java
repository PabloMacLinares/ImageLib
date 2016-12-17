/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelib;

import imagelib.image_lab.tools.ImageTools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Pablo
 */
public class ImageLib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img3.png"));
            //image = ImageTools.convertToGrayScale(image);
            //image = ImageTools.thresholdImage(image, 128);
            //image = ImageTools.splitColorChannels(image)[0];
            //image = ImageTools.inverColor(image);
            //image = ImageTools.maximizeRGB(image);
            //image = ImageTools.minimizeRGB(image);
            //image = ImageTools.adjustHSL(image, 0f, 0, 0);
        } catch (IOException ex) {
            Logger.getLogger(ImageLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        new PreviewImage(image);
        /*ArrayList<BufferedImage> images = new ArrayList<>();
        try {
            images.add(ImageIO.read(new File("img1.png")));
            images.add(ImageIO.read(new File("img2.png")));
            images.add(ImageIO.read(new File("img3.png")));
        } catch (IOException ex) {
            Logger.getLogger(ImageLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        new PreviewCarousel(images);*/
    }

}
