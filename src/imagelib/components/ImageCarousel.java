/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelib.components;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class ImageCarousel extends ImageView{
    
    private ArrayList<BufferedImage> images;
    private int updateTime;
    private int carouselPosition = 0;
    private Thread updateThread;
    
    public ImageCarousel(ArrayList<BufferedImage> images, int scaleType, int updateTime){
        super(images.get(0), scaleType);
        this.images = images;
        this.updateTime = updateTime;
    }
    
    public ImageCarousel(ArrayList images, int updateTime){
        this(images, ImageView.FIT_XY, updateTime);
    }
    
    public ImageCarousel(){
        this.images = new ArrayList<>();
        this.setScaleType(ImageView.FIT_XY);
        this.updateTime = -1;
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<BufferedImage> images) {
        this.images = images;
        if(this.images.size() >= 1){
            this.setImage(images.get(0));
        }
    }
    
    public boolean addImage(BufferedImage image){
        return this.images.add(image);
    }
    
    public BufferedImage removeImage(int position){
        return this.images.remove(position);
    }
    
    public boolean removeImage(BufferedImage image){
        return this.images.remove(image);
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
        resetCarousel();
    }

    public int getCarouselPosition() {
        return carouselPosition;
    }

    public void setCarouselPosition(int carouselPosition) {
        if(carouselPosition < images.size()){
            this.stopCarousel();
            this.carouselPosition = carouselPosition;
            this.setImage(images.get(carouselPosition));
        }
    }
    
    public void startCarousel(){
        if(updateThread == null){
            System.out.println("Start");
            updateThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            ImageCarousel.this.setImage(images.get(carouselPosition++));
                            if(carouselPosition >= images.size())
                                carouselPosition = 0;
                            System.out.println(carouselPosition);
                            Thread.sleep(updateTime);
                        }
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            });
            updateThread.start();
        }
    }
    
    public void stopCarousel(){
        if(updateThread != null){
            updateThread.interrupt();
            updateThread = null;
            System.out.println("Stop");
        }
    }
    
    public void resetCarousel(){
        System.out.println("Reset");
        stopCarousel();
        carouselPosition = 0;
        startCarousel();
    }
}
