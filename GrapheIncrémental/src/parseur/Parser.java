package parseur;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Parser {
	InputStream flux;
	InputStreamReader lecture;
	BufferedReader buff;
	String ligne;
	Integer oldSeparatorFound;
	Integer newSeparator;
	String separator;

	public Parser(String nameFile, String separator) throws FileNotFoundException {
		flux = new FileInputStream(nameFile);
		lecture = new InputStreamReader(flux);
		buff = new BufferedReader(lecture);
		this.separator = separator;
	}

	public String readLigne() throws IOException {
		ligne = buff.readLine();
		return ligne;
	}
	
	public Float parserFloat() throws NumberFormatException{
		Float value = Float.parseFloat(ligne.substring(oldSeparatorFound + 1, newSeparator));
		return value;
	}
	
	public String parserString(){
		String value = ligne.substring(oldSeparatorFound + 1, newSeparator);
		return value;
	}
	
	public Boolean parserNext() {
		newSeparator = ligne.indexOf(separator, oldSeparatorFound);
		if (newSeparator == -1)
			return false;
		return true;
	}
}
