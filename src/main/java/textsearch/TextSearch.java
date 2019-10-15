package textsearch;

import java.util.List;

public interface TextSearch {

	/*
	 * @param searchString is not null
	 * 
	 * @return the number of times the given string occurs in the container
	 */
	int getNumOccurrences(String searchString);

	/*
	 * @param searchString is not null
	 * 
	 * @return an ordered List of the strings in the container that include
	 * searchString as a substring. If the substring does not exist in text then
	 * an empty list is returned.
	 */
	List<String> getOccurrences(String searchString);
}
