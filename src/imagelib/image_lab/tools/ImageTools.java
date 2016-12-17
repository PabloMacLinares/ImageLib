/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelib.image_lab.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Pablo
 */
public class ImageTools {
    
    /**
     * Convert one image to GrayScale.
     * @param image Image to convert.
     * @return the converted image.
     */
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
    
    /**
     * Threshold an image
     * @param image Image to convert.
     * @param threshold value between 0 and 255.
     * @return the converted image.
     */
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
    
    /**
     * Split the RGB channels of the given image.
     * @param image Image to split.
     * @return Return an array of BufferedImage with the 3 color channels:
     * <ol>
     * <li>Red channel.</li>
     * <li>Green channel.</li>
     * <li>Blue channel.</li>
     * </ol>
     */
    public static BufferedImage[] splitColorChannels(BufferedImage image){
        BufferedImage imgRed = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage imgGreen = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage imgBlue = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                imgRed.setRGB(i, j, colorToInt(new int[]{c[0], c[0], c[0]}));
                imgGreen.setRGB(i, j, colorToInt(new int[]{c[1], c[1], c[1]}));
                imgBlue.setRGB(i, j, colorToInt(new int[]{c[2], c[2], c[2]}));
            }
        }
        return new BufferedImage[]{imgRed, imgGreen, imgBlue};
    }
    
    /**
     * Inverts the colors of the given image.
     * @param image Image to invert.
     * @return the converted image.
     */
    public static BufferedImage inverColor(BufferedImage image){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                outImg.setRGB(
                        i,
                        j,
                        colorToInt(new int[]{
                            255 - c[0],
                            255 - c[1],
                            255 - c[2]
                        })
                );
            }
        }
        
        return outImg;
    }
    
    /**
     * Takes the pixels of the image, maximize the highest RGB channel and
     * minimize the others.
     * @param image Image to maximize.
     * @return the converted image.
     */
    public static BufferedImage maximizeRGB(BufferedImage image){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                int[] max = new int[]{0, 0, 0};
                if(c[0] > c[1] && c[0] > c[2]){
                    max = new int[]{255, 0, 0};
                }else if(c[1] > c[0] && c[1] > c[2]){
                    max = new int[]{0, 255, 0};
                }else if(c[2] > c[0] && c[2] > c[1]){
                    max = new int[]{0, 0, 255};
                }else if(c[0] == c[1] && c[0] == c[2]){
                    if(c[0] >= 128)
                        max = new int[]{255, 255, 255};
                }
                outImg.setRGB(i, j, colorToInt(max));
            }
        }
        
        return outImg;
    }
    
    /**
     * Takes the pixels of the image, maximize the lowest RGB channel and
     * minimize the others.
     * @param image Image to minimize.
     * @return the converted image.
     */
    public static BufferedImage minimizeRGB(BufferedImage image){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                int[] max = new int[]{0, 0, 0};
                if(c[0] < c[1] && c[0] < c[2]){
                    max = new int[]{255, 0, 0};
                }else if(c[1] < c[0] && c[1] < c[2]){
                    max = new int[]{0, 255, 0};
                }else if(c[2] < c[0] && c[2] < c[1]){
                    max = new int[]{0, 0, 255};
                }else if(c[0] == c[1] && c[0] == c[2]){
                    if(c[0] < 128)
                        max = new int[]{255, 255, 255};
                }
                
                outImg.setRGB(i, j, colorToInt(max));
            }
        }
        
        return outImg;
    }
    
    /**
     * Adjust the HSL channels of the image (Only Hue channel works).
     * @param image Image to convert.
     * @param hue Hue value (0 - 1).
     * @param saturation Saturation value (0 - 1).
     * @param light Light value value (0 - 1).
     * @return the converted image.
     */
    public static BufferedImage adjustHSL(BufferedImage image, float hue, float saturation, float light){
        BufferedImage outImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] c = intToColor(image.getRGB(i, j));
                float[] hsl = Color.RGBtoHSB(c[0], c[1], c[2], new float[3]);
                int fc = Color.HSBtoRGB(hsl[0] + hue, hsl[1], hsl[2]);
                outImg.setRGB(i, j, fc);
            }
        }
        
        return outImg;
    }
    
    /**
     * Extract the RGB channels from an integer color.
     * @param intColor
     * @return Return an array of integer with the 3 color channels:
     */
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
