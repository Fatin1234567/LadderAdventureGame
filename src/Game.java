import java.util.Random;
import java.util.Scanner;


public class Game  // This Game plays like typical snakes and ladder styles but has twist which is escaping from the defenders or defeating them to reach the end.
                   // containts level which is bigger display board with more defedner.
{
    
    
    
    public static void main(String[] args)
    {
        final int numOFdefender =10;
        final int numOFplayableCharacter = 7;
        
        
        
        Character[] playableCharacter = new Character[ numOFplayableCharacter];
        addPlayerCharacter(playableCharacter); // stores character which can chosen by the host
        
        System.out.println("Welcome to this game");
        System.out.println("Are you ready to escape?");
        int level  = Integer.parseInt(userInput("Now chose the choose the difficulty, 1 being easy 2 being medium and 3 being really hard"));
    
        System.out.println("Get ready, and chose a character that you think is capable of escaping...");
        System.out.println("Note that each race has different power. Wizard  can escape from a battle, Giant can one hit kill and Gobin can move to previous position");
        System.out.println();
        
        
        
        showCharacter(playableCharacter);  
        String name = userInput("Enter the name of the character and their race that you would like to be eg Bob Giant");
        
        Character playerCharacter = found(playableCharacter,name);
        
        
        DisplayBoard a = new DisplayBoard(level,((PlayableCharacter)playerCharacter).getMoveSpeed()); // display board is created with player speed and level
        a.createBoard();
        a.enterDefender();
        
        
        while(playerCharacter.getAlive() && (!(a.getCurrentXposition() == a.getMaxXPosition()) || !(a.getCurrentYposition()==a.getMaxXPosition())))
        {
            
            checkMenu(playerCharacter);
            System.out.println("Press enter to start moving");
            new Scanner(System.in).nextLine();
            a.moveCharacterOntheBoard();
            a.check();
            a.place(true);
            a.print();
            if(a.checkForFight())
            {
                Character defender = GenerateDefender();
                fightSequence(playerCharacter,defender,a);
            }
            
            System.out.println("Press enter to continue");
            new Scanner(System.in).nextLine();
            
            
        }
        
        if(playerCharacter.getAlive() == true)
        {
            System.out.println("You escaped!!");
            
        }
        else
        {
            System.out.println("Game is Over");
        }
        
    }
    
    
    
    public static Character GenerateDefender()
    {
        String[] names = {"Demon","Orc","Vampire","Drow Elf"};
        
        
        Character a = new  NonPlayableCharacter(names[new Random().nextInt(4)]);
        a.setAttack();                                                  // extra health and attack according to the level
        a.setHealth();                                                    
        // use of  polymorphism
        return a;
        
    }
    
    public static void addPlayerCharacter(Character[] a) // where fill player character is filled
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
            for(int x=0;x<a.length;x++)                 // substitution principle  below
            {
                
                if(new Random().nextBoolean())
                {
                    a[x] = new  Giant(names[new Random().nextInt(12)]);      // sub
                    giantCount++;
                }
                else if(new Random().nextBoolean())
                {
                    a[x] = new  Wizard(names[new Random().nextInt(12)]);
                    wizardCount++;
                }
                else
                {
                    a[x] = new Goblin(names[new Random().nextInt(12)]);
                    goblinCount++;
                }
            }
            
        } while(!(giantCount>=1) || !(wizardCount>=1) || !(goblinCount>=1)); // makes sure there is atleast one of all race
        
        
        
    }
    
    public static Character found(Character[] a, String name)
    {
        String[] nameANDrace = name.split(" ");            // allows to find the right character if they have same name
        Character found = null;
        
        for(int x=0;x<a.length;x++)
        {
            if(nameANDrace[0].equals(a[x].getName()) && nameANDrace[1].equals(((PlayableCharacter)a[x]).getRace()))
            {
                found = a[x];
            }
        }
        
        return found;
            
        
    }
    
    public static void showCharacter(Character[] a) // shows  the character name and their race
    {
        System.out.println("Pick your character");
        System.out.print("Names -" );
        for(int x=0;x<a.length;x++)                     // use of  polymorphism
        {
            
            if(x==4)
            {
                System.out.print("\n"+a[x].getName()+"("+((PlayableCharacter)a[x]).getRace()+") ");  //poly
            }
            
            else
            {
                System.out.print(a[x].getName()+"("+((PlayableCharacter)a[x]).getRace()+") ");
            }
        }
        System.out.println(); // clear line
    }
    public static void checkMenu(Character a) // shows health and also ability to use power up for Playable Character
    {
        String option = userInput("Do you wish to see health,attack...and possibly use power up(Y/N)");
        
        if(option.equals("Y"))
        {
            if(((PlayableCharacter)a).getPower()>=1) // only shows the power choice when character has ability to use
            {
                String choice = userInput("Would you like to power up?(Y/N)");
                
                if(choice.equals("Y"))
                {
                    System.out.println("You used power up successfully");
                    a.setAttack();
                    a.setHealth();
                    ((PlayableCharacter)a).decreasePowerUp();             // use of poly
                }
            }
             option = userInput("Press 1) to check all atrributes of your character 2) to check health and attack point 3) to exit ");
            
            if(option.equals("1"))
            {
                System.out.println(a.showAttributes());
            }
            else if(option.equals("2"))
            {
                System.out.println("Health-"+ a.getHealth());
                System.out.println("Attack-"+ a.getAttack());
            }
            
            
        }
        
    }
    
    
    public static String userInput(String question)  // ask question and give output as answer
    {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println(question);
        return userInput.nextLine();
        
    }
    
    public static void fightSequence(Character player,Character defender,DisplayBoard a) // fight method
    {
        System.out.println("Ohh no, you have to fight the defender");
        System.out.println("Defender level is "+((NonPlayableCharacter)defender).getLevel());  // 
        
        boolean escapeORrunback = false;
    
        do                             // keeps fighting until player defeats the defender also other way around, or player with wizard and goblin escapes or move to old position
        {                              // or player with wizard and goblin escapes or move to old position
            
        
            if(new Random().nextBoolean()) // who can attack is random
            {
                escapeORrunback = player.fight(defender);
                
                if(escapeORrunback)        // checks if those attributes being used
                {
                    if(player instanceof Wizard) 
                    {
                        a.setEscape(true);
                        break;
                    }
                    else
                    {
                        
                        a.runBackUsed();
                        break;
                        
                    }
                }
                
            }
            else
            {
                defender.fight(player);  // useful poly
            }
            System.out.println("Your health "+player.getHealth());
            System.out.println("Defender health "+defender.getHealth());
            System.out.println("Press enter to continue");
            new Scanner(System.in).nextLine();
            defender.checkHealth();
            player.checkHealth();
            
        }while(player.getAlive() && defender.getAlive());
        
        if(escapeORrunback) // tells if player won or not and also if they escaped or ran back
        {
            if(player instanceof Wizard) //
            {
                System.out.println(player.getName()+" escaped");
                System.out.println("Amount of Escape potion left "+((Wizard)player).getEscapePotion());
            }
            else
            {
                System.out.println(player.getName()+" ran back");
                System.out.println("Amount of times can run back "+((Goblin)player).getRunBack());
            }
        }
        else if(player.getAlive()) 
        {
            System.out.println("You won the fight");
            ((PlayableCharacter)player).increasePowerUp(); // poly
            System.out.println("Now you can see their power level, make sure to analize");
            System.out.println(defender.showAttributes());
            
            System.out.println("You are rewared with power up, your power up : "+ ((PlayableCharacter)player).getPower() ); // useful poly
            System.out.println("You are "+((PlayableCharacter)player).showPowerPercentage()+"% from getting a power up");
            
            
        }
        else
        {
            System.out.println("You lost the fight");
            player.setAlive();
            
        }
            
        
    }
    
    
    
    

}


