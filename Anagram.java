/** Functions for checking if a given string is an anagram. */
public class Anagram {
	static String abc = "abcdefghijklmnopqrstuvwxyz1234567890";
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);

		for (int i = 0; i < str1.length(); i++) {
			int currentIndex = str2.indexOf(str1.charAt(i));

			if (currentIndex != -1) {
				//System.out.println(Character.toString(str1.charAt(i)));
				str2 = str2.replaceFirst(Character.toString(str1.charAt(i)), " ");
				//System.out.println(str2);
				str2 = preProcess(str2);
				//System.out.println(str2);
			}
			else {
				return false;
			}
		}
		if (str2.isEmpty() == true) {
			return true;
		}
		return false;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
		String processStr = "";
		for (int i = 0; i < str.length(); i++){

			if (abc.indexOf(str.charAt(i)) != -1) {
				
				processStr = processStr + str.charAt(i);
			}
			
		}
		return processStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		
		char[] strArray = str.toCharArray();
		char[] newArray = new char[str.length()];
		int randomIndex = (int) (Math.random() * str.length());

		for (int i = 0; i < str.length(); i++) {
			while (newArray[randomIndex] != 0) {
				randomIndex = (int) (Math.random() * str.length());
			}
			newArray[randomIndex] = strArray[i];	
		}

		String newString = String.valueOf(newArray);
		return newString;
	}
}
