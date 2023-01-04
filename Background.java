import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.util.List;
import java.awt.image.BufferedImage;

/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "nature.jpg";
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 720;

    // Objects and Variables:
    private ImageHolder image;

    private TextButton blueButton, hRevButton, openButton, rotateCWButton, rotateCCWButton, sepiaButton, greyScaleButton, redifyButton, greenifyButton, saveButton, rotate180Button, undoButton, redoButton, increaseExposureButton, decreaseExposureButton, negativeButton, verticalFlipButton, removeRedButton, removeGreenButton, removeBlueButton, swapRGButton,swapRBButton, swapGBButton;
    private SuperTextBox openFile;

    private String fileName;
    
    private static ArrayList<GreenfootImage> imageStorage;
    private static int arrayIndex;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        super(1024, 860, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE); // The image holder constructor does the actual image loading
        
        // Set up UI elements
        blueButton = new TextButton("Blueify", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
    
       // blueButton.setFixedWidth(160); // setting a fixed width so buttons will be the same width
        hRevButton = new TextButton("Flip Horizontal", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
  
        openButton = new TextButton ("Open", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        //openButton.setFixedWidth(80);
        
        rotateCWButton = new TextButton("Rotate CW", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        rotateCCWButton = new TextButton("Rotate CCW", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        sepiaButton = new TextButton("Sepia", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        greyScaleButton = new TextButton("Greyscale", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        redifyButton = new TextButton("Redify", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
       
        greenifyButton = new TextButton("Greenify", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        saveButton =  new TextButton("save", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        rotate180Button = new TextButton("Rotate 180", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        undoButton = new TextButton("undo", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        redoButton = new TextButton("redo", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        increaseExposureButton = new TextButton("+ Exposure", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));

        decreaseExposureButton = new TextButton("- Exposure", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        negativeButton = new TextButton("Negative", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        verticalFlipButton = new TextButton("Flip Vertical", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        removeRedButton = new TextButton("Remove Red", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        removeGreenButton = new TextButton("Remove Green", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        removeBlueButton = new TextButton("Remove Blue", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        swapRGButton = new TextButton("Swap R-G", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        swapRBButton = new TextButton("Swap R-B", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));

        swapGBButton = new TextButton("Swap G-B", 5, 130, true, Color.WHITE, Color.WHITE, new Color (4,108,221), new Color (51,156,251), Color.WHITE, new Font ("Verdana",false ,false ,16));
        
        openFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Color (4,108,221), Color.WHITE,new Font ("Comic Sans MS", false, false, 16),true, 600, 5,Color.WHITE);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");

        int xAdjustment = -77;
        
        // Add objects to the screen
        addObject (image, 440, 460);
        
        addObject (rotateCWButton, 940, 230 + xAdjustment);
        addObject (rotateCCWButton, 940, 265+ xAdjustment);
        addObject (rotate180Button, 940, 300 + xAdjustment);
        addObject (hRevButton, 940, 335+ xAdjustment);
        addObject (verticalFlipButton, 940, 370+ xAdjustment);
        
        addObject (redifyButton, 940, 405+ xAdjustment);
        addObject (greenifyButton, 940, 440+ xAdjustment);
        addObject (blueButton, 940, 475+ xAdjustment);
        
        addObject (removeRedButton, 940, 510 + xAdjustment);
        addObject (removeGreenButton, 940, 545 + xAdjustment);
        addObject (removeBlueButton, 940, 580 + xAdjustment);
        
        addObject (swapRGButton, 940, 615+ xAdjustment);
        addObject (swapRBButton, 940, 650+ xAdjustment);
        addObject (swapGBButton, 940, 685+ xAdjustment);
        
        addObject (greyScaleButton, 940, 720+ xAdjustment);
        addObject (sepiaButton, 940, 755+ xAdjustment);
        addObject (negativeButton, 940,790+ xAdjustment);
        
        addObject (increaseExposureButton, 940, 825+ xAdjustment);
        addObject (decreaseExposureButton, 940, 860+ xAdjustment);
        
        
        addObject(undoButton, 84, (openFile.getImage().getHeight() / 2) - 10);
        addObject (redoButton, 84,(openFile.getImage().getHeight() / 2) + 30);
        
        addObject(openButton, 940, (openFile.getImage().getHeight() / 2) - 10);
        
        // place the open file text box in the top left corner
        addObject (openFile, 512, (openFile.getImage().getHeight() / 2) + 10);
        // place the open file button directly beside the open file text box
        addObject (saveButton, 940, (openFile.getImage().getHeight() / 2) + 30);
        
        imageStorage = new ArrayList<GreenfootImage>();
        arrayIndex = 0;
        imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(blueButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.blueify(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(hRevButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.flipHorizontal(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(openButton)){
                openFile ();
            }else if (Greenfoot.mouseClicked(redifyButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.redify(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(greenifyButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.greenify(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(sepiaButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.sepia(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
                
            }else if (Greenfoot.mouseClicked(greyScaleButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.greyScale(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
                
            }else if (Greenfoot.mouseClicked(rotateCWButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.rotate90Clockwise(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(rotateCCWButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.rotate90CounterClockwise(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(rotate180Button)){
               image.setNewImage(createGreenfootImageFromBI(Processor.rotate180(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(increaseExposureButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.increaseExposure(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(decreaseExposureButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.decreaseExposure(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(negativeButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.negative(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(undoButton)){
                undo();
            }else if (Greenfoot.mouseClicked(redoButton)){
                redo();
            }else if (Greenfoot.mouseClicked(saveButton)){
                saveFile();
            }else if (Greenfoot.mouseClicked (verticalFlipButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.flipVertical(deepCopy(image.getBufferedImage()))));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(removeRedButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.removeColor(deepCopy(image.getBufferedImage()), "red")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(removeGreenButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.removeColor(deepCopy(image.getBufferedImage()), "green")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(removeBlueButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.removeColor(deepCopy(image.getBufferedImage()), "blue")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }
            else if (Greenfoot.mouseClicked(swapRGButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.swapColors(deepCopy(image.getBufferedImage()), "rg")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(swapRBButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.swapColors(deepCopy(image.getBufferedImage()), "rb")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }else if (Greenfoot.mouseClicked(swapGBButton)){
               image.setNewImage(createGreenfootImageFromBI(Processor.swapColors(deepCopy(image.getBufferedImage()), "gb")));
               imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
               arrayIndex++;
               updateImageStorage();
            }
            
            
        }
    }

    // Code provided, but not yet implemented - This will save image as a png.
    private void saveFile () {
        try{
            // This will pop up a text input box - You should improve this with a JFileChooser like for the open function
            String fileName = JOptionPane.showInputDialog("Input file name (no extension)");

            fileName += ".png";
            File f = new File (fileName);  
            ImageIO.write(image.getBufferedImage(), "png", f); 

        }
        catch (IOException e){
            // this code instead
            System.out.println("Unable to save file: " + e);
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();
        // Create a JFileChooser, a file chooser box included with Java 
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (image.openFile (selectedFile.getAbsolutePath(), selectedFile.getName()))
            {
                //String display = " [ Open File: " + selectedFile.getName() + " ] ";
                openFile.update (image.getDetails ());
            }
        }
        // If the file opening operation is successful, update the text in the open file button
        /**if (image.openFile (fileName))
        {
        String display = " [ Open File: " + fileName + " ] ";
        openFile.update (display);
        }*/
        
        imageStorage = new ArrayList<GreenfootImage>();
        arrayIndex = 0;
        imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
        
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
    
    public static BufferedImage deepCopy(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }
    
    public void undo(){
        if (arrayIndex == 0){
            arrayIndex = 0;
        }else {
            arrayIndex--;
            image.setNewImage(createGreenfootImageFromBI(deepCopy((imageStorage.get(arrayIndex)).getAwtImage())));
        }
        
        
        
        //System.out.print("[");
        
        for (int i = 0;i < imageStorage.size(); i++){
            //System.out.print(i + ", ");
        }
        
        //System.out.print("]\n");
    }
    
    public void redo(){
        if (arrayIndex == imageStorage.size() -1){
            arrayIndex = imageStorage.size() - 1;
        }else {
            arrayIndex++;
            image.setNewImage(createGreenfootImageFromBI(deepCopy((imageStorage.get(arrayIndex)).getAwtImage())));
        }
        
        
        
        //System.out.print("[");
        
        for (int i = 0;i < imageStorage.size(); i++){
            //System.out.print(i + ", ");
        }
        
        //System.out.print("]\n");
    }
    
    public void updateImageStorage(){
        
        List<GreenfootImage> temp = imageStorage.subList(0, arrayIndex);
        
        imageStorage = new ArrayList<GreenfootImage>(temp);
        
        imageStorage.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
        //System.out.print("[");
        
        for (int i = 0;i < imageStorage.size(); i++){
            //System.out.print(i + ", ");
        }
        
        //System.out.print("]\n");
        
    }

}