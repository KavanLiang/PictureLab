/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;

import java.util.*;
import java.io.*;
/**
 *
 * @author Kavan
 */
public class ASCII 
{
    private Pixel[][] pixels;
    //I CAN'T THINK OF A WAY TO NAME 50 SHADES OF GREY
    final private static int SUPER_DARK = 28, PRETTY_DARK = 56, SLIGHTLY_DARK = 84, MEDIUM = 112, SLIGHTLY_LIGHT = 138, SLIGHTLY_LIGHTER = 166, LIGHT = 194, PRETTY_LIGHT = 222, SUPER_LIGHT = 250;
    
    public ASCII(SimplePicture sp)
    {
        pixels = sp.getPixels2D();
    }
    
    public void generateArt(String fileName, int size)
    {
        File file = new File(fileName + ".html");
        try
        {
            PrintWriter pw = new PrintWriter(file);
            pw.println("<!doctype html>");
            pw.println("<body>");
            for(int y = 0; y < pixels.length; y++)
            {
                pw.print("<p><font size = \"" + size + "\" face = \"Courier New\"><strong>");
                for(int x  = 0; x < pixels[y].length; x++)
                {
                    pw.print(interperate(pixels[y][x]));
                }
                pw.print("</strong></p>");
            }
            pw.println("</body>");
            pw.println("</html>");
            pw.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    private String interperate(Pixel p)
    {
        double average = p.getAverage();
        if(average <= SUPER_DARK)
            return "@@@@";
        else if(average <= PRETTY_DARK)
            return "%%%%";
        else if(average <= SLIGHTLY_DARK)
            return "####";
        else if(average <= MEDIUM)
            return "oooo";
        else if(average <= MEDIUM)
            return "++++";
        else if(average <= SLIGHTLY_LIGHTER)
            return "====";
        else if(average <= LIGHT)
            return "----";
        else if(average <= PRETTY_LIGHT)
            return "::::";
        else if(average <= SUPER_LIGHT)
            return ",,,,";
        else
            return "....";
        
    }
    
}
