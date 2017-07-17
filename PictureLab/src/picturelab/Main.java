/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;


/**
 *
 * @author Library
 */
public class Main {
    
    public static void main(String[] args)
    {        
        SimplePicture sp = new SimplePicture("wow.jpg");
        ASCII test = new ASCII(sp);
        test.generateArt("ASCIITEST", 1);
    }
    
}
