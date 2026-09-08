/**
 * This program reads text from a file ("msg.txt") and encrypts it using
 * a custom LinkedList-based Encryptor, writing the result to "EncryptedData.txt".
 * It then reads that encrypted file back in and decrypts it using a corresponding
 * Decryptor, writing the final decrypted text to "result_message.txt".
 * Handles cases where the input file is missing, empty, or contains no valid data,
 * and pads odd-length input with a null character (removed again after decryption).
 * @authors: Francis Gonzalez, Dapinderdeep Kaur
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) {
		try {
		    // Create PrintWriter objects to write encrypted and decrypted outputs
		    PrintWriter encWriter = new PrintWriter("EncryptedData.txt");
            PrintWriter decWriter = new PrintWriter("result_message.txt");
		    
		    //Case for Encryption
			FileData txt = new FileData("msg.txt");
			String text;
			
			 
            //Try-catch block used to read the input file. Exception handling, if file is not found.
			try{
			    text = txt.read();
			}
			catch (FileNotFoundException e) {
                System.out.println("Input file not found.");
                encWriter.println("Input file not found.");
                decWriter.println("Input file not found.");
                encWriter.close();
                decWriter.close();
                return;
            }

			//Error handling if input file is empty.
			if(text.length() == 0) {
				System.out.println("File is empty.");
                encWriter.println("File is empty.");
                decWriter.println("File is empty.");
                encWriter.close();
                decWriter.close();
				return;
			}
			
	    	MyLinkedList myList = new MyLinkedList(text, encWriter, decWriter);
			LinkedList<Character> textList = myList.read();
			
			//Error handling if invalid input is provided.
			if(textList.size() == 0) {
				System.out.println("No valid data provided.");
				encWriter.println("No valid data provided.");
                decWriter.println("No valid data provided.");
                encWriter.close();
                decWriter.close();
				return;
			}

            //If the number of characters is odd, add a padding character to make it even/
			boolean addDup = false;
			if (textList.size() % 2 == 1) {
				textList.add('\0');
				addDup = true;
			}

			Encryptor encrypt = new Encryptor();
			LinkedList<Character> encryptedList = encrypt.doEncrypt(textList);
			
			// Write the encrypted data to the output file
			System.out.println("See output files for results.");
            myList.writeEnc(encryptedList);
            encWriter.close();
			
			//Case for decryption
			FileData encTxt = new FileData("EncryptedData.txt");
			String encText = encTxt.read();
			
			MyLinkedList myEncList = new MyLinkedList(encText, encWriter, decWriter);
			LinkedList<Character> encryptedListFromFile = myEncList.read();

			Decryptor decrypt = new Decryptor();
			LinkedList<Character> decryptedList = decrypt.doDecrypt(encryptedListFromFile);
			
            // Remove padding character if it was added previously
			if (addDup) {
				decryptedList.removeLast();
			}
			
			 // Write the decrypted data to the output file
			myList.writeDec(decryptedList);
            decWriter.close();
		}
		// Catch-all for unexpected exceptions
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}


	}
}
