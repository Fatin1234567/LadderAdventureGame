public class PlayableCharacter extends Character
{
    
    private int moveSpeed ; // default move speed
    private double powerUp; // you get power up after you defeat a defender
    private boolean toUsePower = false;
    
    public PlayableCharacter(String name,int health,int attack,int defence,int damage)
    {
        super(name,health,attack,defence,damage);
        powerUp =0;
        moveSpeed=1;
    }
    
    public String showAttributes()
    {
        return super.showAttributes()+" Move speed-"+moveSpeed;
    }
    
    public int showPowerPercentage()  // shows the percentage away from the next power up
    {
        if(this.powerUp - Math.floor(this.powerUp) == 0)
        {
            return 100;
        }
        else
        {
            double max = Math.ceil(this.powerUp);
            double per = (max-this.powerUp/1)*100;
            
            return (int)per;
        }
        
    }
    
    public void decreasePowerUp()  // default one, this gets overwritten 
    {
        this.powerUp = this.powerUp -1;
        
    }
    public void increasePowerUp()  //this method needs to get over written for all subclass
    {                             //as I want each character to get power up in a diffrent speed so the characters are balanced
        
    }
    public void setUsedPower(String yesOrNo)
    {
    	if(yesOrNo.equals("yes")) this.toUsePower = true;
    	else this.toUsePower = false;
    	
    }
    public boolean getToUsePower()
    {
    	return this.toUsePower;
    }
    public void setPowerUp(double newP)
    {
        this.powerUp = newP;
        
    }
    public double getPower()
    {
        return powerUp;
    }
    
    
    public int getMoveSpeed()
    {
        return moveSpeed;
    }
    public void setMoveSpeed(int newMoveSpeed)
    {
        moveSpeed = newMoveSpeed;
    }
    
    public String getRace()  // this method gets overwritten 
    {
        return "";
    }
    
    public String showDetailWhenPowerUsed()
    {
    	return "";
    }
  
    public int showPower()
    {
    	return 0;
    }
    
    
    
    
    
    
    

}
