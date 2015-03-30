package opentenek.game2048;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display
{
    private JFrame frame;
    private Canvas canvas;
    private BufferedImage img;
    private Graphics gfx;
    
    private Keyboard keyboard;
    
    public Display(String title, int width, int height) 
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new Canvas();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        gfx = img.getGraphics();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        keyboard = new Keyboard();
        canvas.addKeyListener(keyboard);
        
        canvas.requestFocus();
    }
    
    public Keyboard getKeyboard() 
    {
        return keyboard;
    }
    
    public void setColor(int col) 
    {
        gfx.setColor(new Color(col));
    }
    
    public void fillRect(int x, int y, int w, int h) 
    {
        gfx.fillRect(x, y, w, h);
    }
    
    public void roundRect(int x, int y, int w, int h, int a) 
    {
        gfx.fillRoundRect(x, y, w, h, a, a);
    }
    
    public void show() 
    {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) 
        {
            canvas.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight(), canvas);
        
        g.dispose();
        bs.show();
    }
    
    public int getWidth() 
    {
        return canvas.getWidth();
    }
    
    public int getHeight() 
    {
        return canvas.getHeight();
    }

    public void render( Board board )
    {
        setColor(0x77777a);
        fillRect(0, 0, img.getWidth(), img.getHeight());
        
        gfx.setFont(new Font("Courier New", Font.BOLD, 20));
        
        for(int x = 0; x < board.getWidth(); x++) 
        {
            for(int y = 0; y < board.getHeight(); y++) 
            {
                int val = board.getValue(x, y);
                
                if(val == 0) setColor(0x99999a); 
                else setColor(Color.HSBtoRGB(10/360f, (float)(Math.log(val)/Math.log(2))/11f, 1f));
                
                roundRect(x*100 + 5, y*100 + 5, 90, 90, 20);
                setColor(0x000000);
                if(val != 0) gfx.drawString("" + val, x*100 + 45, y*100 + 57);
            }
        }
    }
}
