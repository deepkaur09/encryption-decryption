import java.util.*;
import java.io.*;

/**
 * Base class for encryption and decryption.
 * Converts letters to numbers and back, and stores the encryption/decryption keys.
 */
public abstract class Algorithm{
    // Key used to encrypt the data
    public int [] [] encKey = {
        {3, 2},
        {5, 3}
    };
    
    // Key used to decrypt the data
    public int [] [] decKey = {
        {23, 2},
        {5, 23}
    };
    
    /**
      * Converts a character into a number.
      * @param c: The character to convert (a-z or padding '\0')
      * @return num: A number from 1 to 26 for letters, or 0 for padding
    */
    public int switchCharToNum(char c){
        if(c == '\0'){
            return 0;
        }
        int num =  ((int) c) - 96;
        return num;
        
    }
    
    /**
     * Converts a number back into a character.
     * @param num: The number to convert (0-26)
     * @return c : Corresponding lowercase letter, or '\0' for 0
    */
    public char switchNumToChar(int num){
        if(num == 0){
            return '\0';
        }
        char c = (char)(num + 96);
        return c;
    }
}


/**
 * Handles encryption of text.
 * Turns letters into numbers, use the encryption key for matrix multiplication, and converts back to letters.
 */
class Encryptor extends Algorithm{
    /**
     * Encrypts a list of characters and returns the encrypted list.
     * @param dataToEncrypt LinkedList of characters to be encrypted
     * @return LinkedList of characters after encryption
    */
    public LinkedList<Character> doEncrypt(LinkedList<Character> dataToEncrypt){
        LinkedList<Character> encryptedData = new LinkedList<>();
		LinkedList<Integer> dataNums = new LinkedList<>();
		for (int i = 0; i < dataToEncrypt.size(); i++){
			int num = switchCharToNum(dataToEncrypt.get(i));
			dataNums.add(num);
		}
		for (int i = 0; i < dataToEncrypt.size()/2; i++){
			encrypt2chars(dataNums, encryptedData);
		}
		return encryptedData;
    }
    
    public void encrypt2chars(LinkedList<Integer> data, LinkedList<Character> encryptedData){
	
		int x = data.get(0);
		data.removeFirst();
		int y = data.get(0);
		data.removeFirst();

		int newx = (encKey[0][0] * x + encKey[0][1] * y) % 26;
        int newy = (encKey[1][0] * x + encKey[1][1] * y) % 26;
		if (newx == 0) newx = 26;
        if (newy == 0) newy = 26;

		encryptedData.add(switchNumToChar(newx));
		encryptedData.add(switchNumToChar(newy));
	}
}

class Decryptor extends Algorithm{
    public LinkedList<Character> doDecrypt(LinkedList<Character> dataToDecrypt){
        LinkedList<Character> decryptedData = new LinkedList<>();
		LinkedList<Integer> dataNums = new LinkedList<>();
		for (int i = 0; i < dataToDecrypt.size(); i++){
			dataNums.add(switchCharToNum(dataToDecrypt.get(i)));
		}
		for (int i = 0; i < dataToDecrypt.size()/2; i++){
			decrypt2chars(dataNums, decryptedData);
		}
		return decryptedData;
    }
    
    public void decrypt2chars(LinkedList<Integer> data, LinkedList<Character> decryptedData){

		int x = data.get(0);
		data.removeFirst();
		int y = data.get(0);
		data.removeFirst();

		int newx = ((decKey[0][0] * x) + (decKey[0][1] * y)) %26;
		int newy = ((decKey[1][0] * x) + (decKey[1][1] * y)) %26;
		if (newx == 0) newx = 26;
        if (newy == 0) newy = 26;

		decryptedData.add(switchNumToChar(newx));
		decryptedData.add(switchNumToChar(newy));
	}
} 