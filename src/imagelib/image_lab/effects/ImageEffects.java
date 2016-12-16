/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelib.image_lab.effects;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Pablo
 */
public class ImageEffects {
    
    public static BufferedImage convertToGrayScale(BufferedImage image){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                int grayLevel = (c[0] + c[1] + c[2]) / 3;
                outImg.setRGB(
                        i,
                        j,
                        colorToInt(new int[]{
                            grayLevel,
                            grayLevel,
                            grayLevel
                        })
                );
            }
        }
        return outImg;
    }
    
    public static BufferedImage thresholdImage(BufferedImage image, int threshold){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                int grayLevel = (c[0] + c[1] + c[2]) / 3;
                if(grayLevel >= threshold){
                    outImg.setRGB(
                            i,
                            j,
                            Color.white.getRGB()
                    );
                }else{
                    outImg.setRGB(
                            i,
                            j,
                            Color.black.getRGB()
                    );
                }
            }
        }
        return outImg;
    }
    
    private static int[] intToColor(int intColor){
        return new int[]{
            (intColor >> 16) & 0xFF,
            (intColor >> 8) & 0xFF,
            (intColor & 0xFF)
        };
    }
    
    private static int colorToInt(int[] color){
        int intColor = color[0];
        intColor = (intColor << 8) + color[1];
        intColor = (intColor << 8) + color[2];
        return intColor;
    }
}
