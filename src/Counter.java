public class Counter {

	
		
		public int addToNumber(int number) 
		{
			String str ="sdf";
			String[] avr = str.split(str);
			
	        return number + 1;
	        
	    }
	    
	    public int subtractFromNumber(int number) {
	        return number - 1;
	    }
	    public static void main(String[] args)
	    {
	    	System.out.println(countXX("abcxx"));
	    }
	    
	    
	    public static int countXX(String str) 
	    {
	      String[] a = str.split("");
	      
	      int count =0;
	      
	      for(int x=0;x<(a.length-1);x++)
	      {
	    	 
	        if(a[x].equals("x") && a[x+1].equals("x"))
	        {
	          count++;
	        }
	        
	      }
	      return count;
	      
	    }

	    
	    
	

}
