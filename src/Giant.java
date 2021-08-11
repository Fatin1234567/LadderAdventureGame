import java.util.Random;

public class Giant extends PlayableCharacter
{
    private boolean oneHitKill; // attributes shows weather it has one  hit kill or not
    private int maxTime;
    public Giant(String name ) 
    {
        super(name, new Random().nextInt((15-10)+1)+10, new Random().nextInt((3-1)+1)+1, new Random().nextInt((6-4)+1)+4, new Random().nextInt((2-1)+1)+1);
        
        oneHitKill = new Random().nextBoolean();
        if(oneHitKill)
        {
            maxTime = new Random().nextInt((3-1)+1)+1;
        }
    }
    
    
    public String showAttributes()
    {
        return super.showAttributes()+" Ability to one hit kill-"+oneHitKill+"("+maxTime+")";
    }
    
    
    public void oneHitKillUsed()
    {
        maxTime = maxTime -1;
        
        if(maxTime<0)
        {
            this.oneHitKill= false;
        }
        
    }
    
    public void increasePowerUp()
    {
        double newP = super.getPower() +0.25;
        super.setPowerUp(newP);
        
    }
    public void setAttack()              // this when the power is used
    {   
        if(super.getAttack()<=2)
        {
            int attack = super.getAttack() + super.getRandom(2, 1);
            super.setAttack(attack);
        }
        else if(super.getAttack()<=3)
        {
            int attack = super.getAttack() + super.getRandom(1, 0);
            super.setAttack(attack);
        }
    }
    public void setHealth()               // when the power up is used 
    {
        int health = super.getHealth() + super.getRandom(4, 2);
        super.setHeath(health);
        
    }
    
    public boolean fight(Character a)
    {
        if(super.getToUsePower())
        {
            a.decreaseHealth(a.getHealth());
            oneHitKillUsed();
            super.setUsedPower("no"); // so it doesnt keep using power
            return true;
        }
        
       else
       {
            return super.fight(a); // refers to indirect super class method(Character)
       }
    }
    
    public String getRace()
    {
        return "Giant";
    }
    
    public int getOneHit()
    {
    	return this.maxTime;
    }
    
    public String showDetailWhenPowerUsed()
    {
    	return "You used one hit kill\n Amount of one hit kill left-"+this.maxTime;
    }
    
    public int showPower()
    {
    	return this.maxTime;
    }
        
        
    }
    
    
