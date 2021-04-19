package com.mkdevs.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mkdevs.IDable;
import com.mkdevs.io.UserIO;

public class IOUtil {
	
	/**
	 * Returns a map indexing an item in the collection to a number
	 * @param <T> collection type to be indexed
	 * @param collection
	 * @return map indexing the collection
	 */
	public static <T> Map<Integer, T> generateNumberMap(Collection<T> collection) {
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
	public static <T extends IDable> T getIDableFomMap(Map<Integer,T> map, UserIO userIO) {
		while(true) {
			printMapToConsole(map, userIO);
			Integer selection = userIO.getIntegerInput("enter selection number");

			if(map.containsKey(selection)) {
				return map.get(selection);
			}else {
				userIO.writeln(ErrorMessage.INVALID_SELECTION.message());
			}
			
		}

	}
	
	public enum ErrorMessage{
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
