package opentenek.game2048;

public class Game extends GameLoop
{
    public Board board;
    public Display display;
    public Keyboard keyboard;
    
    public Game() 
    {
        super(1000, 60);
        
        display = new Display("2048", 500, 500);
        keyboard = display.getKeyboard();
        
        board = new Board(4, 4);
        
        board.addRandomValue();
        board.addRandomValue();
    }
    
    public void update() 
    {
        keyboard.update();
        
        boolean moved = true;
        
        if(keyboard.isKeyTyped(Keyboard.UP))         board.moveUp();
        else if(keyboard.isKeyTyped(Keyboard.DOWN))  board.moveDown();
        else if(keyboard.isKeyTyped(Keyboard.LEFT))  board.moveLeft();
        else if(keyboard.isKeyTyped(Keyboard.RIGHT)) board.moveRight();
        else moved = false;
        
        // TODO: Should be if can't move
        if(!board.emptySpace()) 
        {
            System.out.println("You lose");
            System.exit(0);
        }
        
        if(moved) board.addRandomValue();
    }
    
    public void render() 
    {
        display.render(board);
        
        display.show();
    }
}