package com.mkdevs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IOUtil {
	
	/**
	 * Returns a map indexing an item in the collection to a number
	 * @param <T> collection type to be indexed
	 * @param collection
	 * @return map indexing the collection
	 */
	static <T> Map<Integer, T> generateNumberMap(Collection<T> collection) {
		Map<Integer, T> map = new HashMap<Integer, T>();
		Integer count = 0;
		for (T item: collection) {
			map.put(count, item);
			count ++;
		}
		
		return map;
	}

	/**
	 * prints the map of dice to Console
	 * @param map
	 */
	public static void printMapToConsole(Map<Integer,? extends IDable> map, UserIO writer) {
		for(Integer key: map.keySet()) {
			writer.writeln(key.toString() + ": " + map.get(key).id());
		}
	}

	/**
	 * returns the IDable specified by the user in the map 
	 * @param map
	 * @return
	 */
	static <T extends IDable> T getIDableFomMap(Map<Integer,T> map, UserIO userIO) {
		while(true) {
			printMapToConsole(map, userIO);
			userIO.writeln("Enter selection number: ");
			Integer selection = userIO.getIntegerInput();

			if(map.containsKey(selection)) {
				return map.get(selection);
			}else {
				userIO.writeln(ErrorMessage.INVALID_SELECTION.message());
			}
			
		}

	}
	
	protected enum ErrorMessage{
		INVALID_SELECTION("Selection invalid please try again");

		private String message;
		ErrorMessage(String message) {
			this.message = message;
		}
		
		public String message() {
			return this.message;
		}
	}
}
