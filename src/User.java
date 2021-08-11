
public class User 
{
	private String name;
	private int numberOFbattleWon =0;
	private boolean won = false;
	
	public User(String name,int battle,boolean won)
	{
		this.name = name;
		this.numberOFbattleWon = battle;
		this.won = won;
	}
	public User()
	{
		
	}
	
	public void setBattleWon()
	{
		numberOFbattleWon++;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setWon()
	{
		this.won = true;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getBattle()
	{
		return this.numberOFbattleWon;
	}
	public boolean getWon()
	{
		return this.won;
	}

}
