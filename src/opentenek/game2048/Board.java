package opentenek.game2048;

public class Board
{
    private Tile tiles[];
    private int width, height;
    
    public Board(int w, int h) 
    {
        width = w;
        height = h;
        tiles = new Tile[w*h];
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void spawnTile(int x, int y, int val) 
    {
        if(!inBounds(x, y)) return;
        
        tiles[x + y*width] = new Tile(x, y, val);
    }
    
    public void spawnRandomTile() 
    {
        int x, y;
        do 
        {
            x = (int)(Math.random() * width);
            y = (int)(Math.random() * height);
        } while(tiles[x + y*width] != null);
        
        int val = Math.random() < .9 ? 2 : 4;
        
        spawnTile(x, y, val);
    }
    
    public Tile getTile(int x, int y) 
    {
        if(inBounds(x, y)) return tiles[x + y*width];
        return null;
    }
    
    private void setTile(int x, int y, Tile t) 
    {
        if(inBounds(x, y)) tiles[x + y*width] = t;
    }
    
    public void moveUp() 
    {
        for(int y = 0; y < height; y++) 
            for(int x = 0; x < width; x++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                move(x, y, 0, -1);
            }
    }
    
    public void moveDown() 
    {
        for(int y = height - 1; y >= 0; y--) 
            for(int x = 0; x < width; x++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                move(x, y, 0, 1);
            }
    }
    
    public void moveLeft() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                move(x, y, -1, 0);
            }
    }
    
    public void moveRight() 
    {
        for(int x = width - 1; x >= 0; x--)
            for(int y = 0; y < height; y++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                move(x, y, 1, 0);
            }
    }
    
    private void move(int x, int y, int dx, int dy) 
    {
        int xx = x, yy = y;
        if(getTile(x, y) == null) return;
        while(inBounds(xx + dx, yy + dy)) 
        {
            Tile t = getTile(xx, yy);
            if(getTile(xx + dx, yy + dy) == null) 
            {
                setTile(xx, yy, null);
                setTile(xx + dx, yy + dy, t);
                t.setLocation(new Point(xx + dx, yy + dy));
            } else if(getTile(xx + dx, yy + dy).getValue() == t.getValue() && t.canCombine() && getTile(xx + dx, yy + dy).canCombine()) 
            {
                t.setValue(t.getValue() * 2);
                setTile(xx + dx, yy + dy, t);
                setTile(xx, yy, null);
                t.setLocation(new Point(xx + dx, yy + dy));
                t.setToReal();
                t.setCanCombine(false);
            }
            
            xx += dx;
            yy += dy;
        }
    }
    
    public void update() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
                if(tiles[x + y*width] != null) 
                {
                    tiles[x + y*width].update();
                }
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
