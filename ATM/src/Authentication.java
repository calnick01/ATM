public class Authentication //CLASS TO CHECK AUTHENTICATON
{	static String pin ="1234"; //CORRECT PIN TO CHECK AGAINST
	public static boolean authentificationPin(String input) 
	{
		if (input.equals(pin)) //IF INPUT EQUALS PIN
			return true; //YES TRUE = CORRECT
		else
			return false; //NOT TRUE = INCORRECT
	}
}