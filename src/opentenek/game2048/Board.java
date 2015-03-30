package opentenek.game2048;

public class Board
{
    private int values[];
    private int width, height;
    
    public Board(int w, int h) 
    {
        width = w;
        height = h;
        values = new int[w*h];
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public int getValue(int x, int y) 
    {
        if(inBounds(x, y)) return values[x + y * width];
        return -1;
    }
    
    public boolean emptySpace() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
                if(getValue(x, y) == 0) return true;
        
        return false;
    }
    
    public void addRandomValue() 
    {
        if(!emptySpace()) return;
        
        int val = Math.random() > 0.5 ? 4 : 2;
        int x, y;
        do 
        {
            x = (int)(Math.random()*width);
            y = (int)(Math.random()*height);
        } while(getValue(x, y) != 0);
        setValue(x, y, val);
    }
    
    public void moveUp() 
    {
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++) 
            {
                int val = getValue(x, y);
                int yy = y;
                while(getValue(x, yy-1) == 0) 
                {
                    setValue(x, yy - 1, val);
                    setValue(x, yy, 0);
                    yy--;
                }
                if(getValue(x, yy-1) == val) 
                {
                    setValue(x, yy-1, 2 * val);
                    setValue(x, yy, 0);
                }
            }
    }
    
    public void moveDown() 
    {
        for(int y = height - 1; y >= 0; y--)
            for(int x = 0; x < width; x++) 
            {
                int val = getValue(x, y);
                int yy = y;
                while(getValue(x, yy+1) == 0) 
                {
                    setValue(x, yy + 1, val);
                    setValue(x, yy, 0);
                    yy++;
                }
                if(getValue(x, yy+1) == val) 
                {
                    setValue(x, yy+1, 2 * val);
                    setValue(x, yy, 0);
                }
            }
    }
    
    public void moveLeft() 
    {
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++) 
            {
                int val = getValue(x, y);
                int xx = x;
                while(getValue(xx-1, y) == 0) 
                {
                    setValue(xx-1, y, val);
                    setValue(xx, y, 0);
                    xx--;
                }
                if(getValue(xx-1, y) == val) 
                {
                    setValue(xx-1, y, 2 * val);
                    setValue(xx, y, 0);
                }
            }
    }
    
    public void moveRight() 
    {
        for(int x = width -1; x >= 0; x--)
            for(int y = 0; y < height; y++) 
            {
                int val = getValue(x, y);
                int xx = x;
                while(getValue(xx+1, y) == 0) 
                {
                    setValue(xx+1, y, val);
                    setValue(xx, y, 0);
                    xx++;
                }
                if(getValue(xx+1, y) == val) 
                {
                    setValue(xx+1, y, 2 * val);
                    setValue(xx, y, 0);
                }
            }
    }
    
    public boolean inBounds(int x, int y) { return x >= 0 && x < width && y >= 0 && y < height; }
    
    public void setValue(int x, int y, int val) 
    {
        if(inBounds(x, y)) values[x + y * width] = val;
    }
}
