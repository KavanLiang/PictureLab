/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;
import java.awt.Color;

/**
 *
 * @author Library
 */
public class PictureEffect 
{
    private SimplePicture img_;
    public PictureEffect(SimplePicture pic)
    {
        img_ = pic;
    }
    
    public SimplePicture zeroRed()
    {
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        
        int row = 0;
        for( Pixel[] rowOfPixels : pixels )
        {
            int column = 0;
            for( Pixel p : rowOfPixels )
            {
                Color zeroR = new Color(0, p.getGreen(), p.getBlue(), p.getAlpha());
                result.setBasicPixel(column, row, zeroR.getRGB());
                column++;
            }
            row++;
        }
        
        return result;
    }
    
    public SimplePicture zeroGreen()
    {
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        
        int row = 0;
        for( Pixel[] rowOfPixels : pixels )
        {
            int column = 0;
            for( Pixel p : rowOfPixels )
            {
                Color zeroG = new Color(p.getRed(), 0, p.getBlue(), p.getAlpha());
                result.setBasicPixel(column, row, zeroG.getRGB());
                column++;
            }
            row++;
        }
        
        return result;
    }
    
    public SimplePicture zeroBlue()
    {
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        
        for( int row = 0; row < pixels.length; row++ )
        {
            for( int column = 0; column < pixels[0].length; column++ )
            {
                Pixel p = pixels[row][column];
                Color zeroB = new Color(p.getRed(), p.getGreen(), 0, p.getAlpha());
                result.setBasicPixel(column, row, zeroB.getRGB());
            }
        }
        
        return result;
    }
    
    public SimplePicture andMask(int mask)
    {
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        
        for( int row = 0; row < pixels.length; row++ )
        {
            for( int column = 0; column < pixels[0].length; column++ )
            {
                Pixel p = pixels[row][column];
                int value = p.getColor().getRGB();
                result.setBasicPixel(column, row, value & mask);
            }
        }
        
        return result;
    }
    
    public SimplePicture vMirror()
    {
        int rows = img_.getHeight();
        int columns = img_.getWidth();
        int center = (columns+1)/2;
        
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < center; c++)
            {
                int mirrorC = columns - c - 1;
                int pixVal = pixels[r][c].getColor().getRGB();
                result.setBasicPixel(c, r, pixVal);
                result.setBasicPixel(mirrorC, r, pixVal);
            }
        }
        
        return result;
    }
    
    public SimplePicture hMirror()
    {
        int rows = img_.getHeight();
        int columns = img_.getWidth();
        int center = (rows+1)/2;
        
        Pixel[][] pixels = img_.getPixels2D();
        SimplePicture result = new SimplePicture(img_.getWidth(), img_.getHeight());
        for(int r = 0; r < center; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                int mirrorR = rows - r - 1;
                int pixVal = pixels[r][c].getColor().getRGB();
                result.setBasicPixel(c, r, pixVal);
                result.setBasicPixel(c, mirrorR, pixVal);
            }
        }
        
        return result;
    }
}
