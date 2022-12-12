import java.util.*;

public class palindromecheck {
	
	//Bonus Part
	public static String palindromeHelper(String input) {
		input = input.replaceAll("[^a-zA-Z0-9]", "");
		
		return input.toUpperCase();
	}
	public static boolean checkPalindrome(String input) {
        if((input.length() == 0) || (input.length() == 1)) //If it's a 0 length string or only 1 letter...
            return true; 
        if(input.charAt(0) == input.charAt(input.length()-1)) //If  0 == n-1 letter, run again but move it in one
            return checkPalindrome(input.substring(1, input.length()-1));

        return false; //If the 0 || 1 doesn't break, return false
    }

    public static void main(String[]args) {
    	System.out.println("What word/sentence/string would you like to check is a palindrome?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if(checkPalindrome(palindromeHelper(input)))
            System.out.println(input + " is a palindrome");
        else
            System.out.println(input + " is not a palindrome");
    }

}
