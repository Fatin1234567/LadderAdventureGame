import java.util.Random;

public class Goblin extends PlayableCharacter implements changeBoard
{
    
    private boolean canRunBack;
    private int amountRunBack=0; // this allows goblin to go back to previous position

    public Goblin(String name) 
    {
        super(name, new Random().nextInt((10-4)+1)+4, new Random().nextInt((2-1)+1)+1, new Random().nextInt((4-2)+1)+2, new Random().nextInt((3-1)+1)+1);
        
        canRunBack = new Random().nextBoolean();
        if(this.canRunBack)
        {
            amountRunBack = new Random().nextInt((6-4)+1)+4;
        }
        if(getHealth()<7)
        {
            super.setMoveSpeed(4);
        }
        else
        {
            super.setMoveSpeed(2);
        }
    
    }
    public String showAttributes()
    {
        return super.showAttributes()+" Ability to run back-"+canRunBack+"("+amountRunBack+") ";
    }
    public void usedRunBack()                      // takes one away when used
    {
        this.amountRunBack = this.amountRunBack-1;
    }
    public int getRunBack()
    {
        return this.amountRunBack;
    }
    public void checkCanRunBack()                 // checks weather ability is true or false
    {
        if(this.amountRunBack<0)
        {
            this.canRunBack = false;
            
        }
    }
    
    public void increasePowerUp()
    {
        double newP = super.getPower() + 2;
        super.setPowerUp(newP);
    }
    
    public void setAttack()  // when power up is used
    {
        if(super.getAttack()<=1)
        {
            int attack = super.getAttack() + super.getRandom(1, 0);
            super.setAttack(attack);
        }
    }
    public void setHealth()        // when power up is used
    {
        int health = super.getHealth() + super.getRandom(5, 1);
        super.setHeath(health);
        
    }
    
    
    public String getRace()
    {
        return "Goblin";
    }
     
    public boolean fight(Character a)
    {
        if(super.getToUsePower())
        {
            this.usedRunBack();
            this.checkCanRunBack();
            super.setUsedPower("no");  // resets the power used
            return true;
        }
        
       else
       {
            return super.fight(a); // refers to indirect super class method(Character)
       }
    }
    
    public String showDetailWhenPowerUsed()
    {
     return "You return to your previous position\namount of run back-"+this.amountRunBack;
    }
    
    public void changeBoardPosition(DisplayBoard a)
    {
    	a.runBackUsed();
    }
    
    public int showPower()
    {
    	return this.amountRunBack;
    }
    
    
    
    
}
