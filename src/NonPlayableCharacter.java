import java.util.Random;

public class NonPlayableCharacter extends Character
{
    private int level;
    private int currentRandomPower;
    
    
    public NonPlayableCharacter(String name)
    { 
        super(name, new Random().nextInt((10-8)+1)+8, new Random().nextInt((2-1)+1)+1, new Random().nextInt((2-1)+1)+1, new Random().nextInt((2-1)+1)+1);
        this.level = probabilityOFlevel();
    }
    
    public double getLevel()
    {
        return this.level;
    }
    
    public void setHealth()  // according to the level
    {
        
        
        if(this.level>=2 && this.level<=3)
        {
            
            int health = super.getHealth() +2;
            super.setHeath(health);
        }
        else if(this.level>=4 && this.level<=5)
        {
            
            int health = super.getHealth() +4;
            super.setHeath(health);
        }
    }
    public void setAttack() // according to the level
    {
        
        if(this.level>=2 && this.level<=3)
        {
            int attack = super.getAttack() +1;
            super.setAttack(attack);
        }
        else if(this.level>=4 && this.level<=5)
        {
            int attack = super.getAttack() +2;
            super.setAttack(attack);
        }
        
    }
    public static int probabilityOFlevel() // to set the level for the non player
    {
        if((new Random().nextInt(100) + 1) < 10)
        {
            return 5;
        }
        else if((new Random().nextInt(100) + 1) < 30)
        {
            return 4;
        }
        else if((new Random().nextInt(100) + 1) < 35)
        {
            return 3;
        }
        else
        {
            return new Random().nextInt((1 - 1) + 1) + 1;
        }
    }
    public boolean fight(Character a) // defender can always gain power(even 0) during each sub battle
    {
    	currentRandomPower =new Random().nextInt(4);
        int oldAttack = super.getAttack();
        super.setAttack(super.getAttack() + currentRandomPower); // defender can always borrow strength from the nature  
        int feedbackOFblessing = new Random().nextInt(2);
        super.setDefence(super.getDefence()-feedbackOFblessing); // decrease their defence 
       
      
        boolean b =  super.fight(a);
        super.setAttack(oldAttack); // this makes sures the extra power doesnt keep accumilating 
        
        return b;
    }
    
    
    
    

}
