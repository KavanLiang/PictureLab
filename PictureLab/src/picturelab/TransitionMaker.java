/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author joyce_c
 */
public class TransitionMaker 
{
    private int frames_;
    private int timeBetweenFrames_;
    private ImageBlender ib_;
    public TransitionMaker(ImageBlender ib, int frames, int timeBetweenFrames)
    {
        ib_ = ib;
        frames_ = frames;
        timeBetweenFrames_ = timeBetweenFrames;
    }
    
    public void produce(String path)
    {
        try
        {
            double percent = 0;
            SimplePicture transition = ib_.produce(percent);
            BufferedImage image = transition.getBufferedImage();
            ImageOutputStream output = new FileImageOutputStream(new File(path));
            GifSequenceWriter writer = new GifSequenceWriter(output, image.getType(), timeBetweenFrames_, false);
            writer.writeToSequence(image);

            for(int n = 1; n < frames_; n++)
            {
                System.out.println("Writing frame: " + n);
                percent = (double)n/(frames_ - 1);
                transition = ib_.produce(percent);
                writer.writeToSequence(transition.getBufferedImage());
            }

            writer.close();
            output.close();
        }
        catch(Exception e)
        {
            System.out.println("produce in Transition.java failed to write the transition to file\n" + e);
        }
    }
}
