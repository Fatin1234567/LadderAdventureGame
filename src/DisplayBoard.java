import java.util.Random;

public class DisplayBoard {                 // this class models the display board of the game
    
    
    private String[][] board;
    private int sizeXY;
    private int maxYposition;
    private int maxXposition;
    private int currentXposition=0;
    private int currentYposition=0;
    private int amountOFdefender;
    private int previousXposition;
    private int previousYposition;
    private String defenderLogo = "D";
    private String playerLogo="P";
    private int moveSpeed;
    private boolean finishGame = false;
    
    
    private String currentMoveSpeed;
    
    private boolean escapeUsed = false;  // only used when the wizard escapes
    private boolean runBackUsed = false; //only used when goblin runs back
    
    public DisplayBoard(int level,int moveSpeed) // checks the level depending on that it will create the display board
    {
        int sizeXY=0;                            
        this.moveSpeed = moveSpeed;
        if(level==1)
        {
            sizeXY=5;
            int defender=1;
            intialiseBoard(sizeXY,defender);
            
        }
        else if(level ==2)
        {
            sizeXY=10;
            int defender=3;
            intialiseBoard(sizeXY,defender);
        }
        else if(level ==3)
        {
            sizeXY=12;
            int defender=5;
            intialiseBoard(sizeXY,defender);
        }
        
    }
    public void intialiseBoard(int size,int defender)
    {
        
        maxYposition = size-1;
        maxXposition = size-1;
        board = new String[size][size];
        this.sizeXY= size;
        amountOFdefender = defender;
        
        
    }
    public void createBoard()
    {
        
        for(int y=0;y<sizeXY;y++)
        {
            for(int x=0;x<sizeXY;x++)
            {
                if(y==0 && x==0)
                {
                    board[y][x] = "_"+playerLogo+"_";
                }
                else if(y== (sizeXY-1) && x==(sizeXY-1))
                {
                    board[y][x] = "_E_";
                }
                else
                {
                    board[x][y]="___";
                }
            }
        }
        
        
    }
    public String print()
    {
    	String total ="";
        for(int y=(sizeXY-1);y>=0;y--)
        {
            for(int x=0;x<sizeXY;x++)
            {
                total = total+" | "+board[y][x]+" |";
            }
            total = total+"\n";
        }
        return total;
        
    }
    
    public void enterDefender() // enter the defenders
    {
        Random generateRandomNum = new Random();
        
        for(int y=0;y<sizeXY;y++)
        {
            int currentDefender=0;
            do 
            {   
                int x = generateRandomNum.nextInt(maxXposition);
                if(addBoard(y,x)) { currentDefender++;} // depending on the level it chooses the amount of defenders
                
            }while(currentDefender!=amountOFdefender);
        }
    }
    public boolean addBoard(int y,int x)  // add defender in the board
    {
        boolean done = false;
        if(!(board[y][x].equals(playerLogo)) && (!board[y][x].equals(defenderLogo)) && (!board[y][x].equals("E")))
        {
            board[y][x] = "_"+defenderLogo+"_";
            done =true;
        }
        return done;
        
    }
    
    public void moveCharacterOntheBoard()
    {
        
        int b =0;
        Random a = new Random();
        int randomNum = a.nextInt(2);
        if(currentYposition==maxYposition) // in the final floor all the character move one step at a time
        {
        
            b =  1;
            currentMoveSpeed = Integer.toString(b);
            
        }
        else
        {
            b = randomNum+this.moveSpeed;
            currentMoveSpeed = Integer.toString(b);
            
        }
        
        
        previousXposition = currentXposition;
        previousYposition = currentYposition;
        currentXposition = currentXposition+b;
        
    }
    public void check()                   // checks weather you have to move up or not
    { 
        
        if(currentXposition>maxXposition)
        {
            currentXposition = (currentXposition - maxXposition)-1; // since starts from 0
            
            if(currentYposition<maxYposition)
            {
                currentYposition++;
            }  
            
        }
        
    }
    public boolean checkForFight()    // check  if monster in the space place as players
    {
        if(board[currentYposition][currentXposition].equals("D+P")) { return true;}
        else {return false;}
    }
    public void place(boolean alive) // places the character in the right path
    {
        if(alive)
        {
            
            
            if(this.escapeUsed)
            {
                board[previousYposition][previousXposition] ="_D_"; 
                setEscape(false);              // so this doesnt happen again
            }
            else
            {
                board[previousYposition][previousXposition] ="___"; 
                
            }
            
            
            if(board[currentYposition][currentXposition].equals("_D_"))
            {
                board[currentYposition][currentXposition] = "D+P";
            }
            else
            {
                board[currentYposition][currentXposition] = "_"+this.playerLogo+"_";
            }
        }
        else
        {
            board[previousYposition][previousXposition] = defenderLogo;   
            board[currentYposition][currentXposition] = playerLogo;   
        }
    }
    
    public boolean checkIFgameFinish()
    {
        if(currentYposition==maxYposition && currentXposition==maxXposition) return true; // this is when you reach 'E' where the game will end
       
        else return false;
    }
    
    public void setFinishGame()
    {
        this.finishGame = true;
        
    }
    
    public void setEscape(boolean a)
    {
        this.escapeUsed = a;
    }
    
    public void runBackUsed()                          // allows the goblin to go back
    {
        
        board[this.currentYposition][this.currentXposition] ="_D_"; 
     
        this.currentXposition= this.previousXposition;
        this.currentYposition = this.previousYposition;
        board[this.currentYposition][currentXposition] ="_P_";
    
        
    }
    public int getMaxYPosition()
    {
        return this.maxYposition;
    }
    public int getMaxXPosition()
    {
        return this.maxXposition;
    }
    
    public int getCurrentXposition()
    {
        return this.currentXposition;
    }
    public int getCurrentYposition()
    {
        return this.currentYposition;
    }
    
    public String getMove()
    {
    	return "Speed generated: "+this.currentMoveSpeed;
    }

}
