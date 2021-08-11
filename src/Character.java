import java.util.Random;

public class Character
{
    
    private String name;
    private int health;
    private int attack;
    private int defence;
    private int damage;
    private boolean alive=true;
   
    
    private boolean landHit;
    
    public Character(String name,int health,int attack,int defence,int damage)
    {
        this.name = name;
        this.health= health;
        this.attack = attack;
        this.defence = defence;
        this.damage = damage;
        
    }
    


    public String showAttributes() // prints character attributes
    {
        return "Your chracter name is "+name+"\nHealth-"+health+" Attack-"+attack+" Defence-"+defence+" damage- "+damage;
    }
    
    
    
    public void decreaseHealth(int damage) 
    {
        int newHealth = this.health - damage; // so it doesnt show negative health
        
        if(newHealth<0)
        {
            this.health = 0;
        }
        else
        {
            this.health = newHealth;
        }
    }
    public void checkHealth()  // checks weather character is alive
    {
        if(this.health<=0)
        {
            this.alive = false;
        }
    }
    
    public void setHeath(int newHealth) 
    {
        this.health = newHealth;
        
    }
    public void setHealth()   // this used to give extra health, non player gets it in form of level whereas player gets as power up
    {
        
        
    }
    public void setAttack(int newAttack) 
    {
        attack = newAttack;
        
    }
    public void setAttack()  // this used to give extra attack, non player gets it in form of level whereas player gets as power up
    {
        
        
    }
    public void setDefence(int newDefence)
    {
        this.defence = newDefence;
        
    }
    public void setAlive()
    {
        this.alive = false;
    }
   
    public int getHealth()
    {
        return this.health;
    }
    
    
    public String getName()
    {
        return name;
    }
    public int  getAttack()
    {
        return attack;
    }
    public int  getDefence()
    {
        return defence;
    }
    public int  getDamage()
    {
        return damage;
    }
    
    public boolean getAlive()
    {
        return alive;
    }
    
    
    public boolean fight(Character a)  // generic fight method
    {


        if(this.attack>= a.getDefence())
        {
        	landHit = true;
            a.decreaseHealth(this.damage);
        }
        else
        {
        	landHit = false;
           
        }
        
        return false;
    }
    
     
    public boolean getLandHit()
    {
    	return this.landHit;
    }
    public void setLandHit()
    {
    	this.landHit = false;
    }
    
    public static int getRandom(int max,int min)
    {
        Random a = new Random();
        
        int randomNum = a.nextInt((max-min)+1)+min;
        return randomNum;
    }
    
    
    

}
