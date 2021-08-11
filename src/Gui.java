import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Gui
{
	JFrame window;          // contains 
	Container con;
	JPanel titleNamePanel, startButtonPanel, selectorPanel,errorShow,continuePanel,gameScreenPanel,gameScreenButtonsPanel,gameShowMovePanel,fightScreenButtonsPanel,fightScreenShowPanel,attributeButtonPanel,enterNamePanel,leaderBoardPanel,leaderBoardScreenPanel,backPanel;
	JLabel titleNameLabel,playerHp,defenderHp;
	Font titleFont = new Font("Normal",Font.PLAIN,70);
	JButton startButton,continueButton,moveButton,fightButton,showAttributesButton,fightContinueButton,powerButton,backButton,powerUp,leaderBoardButton;
	Font buttonFont = new Font("Normal",Font.PLAIN,40);
	Font gameScreenSize = new Font("Normal",Font.PLAIN,20);
	Font leaderScreen = new Font("Normal",Font.PLAIN,24);
	Font popUPText = new Font("Normal",Font.PLAIN,20);
	JComboBox characterSelection;
	JTextArea gameScreen,displayMoveScreen,leaderBoardScreen;
	JTextField name;
	
	
	titleScreenHandler tsHandler = new titleScreenHandler();
	titleScreenHandlerLeaderBoard tsLeaderBoardHandler = new titleScreenHandlerLeaderBoard();// here are the actio handler
	leaderBoardBackHandler back = new leaderBoardBackHandler();
	characterScreenHandlerContinue csContinueHandler = new characterScreenHandlerContinue();
	
	
	gameHandlerAttributes gameAttributesHandler = new gameHandlerAttributes();
	gameHandlerMove gameMoveHandler = new gameHandlerMove();
	gameHandlerFight fightHandler = new gameHandlerFight();
	
	fightHandlerFight fightHandlerButton = new fightHandlerFight();
	fightHandlerPower powerHandlerButton = new fightHandlerPower();
	fightHandlerBack goBackGameScreen = new fightHandlerBack();
	attributeHandlerBack goBack = new attributeHandlerBack();
	attributeHandlerPower powerAction = new attributeHandlerPower();
	
	Game2 game;
	DisplayBoard a;
	private boolean exitFight;
	private Character attacker;
	private boolean finishedFightSession = false;
	private boolean usedPower = false;
	private User playerRecordedInfo = new User();
	
	
	public static void main(String[] args)
	{
		new Gui();
		
	}
	
	public Gui()  // creates the whole gui
	{
		
		game = new Game2();
		game.addPlayerCharacter();
		
		
		
		window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setResizable(false);
		window.setLayout(null);
		con = window.getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100,600, 100); // sets the size
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("Adventure Escape");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 80);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("Start");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(buttonFont);
		startButton.addActionListener(tsHandler);
		
		leaderBoardPanel = new JPanel();
		leaderBoardPanel.setBounds(250,480, 300, 100); // sets the size
		leaderBoardPanel.setBackground(Color.black);
		
		leaderBoardButton = new JButton("LeaderBoard");
		leaderBoardButton.setBackground(Color.black);
		leaderBoardButton.setForeground(Color.white);
		leaderBoardButton.setFont(buttonFont);
		leaderBoardButton.addActionListener(tsLeaderBoardHandler);
		
		leaderBoardPanel.add(leaderBoardButton);
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(leaderBoardPanel);
		
		
		
		
		window.setVisible(true);
		
	}
	public void createLeaderBoardScreen() // creates the leaderboard screen when its clicked 
	{
		startButtonPanel.setVisible(false);
		leaderBoardPanel.setVisible(false);
		titleNameLabel.setText("LeaderBoard");
		
		leaderBoardScreenPanel = new JPanel();
		leaderBoardScreenPanel.setBounds(100, 250, 600, 300);
		leaderBoardScreenPanel.setBackground(Color.blue);
		con.add(leaderBoardScreenPanel);
		
		leaderBoardScreen = new JTextArea(8,28);
		
		ArrayList<User> playerInfo = readData();
		String total = "";
		int num =1;
		for(User a: playerInfo)
		{
			total = total+num+") Name:"+a.getName()+" Battle:"+a.getBattle()+" Won:"+a.getWon()+"\n";
			num++;
		}
		leaderBoardScreen.setText(total);
		
	
		leaderBoardScreen.setSize(1000,100);
		leaderBoardScreen.setFont(leaderScreen);
		
		JScrollPane sp = new JScrollPane(leaderBoardScreen,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		leaderBoardScreenPanel.add(sp);
		
		backPanel = new JPanel();
		backPanel.setBounds(0, 0, 200, 100);
		backPanel.setBackground(Color.black);
		con.add(backPanel);
		
		backButton = new JButton("Back");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(buttonFont);
		backPanel.add(backButton);
		backButton.addActionListener(back);
		
	}
	
	public void createCharacterScreen()  // creates cahracter selection page
	{
		leaderBoardPanel.setVisible(false);
		titleNamePanel.setVisible(true);
		startButtonPanel.setVisible(false);
		
		titleNameLabel.setText("Select Character");
		
		enterNamePanel = new JPanel();
		enterNamePanel.setBounds(250, 300, 300,50);
		enterNamePanel.setBackground(Color.black);
		con.add(enterNamePanel);
		
		name = new JTextField("Enter name",10);
		name.setFont(gameScreenSize); // so the text field appears bigger
		enterNamePanel.add(name);
		
		selectorPanel = new JPanel();
		selectorPanel.setBounds(200, 350, 400,50);
		selectorPanel.setBackground(Color.black);
		con.add(selectorPanel);
		
		characterSelection = new JComboBox();
		for(int x=0;x<game.getCharacters().length;x++)                // adding character to the list
		{
			characterSelection.addItem(game.showNameAndRace(game.getCharacters()[x]));
		}
		characterSelection.setSelectedItem(null);
		
		
		selectorPanel.add(characterSelection);
		
		continuePanel = new JPanel();
		continuePanel.setBounds(300,450, 200, 100);
		continuePanel.setBackground(Color.black);
		con.add(continuePanel);
		
		continueButton = new JButton("Continue");
		continueButton.setBackground(Color.black);
		continueButton.setForeground(Color.white);
		continueButton.setFont(buttonFont);
		continueButton.addActionListener(csContinueHandler);
		continuePanel.add(continueButton);
		
	}
	
	public void createGameScreen()  // creates battle page
	{
		//titleNamePanel.setVisible(false);
		titleNameLabel.setText("Escape Defender");
		titleNamePanel.setBounds(100, 0,600, 100);
		selectorPanel.setVisible(false);
		continuePanel.setVisible(false);
		enterNamePanel.setVisible(false);
		
		playerRecordedInfo.setName(name.getText());
		
		gameScreenPanel = new JPanel();
		gameScreenPanel.setBounds(100, 100, 625, 300);
		gameScreenPanel.setBackground(Color.blue);
		con.add(gameScreenPanel);
		
		gameScreen = new JTextArea();
		gameScreen.setBounds(200, 150, 1000,1000);
		gameScreen.setBackground(Color.blue);
		gameScreen.setFont(gameScreenSize);
		gameScreen.setForeground(Color.white);
		gameScreen.setText(a.print());
		gameScreenPanel.add(gameScreen);
		
		
		gameScreenButtonsPanel = new JPanel();
		gameScreenButtonsPanel.setBounds(100, 450, 600, 100);
		gameScreenButtonsPanel.setBackground(Color.black);
		con.add(gameScreenButtonsPanel);
		
		

		showAttributesButton = new JButton("Attributes");
		showAttributesButton.setBackground(Color.black);
		showAttributesButton.setForeground(Color.white);
		showAttributesButton.setFont(buttonFont);
		showAttributesButton.addActionListener(gameAttributesHandler);
		gameScreenButtonsPanel.add(showAttributesButton);
		
		moveButton = new JButton("Move");
		moveButton.setBackground(Color.black);
		moveButton.setForeground(Color.white);
		moveButton.setFont(buttonFont);
		moveButton.addActionListener(gameMoveHandler);
		
		gameScreenButtonsPanel.add(moveButton);
		
		fightButton = new JButton("Fight");
		fightButton.setBackground(Color.black);
		fightButton.setForeground(Color.white);
		fightButton.setFont(buttonFont);
		fightButton.addActionListener(fightHandler);
		gameScreenButtonsPanel.add(fightButton);
		
		gameShowMovePanel = new JPanel();
		gameShowMovePanel.setBounds(320, 400, 200, 50);
		gameShowMovePanel.setBackground(Color.black);
		con.add(gameShowMovePanel);
		
		displayMoveScreen = new JTextArea();
		displayMoveScreen.setBounds(320, 400, 200, 50);
		displayMoveScreen.setBackground(Color.black);
		displayMoveScreen.setFont(popUPText);
		displayMoveScreen.setForeground(Color.white);
		displayMoveScreen.setLineWrap(true);
		gameShowMovePanel.add(displayMoveScreen);
		
		
	}
	
	public void createShowAttributesScreen()  // creates attribute page where it shows detail of the character
	{
		titleNameLabel.setText("Attributes");
		gameScreenButtonsPanel.setVisible(false);
		gameScreen.setLineWrap(true);
		String msg= game.getPlayer().showAttributes()+"\n";
		msg = msg +"Power potion:"+((PlayableCharacter)game.getPlayer()).getPower();
		gameScreen.setText(msg);
		
		attributeButtonPanel = new JPanel();
		attributeButtonPanel.setBounds(100, 450, 600, 100);
		attributeButtonPanel.setBackground(Color.black);
		con.add(attributeButtonPanel);
		
		backButton = new JButton("Back");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(buttonFont);
		backButton.addActionListener(goBack);
		attributeButtonPanel.add(backButton);
		
		powerUp = new JButton("Power Up");
		powerUp.setBackground(Color.black);
		powerUp.setForeground(Color.white);
		powerUp.setFont(buttonFont);
		powerUp.addActionListener(powerAction);
		attributeButtonPanel.add(powerUp);
		
		
		
	}
	
	public void createFightScreen() // where the battle screen is
	{
		if(a.checkForFight())
		{
			
			
			game.assignDefender();
			gameScreen.setBounds(0,0,400, 250);
			gameScreen.setLineWrap(true);
			gameScreenButtonsPanel.setVisible(false);
			
			titleNamePanel.setVisible(true);
			titleNamePanel.remove(titleNameLabel);
			titleNamePanel.setBounds(100, 15, 650, 50);
			titleNamePanel.setLayout(new GridLayout(1,2));
			titleNamePanel.remove(titleNameLabel);
			
			playerHp = new JLabel("Player Hp:"+game.getPlayer().getHealth());
			playerHp.setFont(buttonFont);
			playerHp.setForeground(Color.white);
			titleNamePanel.add(playerHp);
			defenderHp = new JLabel("Defender Hp:"+game.getDefender().getHealth());
			defenderHp.setFont(buttonFont);
			defenderHp.setForeground(Color.white);
			titleNamePanel.add(defenderHp);
			
		
			String msg = "Few Rules\nMake sure you to use power up\nuse before you go into the sub fight\n\n";
			msg = msg+"Defender is called"+ game.getDefender().getName()+" power  level "+((NonPlayableCharacter)game.getDefender()).getLevel();
			displayMoveScreen.setText("Defeat the defender");
			gameScreen.setText(msg);
			
			fightScreenButtonsPanel = new JPanel();
			fightScreenButtonsPanel.setBounds(100, 450, 650, 100);
			fightScreenButtonsPanel.setBackground(Color.black);
			con.add(fightScreenButtonsPanel);
			
			fightContinueButton = new JButton("Press Fight");
			fightContinueButton.setBackground(Color.black);
			fightContinueButton.setForeground(Color.white);
			fightContinueButton.setFont(buttonFont);
			fightContinueButton.addActionListener(fightHandlerButton);
			fightScreenButtonsPanel.add(fightContinueButton);
			
			powerButton = new JButton("Use Power");
			powerButton.setBackground(Color.black);
			powerButton.setForeground(Color.white);
			powerButton.setFont(buttonFont);
			powerButton.addActionListener(powerHandlerButton);
			fightScreenButtonsPanel.add(powerButton);
			
			
			
			backButton = new JButton("Back");
			backButton.setBackground(Color.black);
			backButton.setForeground(Color.white);
			backButton.setFont(buttonFont);
			backButton.addActionListener(goBackGameScreen);
			fightScreenButtonsPanel.add(backButton);
			
		}
		else
		{
			displayMoveScreen.setText("Can't fight");
		}
	}
	
	public void fightScene() // finish the fight screen needs button where clicked should
	{	
		String total = "";
		
		this.exitFight = game.fightSequence();
		if(!this.exitFight)
		{
			attacker =  game.getWhoAttacks();
			total = attacker.getName() +" attacks \n";
			total = total +"Land hit: "+attacker.getLandHit()+"\n";
			
			if(attacker.getLandHit())
			{
				total = total+"So damage of "+attacker.getDamage()+" is done\n";
				
			}
			else
			{
				total = total+"No damage done\n";
			}
			playerHp.setText("Player Hp:"+game.getPlayer().getHealth());
			defenderHp.setText("Defender Hp:"+game.getDefender().getHealth());
			
			
			if(!game.getPlayer().getAlive())
				{
					total = total +"Game Over";
					addData();
				}
			else if(!game.getDefender().getAlive())
			{
				((PlayableCharacter)game.getPlayer()).increasePowerUp();
				total = total+"You defeated the monster \nPowerUp-"+((PlayableCharacter)game.getPlayer()).getPower();
				total = total+"\nPercentage needed to use:"+((PlayableCharacter)game.getPlayer()).showPowerPercentage()+"%";
				playerRecordedInfo.setBattleWon();
			}
			
		}
		else
		{
			total = ((PlayableCharacter)game.getPlayer()).showDetailWhenPowerUsed()+"\nPress go back";
			if(!(game.getPlayer() instanceof Giant))((changeBoard)game.getPlayer()).changeBoardPosition(a); // interface type used since only wizard and giant can change the board
			usedPower = true;
		}
		
	
		
		gameScreen.setText(total);
		
		
	}
	
	public void addData() // adds the info into the file
	{
		try
		{
			PrintWriter outputStream = new PrintWriter(new FileWriter("leaderBoard.txt",true));
		
			outputStream.format("%s,%s,%s", playerRecordedInfo.getName(),Integer.toString(playerRecordedInfo.getBattle()),Boolean.toString(playerRecordedInfo.getWon()));
			outputStream.println(); // so goes new line after
			
			outputStream.close();
			System.out.println("Done");
		}catch(Exception e) 
		{
			System.out.println("Error");
		}
	}
	public ArrayList<User> readData() // puts the info into type user and returns it
	{
		ArrayList<User> playerInfo = new ArrayList<>();
		try
		{
			 String filename = "leaderBoard.txt";
			 BufferedReader inputStream = new BufferedReader (new FileReader(filename));
			 String a = inputStream.readLine();
			 while(a != null)
			 {
				 String[] b = a.split(",");
				 User player = new User(b[0],Integer.parseInt(b[1]),Boolean.parseBoolean(b[2]));
				 playerInfo.add(player);
				 a = inputStream.readLine();
			 }
			 inputStream.close();
		}catch(Exception e) 
		{
			System.out.println("Error");
		}
		return playerInfo;
	}
	// all the handler for the buttons
	class leaderBoardBackHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			leaderBoardScreenPanel.setVisible(false);
			backPanel.setVisible(false);
			startButtonPanel.setVisible(true);
			leaderBoardPanel.setVisible(true);
			titleNameLabel.setText("Adventure Escape");
		}
	}
	class titleScreenHandler implements ActionListener // when continue is clicked
	{
		public void actionPerformed(ActionEvent event)
		{
			createCharacterScreen();
		}
	}
	class titleScreenHandlerLeaderBoard implements ActionListener // when leaderBoard is clicked
	{
		public void actionPerformed(ActionEvent event)
		{
			createLeaderBoardScreen();	
		}
	}
	
	
	
	
	class characterScreenHandlerContinue implements ActionListener // when continue is pressed to go  game screen
	{
		
		public void actionPerformed(ActionEvent event)
		{
			
			
			try
			{
				game.found((String)characterSelection.getSelectedItem());
				
			}catch(NullPointerException e)
			{
				game.found((String) characterSelection.getItemAt(1));
				System.out.println("You will just get default Character"); 
			}
			a = new DisplayBoard(2,((PlayableCharacter)game.getPlayer()).getMoveSpeed()); // since if no character is selected
			
			a.createBoard();
			a.enterDefender();
			a.place(game.getPlayer().getAlive());
			createGameScreen();
		}
	}
	
	class gameHandlerAttributes implements ActionListener // attribute is clicked
	{
		public  void actionPerformed(ActionEvent event)
		{
			createShowAttributesScreen();
		}
	}
	
	class gameHandlerMove implements ActionListener // when move is clicked
	{
		public void actionPerformed(ActionEvent event)
		{
			if( a.checkForFight() && !finishedFightSession ) displayMoveScreen.setText("Have to fight");
			else if(a.checkIFgameFinish())
			{
				displayMoveScreen.setText("You have won");
				playerRecordedInfo.setWon();
				addData(); 	
			}
			else 
			{
				 a.moveCharacterOntheBoard();
		         a.check();
		         a.place(game.getPlayer().getAlive());
		         gameScreen.setText(a.print());
		         displayMoveScreen.setText(a.getMove());
		         finishedFightSession = false;
			}

		}
	}
	
	class gameHandlerFight implements ActionListener // when fight is clicked in game screen
	{
		public void actionPerformed(ActionEvent event)
		{
			createFightScreen();
		}
	}
	
	class fightHandlerFight implements ActionListener // when fight is clicked
	{
		public void actionPerformed(ActionEvent event)
		{
			
			if(usedPower)
			{
				displayMoveScreen.setText("Go back");
				usedPower = false;                     // resets
			}
			else if(game.getPlayer().getAlive() && game.getDefender().getAlive()) fightScene();
			else displayMoveScreen.setText("Go back");
			
		}
	}
	class fightHandlerPower implements ActionListener   // when powerUsed is clicked
	{
		public void actionPerformed(ActionEvent event)
		{
			if(((PlayableCharacter)game.getPlayer()).showPower()>0)
			{
				displayMoveScreen.setText("Used Power");
				((PlayableCharacter)game.getPlayer()).setUsedPower("yes");
				
			}
			else displayMoveScreen.setText("No Power");
		}
	}
	
	class fightHandlerBack implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			if(!(game.getDefender().getAlive()) || exitFight)
			{
				fightScreenButtonsPanel.setVisible(false);
				titleNamePanel.remove(playerHp);
				titleNamePanel.remove(defenderHp);
				titleNamePanel.setLayout(null);
				titleNamePanel.add(titleNameLabel);
				titleNamePanel.setBounds(100,0,600, 100);
				gameScreen.setLineWrap(false);
				gameScreen.setText(a.print());
				gameScreenButtonsPanel.setVisible(true);
				finishedFightSession = true;
			}
			
			else displayMoveScreen.setText("You havent defeated the defender");
		}
	}
	
	class attributeHandlerBack implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			titleNameLabel.setText("Escape Defender");
			attributeButtonPanel.setVisible(false);
			gameScreen.setLineWrap(false);
			gameScreen.setText(a.print());
			gameScreenButtonsPanel.setVisible(true);
		}
	}
	
	class attributeHandlerPower implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(((PlayableCharacter)game.getPlayer()).getPower()>=1)
			{
				((PlayableCharacter)game.getPlayer()).setAttack();
				((PlayableCharacter)game.getPlayer()).setHealth();
				((PlayableCharacter)game.getPlayer()).decreasePowerUp();
				String msg = "You used your power up\n";
				msg = msg+"New Health:"+game.getPlayer().getHealth();
				msg = msg +"New Attack:"+game.getPlayer().getAttack();
				gameScreen.setText(msg);
			}else gameScreen.setText("You dont have enough power up potion");
		}
	}
	
	
	
	

}
