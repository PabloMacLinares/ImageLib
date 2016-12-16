/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelib.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Pablo
 */
public class ImageView extends JPanel{
    
    /**
     * Center the image in the view, but perform no scaling.
     */
    public static final int CENTER = 0;

    /**
     * Scale the image uniformly (maintain the image’s aspect ratio) so that both
     * dimensions (width and height) of the image will be equal to or larger than
     * the corresponding dimension of the view.
     */
    public static final int CENTER_CROP = 1;

    /**
     * Scale the image uniformly (maintain the image’s aspect ratio) so that both
     * dimensions (width and height) of the image will be equal to or less than 
     * the corresponding dimension of the view.
     */
    public static final int CENTER_INSIDE = 2;

    /**
     * Scale using the image matrix when drawing.
     */
    public static final int MATRIX = 3;

    /**
     * Scale in X and Y independently, so that src matches dst exactly. 
     * This may change the aspect ratio of the src.
     */
    public static final int FIT_XY = 4;
    
    private BufferedImage image;
    private int scaleType;
    
    /**
     * Creates a new ImaveView with the specified image and scaleType.
     * @param image
     * @param scaleType
     */
    public ImageView(BufferedImage image, int scaleType){
        this.image = image;
        this.scaleType = scaleType;
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.repaint();
    }
    
    /**
     * Creates a new ImageView with the specified image and scaleType = FITXY.
     * @param image
     */
    public ImageView(BufferedImage image){
        this(image, FIT_XY);
    }
    
    /**
     * Creates a new ImageView with a null image and scaleType = FITXY.
     */
    public ImageView(){
        this(null);
    }

    /**
     * Returns the image of this component or null if no image is currently set.
     * @return the image object for this component
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the image of this component
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        this.repaint();
    }
    
    /**
     * Clear the image of this component
     */
    public void clearImage(){
        this.image = null;
        this.repaint();
    }

    /**
     * Returns the Scale Type of this component or 0 if not set.
     * @return the image object for this component
     */
    public int getScaleType() {
        return scaleType;
    }

    /**
     * Sets de Scale Type of the image of this component.
     * You must specify one of the following choices:
     * <br>
     * <ul>
     * <li><code>CENTER</code></li>
     * <li><code>CENTER_CROP</code></li>
     * <li><code>CENTER_INSIDE</code></li>
     * <li><code>MATRIX</code></li>
     * <li><code>FIT_XY</code></li>
     * </ul>
     * @param scaleType the Scale type which should be applied to the image
     * of this component.
     */
    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(this.image != null){
            Rectangle r = performScale();
            g.drawImage(this.image, r.x, r.y, r.width, r.height, null);
        }
    }
    
    /**
     * Perform the asigned scaleType to the image of this component.
     * @return the scaled rectangle.
     */
    protected Rectangle performScale(){
        float aspectRatio = (float)image.getWidth() / (float)image.getHeight();
        int xOffset = 0;
        int yOffset = 0;
        switch(scaleType){
            case CENTER:
                xOffset = (this.getWidth() - image.getWidth()) / 2;
                yOffset = (this.getHeight() - image.getHeight()) / 2;
                return new Rectangle(
                        xOffset,
                        yOffset,
                        image.getWidth(),
                        image.getHeight()
                );
            case CENTER_CROP:
                if(this.getWidth() >= this.getHeight()){
                    yOffset = (this.getHeight() - (int) (this.getWidth() / aspectRatio)) / 2;
                    return new Rectangle(
                            0,
                            yOffset,
                            this.getWidth(), 
                            (int) (this.getWidth() / aspectRatio)
                    );
                }else{
                    xOffset = (this.getWidth() - (int) (this.getHeight() / aspectRatio)) / 2;
                    return new Rectangle(
                            xOffset,
                            0,
                            (int) (this.getHeight() / aspectRatio),
                            this.getHeight()
                    );
                }
            case CENTER_INSIDE:
                if(this.getWidth() < this.getHeight()){
                    yOffset = (this.getHeight() - (int) (this.getWidth() / aspectRatio)) / 2;
                    return new Rectangle(
                            0,
                            yOffset,
                            this.getWidth(), 
                            (int) (this.getWidth() / aspectRatio)
                    );
                }else{
                    xOffset = (this.getWidth() - (int) (this.getHeight() / aspectRatio)) / 2;
                    return new Rectangle(
                            xOffset,
                            0,
                            (int) (this.getHeight() / aspectRatio),
                            this.getHeight()
                    );
                }
            case MATRIX:
                return new Rectangle(
                        0,
                        0,
                        image.getWidth(),
                        image.getHeight()
                );
            case FIT_XY:
                return new Rectangle(0, 0, this.getWidth(), this.getHeight());
            default:
                return new Rectangle(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
