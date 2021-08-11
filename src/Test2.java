public class Test2

{
	
	public static void main(String[] args)
	{
		Character[] a = { new Wizard("Bob"), new Giant("Bob") };
		
		
		
		//for(int x=0;x<a.length;x++)
		//{
		//	System.out.println(a[x].getName()+" "+ ((PlayableCharacter)a[x]).getRace());
		//}
		//System.out.println("Chose your character by name and their race eg Eren Giant");
		//String name = new Scanner(System.in).nextLine();
		
		//Character yours = found(a,name);
		//System.out.println(((PlayableCharacter)yours).getRace());
		
		
	}
	
	
	public static Character found(Character[] a, String name)
	{
		String[] nameANDrace = name.split(" ");
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
	public static void showCharacter(Character[] a)
	{
		for(int x=0;x<a.length;x++)
		{
			System.out.print("Name- "+a[x].getName()+"("+((PlayableCharacter)a[x]).getRace()+") ");
		}
	}
	

}
