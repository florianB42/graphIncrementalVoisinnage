package parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Parser {
	/**
	 * A stream to the file
	 */
	private InputStream stream;
	private InputStreamReader reader;
	private BufferedReader buffer;
	
	/**
	 * The last line read
	 */
	private String line;
	
	/**
	 * The last separator
	 */
	private Integer oldSeparator;
	/**
	 * the current separator
	 */
	private Integer newSeparator;
	
	/**
	 * The separator that is used in the file to separate data
	 */
	private String separator;

	/**
	 * This builder opens the file. 
	 * @param nameFile 
	 * @param separator : the separator that is used in the file to separate data 
	 * @throws FileNotFoundException
	 */
	public Parser(String nameFile, String separator) throws FileNotFoundException {
		stream = new FileInputStream(nameFile);
		reader = new InputStreamReader(stream);
		buffer = new BufferedReader(reader);
		this.separator = separator;
	}
	
	/**
	 * This method reads a line in the file and calls the method parserNext
	 * @return the line which is read
	 * @throws IOException
	 */
	public String readLine() throws IOException {
		line = buffer.readLine();
		if (line != null) {
			newSeparator = -1;
			parserNext();
		}
		return line;
	}

	/**
	 * It parses a string to a float
	 * @return the float value parsed
	 * @throws NumberFormatException
	 */
	public Float parserFloat() throws NumberFormatException {
		Float value = Float.parseFloat(line.substring(oldSeparator, newSeparator));
		return value;
	}
	
	/**
	 * It reads the string between two separators
	 * @return the string value
	 */
	public String parserString() {
		String value = line.substring(oldSeparator, newSeparator);
		return value;
	}

	/**
	 * It moves in the line.
	 * @return false if it is the end of the line and true if not.
	 */
	public Boolean parserNext(){
		oldSeparator = newSeparator + 1;
		if (oldSeparator == line.length() + 1) {
			return false;
		}
		newSeparator = line.indexOf(separator, oldSeparator);
		if (newSeparator == -1) {
			newSeparator = line.length();
		}
		
		return true;
	}
}
