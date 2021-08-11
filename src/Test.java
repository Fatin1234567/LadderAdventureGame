public class Test {
	
	
	public static void main(String[] args)
	{
		System.out.println(makeBricks(3, 1, 7)  );
	}
	
	public static boolean makeBricks(int small, int big, int goal) 
	  {
	     
	    int possibleAnswer =possibleAnswer(big,goal);
	    System.out.println(possibleAnswer);
	    if(possibleAnswer == goal) return true;
	    for(int x=1;x<=small;x++)
	    {
	       possibleAnswer = possibleAnswer + 1;
	       if(possibleAnswer == goal) return true;
	       
	    }
	    return false;
		  
		
	  }
	  
	  public static int possibleAnswer(int number,int goal)
	  {
		  int numberOFbricks =1;
		  int answer =0;
		  int answerCopy = 0;
		    
		    for(int x=1;x<=number;x++)
		    {
		    	
		    	answerCopy = answer;
		    	answer = 5* x;
		    	if(answer> goal) return answerCopy;	
		    }
		    return answer;
	  }
	
	
	
	
	
	

}
