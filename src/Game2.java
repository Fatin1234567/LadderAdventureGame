import java.util.Random;

public class Game2 
{
	final int numOFplayableCharacter =7;
	private Character[] characters =new Character[ numOFplayableCharacter];
	private Character player;
	private Character defender;
	
	private Character whoAttacks;
	
	
	
	
	public  void addPlayerCharacter() // where fill player character is filled
    {
        String[] names = {"Lio","Ezio","Ban","Pit","Rayman","Kong","Wrex","Spyro","Cacus","Argus","Geryon","Gigantes"};
        int giantCount =0;
        int wizardCount =0; 
        int goblinCount =0; // makes sures that they is at least one of each race
        
        do
        {
             giantCount =0;
             wizardCount =0;
             goblinCount =0;
            for(int x=0;x<characters.length;x++)                 // substitution principle  below
            {
                
                if(new Random().nextBoolean())
                {
                	characters[x] = new  Giant(names[new Random().nextInt(12)]);      // sub
                    giantCount++;
                }
                else if(new Random().nextBoolean())
                {
                	characters[x] = new  Wizard(names[new Random().nextInt(12)]);
                    wizardCount++;
                }
                else
                {
                	characters[x] = new Goblin(names[new Random().nextInt(12)]);
                    goblinCount++;
                }
            }
            
        } while(!(giantCount>=1) || !(wizardCount>=1) || !(goblinCount>=1)); // makes sure there is atleast one of all race
        
    }
	
	
	 
	 public void  found(String name) // finds and assign character
	 {
		 
         String[] nameANDrace = name.split(" ");            // allows to find the right character if they have same name
	   
	        
         for(int x=0;x<characters.length;x++)
         {
             if(nameANDrace[0].equals(characters[x].getName()) && nameANDrace[1].equals(((PlayableCharacter)characters[x]).getRace()))
             {
                 this.player = characters[x];
             }
         }
	        
	  }
	public  Character GenerateDefender()
    {
        String[] names = {"Demon","Orc","Vampire","Drow Elf"};
        
        
        Character a = new  NonPlayableCharacter(names[new Random().nextInt(4)]);
        a.setAttack();                                                  // extra health and attack according to the level
        a.setHealth();                                                    
        // use of  polymorphism
        return a;
    }
	public void assignDefender()
	{
		defender = GenerateDefender();
	}
	public String showNameAndRace(Character a)
 	{
	 	return a.getName() +" "+((PlayableCharacter)a).getRace();
	}
	
	public boolean fightSequence() // dont use displayBoard use only on gui
	{
		boolean exitFight;
		if(new Random().nextBoolean())
		{
			this.whoAttacks = player;
			exitFight= player.fight(defender);
		}      
        else 
        {
        	this.whoAttacks = defender;
        	exitFight= defender.fight(player);
        }
		
		player.checkHealth();
		defender.checkHealth();
		return exitFight;
		
	}
	
	
	
	
	public Character[] getCharacters()
	{
		return this.characters;
	}
	public Character getPlayer()
	{
		return this.player;
	}
	
	
	public Character getWhoAttacks()
	{
		return this.whoAttacks;
	}
	public Character getDefender()
	{
		return this.defender;
	}
 
	
	 

}
