/**
 * Starter code for Processor - the class that processes images.
 * <p> This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.</p>
 * 
 * <p>All methods added to this class should be static. In other words, you do not
 *    have to instantiate (create an object of) this class to use it - simply call
 *    the methods with Processor.methodName and pass a GreenfootImage to be manipulated.
 *    Note that you do not have to return the processed image, as you will be passing a
 *    reference to your image, and it will be altered by the method, as seen in the Blueify
 *    example.</p>
 *    
 * <p>Some methods, especially methods that change the size of the image (such as rotation
 *    or scaling) may require a GreenfootImage return type. This is because while it is
 *    possible to alter an image passed as a parameter, it is not possible to re-instantiate it, 
 *    which is required to change the size of a GreenfootImage</p>
 * 
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. </p>
 * 
 * @author Jordan Cohen 
 * @version November 2013
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import greenfoot.*;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;

public class Processor  
{
    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static BufferedImage blueify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        
        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 253){
                    blue += 2;
                }
                    
                if (red >= 50){
                  red--;  
                }
                    
                if (green >= 50){
                    green--;
                }
                    

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }

    public static BufferedImage rotate90Clockwise (BufferedImage bi){
        int xSizeNew = bi.getHeight();
        int ySizeNew = bi.getWidth();
        int count;
        
        BufferedImage newBi = new BufferedImage (bi.getHeight(), bi.getWidth(), 3);
        
        for (int y = 0; y<ySizeNew; y++){
            count = xSizeNew - 1;
            for (int x = 0; x<xSizeNew; x++){
                int rgba = bi.getRGB(y, count);
                newBi.setRGB(x,y, rgba);
                count--;
            }
        }
        
        
        return newBi;
    }
    
    public static BufferedImage rotate90CounterClockwise (BufferedImage bi)
    {
        //this is the single most intelligent line of code I have ever written
        return rotate90Clockwise(rotate90Clockwise(rotate90Clockwise(bi)));
    }
    
    public static BufferedImage rotate180 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        int countX;
        int countY;
        
        BufferedImage newBi = new BufferedImage (bi.getWidth(), bi.getHeight(), 3);
        
        countY = ySize -1;
        
        for (int y = 0; y < ySize; y++){
            countX = xSize - 1;
            for (int x = 0; x < xSize; x++){
                int rgba = bi.getRGB(x,y);
                newBi.setRGB(countX,countY,rgba);
                countX--;
            }
            countY--;
        }
        
        for (int i = 0; i <ySize; i++){
            for (int j = 0; j < xSize; j ++){
                bi.setRGB(j,i,newBi.getRGB(j,i));
            }
        }
        
        return bi;
    }

    public static BufferedImage flipHorizontal (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        int count;

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        
        
        for (int y = 0; y < ySize; y ++){
            count = xSize - 1;
            for (int x = 0; x < xSize; x++){
                int rgba = bi.getRGB(x,y);
                newBi.setRGB(count, y, rgba);
                count --;
            }
        }
        
        for (int i = 0; i <ySize; i++){
            for (int j = 0; j < xSize; j ++){
                bi.setRGB(j,i,newBi.getRGB(j,i));
            }
        }
        
        return bi;
    }
    
    public static BufferedImage flipVertical(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        int count;
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        
        count = ySize - 1;
        for (int y = 0; y <ySize; y++){
            for (int x = 0; x< xSize; x++){
                int rgba = bi.getRGB(x,y);
                newBi.setRGB(x,count, rgba);
            }
            count --;
        }
        
        for (int i = 0; i <ySize; i++){
            for (int j = 0; j < xSize; j ++){
                bi.setRGB(j,i,newBi.getRGB(j,i));
            }
        }
        
        return bi;
    }
    
    public static BufferedImage greyScale(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        for (int y = 0; y < ySize; y++){
            for (int x = 0; x < xSize; x++){
                int rgba = bi.getRGB(x,y);
                int[] rgbValues = unpackPixel(rgba);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                int average = (red + green + blue)/3;
                
                if (average < 250){
                    average += 5;
                }
                
                int newColour = packagePixel (average, average, average, alpha);
                
                bi.setRGB(x,y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage sepia(BufferedImage bi)
    {
        
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage (bi.getWidth(), bi.getHeight(), 3);

        for (int y = 0; y < ySize; y++){
            for (int x = 0; x < xSize; x++){
                int rgba = bi.getRGB(x,y);
                int[] rgbValues = unpackPixel(rgba);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                int average = (red + green + blue)/3;
                
                //I found that these values work the best
                red = average + 50;
                green = average + 30;
                blue = average - 25;
                
                if (red > 255){
                    red = 255;
                }
                if (blue > 255){
                    blue = 255;
                }else if (blue < 0){
                    blue =0;
                }
                if (green > 255){
                    green = 255;
                }
                
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB(x,y, newColour);
            }
        }
        
        return newBi;
    }
    
    public static BufferedImage redify(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (red < 253){
                    red += 2;
                }
                    
                if (blue >= 50){
                  blue--;  
                }
                    
                if (green >= 50){
                    green--;
                }
                    

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage greenify(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (green < 253){
                    green += 2;
                }
                    
                if (blue >= 50){
                  blue--;  
                }
                    
                if (red >= 50){
                    red--;
                }
                    

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage increaseExposure (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                
                if (green < 250){
                    green += 5;
                }
                    
                if (blue <= 250){
                    blue+= 5;  
                }
                    
                if (red <= 250){
                    red+= 5;
                }
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage decreaseExposure (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                
                if (green > 5){
                    green -= 5;
                }
                    
                if (blue > 5){
                    blue-= 5;  
                }
                    
                if (red > 5){
                    red-=5;
                }
                    

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage negative (BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                    
                

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        
        return bi;
    }
    
    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * @param newBi The BufferedImage to convert.
     * 
     * @return GreenfootImage   A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);

        return returnImage;
    }

    
    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
    
    public static BufferedImage deepCopy(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }
    
    public static BufferedImage removeColor(BufferedImage bi, String removedColor){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (removedColor.equals("red")){
                    red = 0;
                }else if (removedColor.equals("green")){
                    green = 0;
                }else if (removedColor.equals("blue")){
                    blue = 0;
                }
                    

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    public static BufferedImage swapColors(BufferedImage bi, String swapColors){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                
                if (swapColors.equals("rg")){
                    newColour = packagePixel (green, red, blue, alpha);
                }else if (swapColors.equals("rb")){
                    newColour = packagePixel (blue, green, red, alpha);
                }else if (swapColors.equals("gb")){
                    newColour = packagePixel(red, blue, green,alpha);
                }
                    

                
                bi.setRGB (x, y, newColour);
            }
        }
        
        return bi;
    }
    
    
    
    
    
    
    
}
