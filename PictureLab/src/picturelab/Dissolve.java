/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;

/**
 *
 * @author joyce_c
 */
public class Dissolve implements ImageBlender
{
    private SimplePicture orig_;
    private SimplePicture next_;
    private int[] pixelSequence_;
    public Dissolve(SimplePicture original, SimplePicture upcoming)
    {
        orig_ = original;
        next_ = upcoming;
        
        pixelSequence_ = new int[orig_.getHeight() * orig_.getWidth()];
        for( int n = 0; n < pixelSequence_.length; n++ )
            pixelSequence_[n] = n;
        
        //shuffle the array
        for( int k = 0; k < pixelSequence_.length; k++ )
        {
            int target = (int)(Math.random()*pixelSequence_.length);
            int temp = pixelSequence_[k];
            pixelSequence_[k] = pixelSequence_[target];
            pixelSequence_[target] = temp;
        }
    }
    
    public SimplePicture produce(double percentageBlend)
    {
        Pixel[][] orig = orig_.getPixels2D();
        Pixel[][] upcoming = next_.getPixels2D();
        int cols = orig_.getWidth();
        SimplePicture blended = new SimplePicture(orig_.getWidth(),orig_.getHeight());
        int last = (int)(percentageBlend*pixelSequence_.length);
        
        for(int n = 0; n < last; n++)
        {
            int target = pixelSequence_[n];
            int targetR = target/cols;
            int targetC = target%cols;

            blended.setBasicPixel(targetC, targetR, upcoming[targetR][targetC].getColor().getRGB());
        }

        for(int n = last; n < pixelSequence_.length; n++)
        {
            int target = pixelSequence_[n];
            int targetR = target/cols;
            int targetC = target%cols;

            blended.setBasicPixel(targetC, targetR, orig[targetR][targetC].getColor().getRGB());
        }

        return blended;
    }
}
