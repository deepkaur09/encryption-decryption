import java.util.*;
import java.io.*;

/**
 * Abstract class representing data operations.
 * Provides base for reading data and storing output writers.
*/
public abstract class Data {
    protected PrintWriter encWriter = null;
    protected PrintWriter decWriter = null;
    
    // Constructor to initialize PrintWriters
    public Data(PrintWriter encWriter, PrintWriter decWriter) {
        this.encWriter = encWriter;
        this.decWriter = decWriter;
    }
	public Data() {}

    
    /**
     * Abstract method to read data from a source.
     * @return Object representing the read data
    */
	public abstract Object read() throws Exception;
}

/**
 *Class to read data from a file,and returns the content as a single String.
*/
class FileData extends Data {
	private Scanner fileScanner = null;
	private String fileName;

	public FileData(String fileName){
	    this.fileName = fileName;
	}

	@Override
	public String read() throws FileNotFoundException{
		File inputFile = new File(fileName);
		fileScanner = new Scanner(inputFile);
		StringBuilder tempData = new StringBuilder();
		while (fileScanner.hasNextLine()) {
		    tempData.append(fileScanner.nextLine());
		}
		fileScanner.close();
		return tempData.toString();
	}
}

/**
 * Class to process a text string as a LinkedList of characters.
 * Filters only lowercase letters and handles writing encrypted/decrypted data.
*/
class MyLinkedList extends Data {
    
    public String text;
    
    // Constructor to initialize text and writers
    public MyLinkedList(String msg, PrintWriter encWriter, PrintWriter decWriter){
        super(encWriter, decWriter);
        this.text = msg.toLowerCase();
    }

     /**
     * Reads the text and converts it to a LinkedList of lowercase letters only.
     * @return data: A LinkedList of Characters.
     */
	@Override
	public LinkedList<Character> read() {
		LinkedList<Character> data = new LinkedList<>();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 'a' && c <= 'z') {
			    // only lowercase letters
                data.add(c);
			}
		}
		return data;
	}
	
	/**
     * Writes the encrypted data to the "EncryptionData.txt" file.
    */
    public void writeEnc(LinkedList<Character> list) {
        for(int i = 0; i < list.size(); i++){
            encWriter.print(list.get(i));
        }
    }
    
    /**
     * Writes the decrypted data to the "result_message.txt" file.
    */
    public void writeDec(LinkedList<Character> list) {
        for(int i = 0; i < list.size(); i++){
            decWriter.print(list.get(i));
        }
    }
}


