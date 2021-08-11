import java.util.Random;

public class Wizard extends PlayableCharacter  implements changeBoard
{   
    
    private int escapePotion; // this allows wizard to escape 
    
    public Wizard(String name)
    {
        super(name, new Random().nextInt((10-8)+1)+8, new Random().nextInt((4-3)+1)+3, new Random().nextInt((4-2)+1)+2, new Random().nextInt((4-3)+1)+3);
        
        escapePotion = new Random().nextInt((4 - 2) + 1) + 2;
    }
    public String showAttributes()
    {
        return super.showAttributes()+" Escape Potion-"+escapePotion;
    }
    public void setAttack()            // when power up is used as wizard will have high attack point
    {
        if(super.getAttack()>4) 
        {
            int attack = super.getAttack() + 0;
            super.setAttack(attack);
        }
        else if(super.getAttack()<=6)
        {
            
            int attack = super.getAttack() + 1;
            super.setAttack(attack);
        }
        
    }
    public void setHealth()        // when the power up is used
    {
        int health = super.getHealth() + super.getRandom(6, 4);
        super.setHeath(health);
        
    }
    public void increasePowerUp()
    {
        double newP = super.getPower() + 0.5;
        super.setPowerUp(newP);
    }
    
    public int getEscapePotion()
    {
        return escapePotion;
    }
    public void usedEscapePotion() // when it used once then it should not be used again
    {
        escapePotion = escapePotion -1;
    }
    
    
    public boolean fight(Character a)
    {
        if(super.getToUsePower())
        {
            this.usedEscapePotion();
            super.setUsedPower("no");
            return true;
        }
        
       else
       {
            return super.fight(a); // refers to indirect super class method(Character)
       }
    }
    
    
    public String getRace() 
    {
        return "Wizard";
    }
    public String showDetailWhenPowerUsed()
    {
    	return "You have escaped\n Amount of escape point left-"+this.escapePotion;
    }
    
    public void changeBoardPosition(DisplayBoard a)
    {
    	a.setEscape(true);
    }
    
    public int showPower()
    {
    	return this.escapePotion;
    }
}
