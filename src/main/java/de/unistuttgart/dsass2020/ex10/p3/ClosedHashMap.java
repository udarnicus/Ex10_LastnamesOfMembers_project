package de.unistuttgart.dsass2020.ex10.p3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Realizes a basic hash map with closed hashing and linear probing. The hash
 * map is not expanded automatically on overflow. It extends
 * {@link AbstractHashMap} and uses its array of {@link KeyValuePair} as basic
 * data structure.
 *
 * @param <V> the type of the values to be added to the HashMap
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 28.06.2020
 */

public class ClosedHashMap<V> extends AbstractHashMap<V> {
	private int jumpConstant;
	private int elementsInMap = 0;

	/**
	 * Initializes a ClosedHashMap with the defined size and probing step size.
	 * 
	 * @param c
	 *                 the probing step size
	 * @param size
	 *                 the size of the map
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashMap(int size, int c) throws IllegalArgumentException {
		super.map = new KeyValuePair[size];
		jumpConstant = c;
	}

	/**
	 * Checks if the map is full
	 *
	 * @return
	 */
	private boolean maximumNumberOfElementsReached(){
		return map.length == elementsInMap;
	}



	/**
	 * Adds KeyValuePair to the map
	 *
	 *  CAUTION: if the size and jumping constant are poorly chosen, the while loop can run infinitely
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public V put(Integer key, V value) throws IllegalArgumentException {
		if(key == null || value == null){
			throw new IllegalArgumentException();
		}else if (maximumNumberOfElementsReached()){
			throw new IllegalStateException();
		}

		int moduloValue = key % map.length;
		while(map[moduloValue] != null){
			if(map[moduloValue].getKey().equals(key)){
				return map[moduloValue].getValue();
			}
			moduloValue = (moduloValue + jumpConstant) % map.length;
		}
		map[moduloValue] = new KeyValuePair<V>(key,value);
		elementsInMap++;
		return null;
	}

	/**
	 * Returns true if the given key exists in the map
	 *
	 *  CAUTION: if the size and jumping constant are poorly chosen, the while loop can run infinitely
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean containsKey(Integer key) {
		int moduloValue = key % map.length;
		//can be infinite
		while(map[moduloValue] != null){
			if(map[moduloValue].getKey().equals(key)){
				return true;
			}
			moduloValue = (moduloValue + jumpConstant) % map.length;
		}
		return false;
	}

	/**
	 * Returns the value of the given key
	 *
	 *  CAUTION: if the size and jumping constant are poorly chosen, the while loop can run infinitely
	 *
	 * @param key
	 * @return
	 */
	@Override
	public V get(Integer key) {
		if(!containsKey(key)){
			throw new IllegalStateException();
		}
		int moduloValue = key % map.length;
		//can be infinite
		while(map[moduloValue] != null){
			if(map[moduloValue].getKey().equals(key)){
				return map[moduloValue].getValue();
			}
			moduloValue = (moduloValue + jumpConstant) % map.length;
		}
		return null;
	}

	/** Methode to make testing easier
	 *
	 * @param index
	 * @return
	 */
	public KeyValuePair<V> getCellValue(int index){
		return map[index];
	}

	/**
	 * Removes KeyValuePair if given key exists
	 *
	 *  CAUTION: if the size and jumping constant are poorly chosen, the while loop can run infinitely
	 *
	 * @param key
	 * @return
	 */
	@Override
	public V remove(Integer key) {
		if(!containsKey(key)){
			throw new IllegalStateException();
		}
		int moduloValue = key % map.length;
		//can be infinite
		while(map[moduloValue] != null){
			if(map[moduloValue].getKey().equals(key)){
				V value = map[moduloValue].getValue();
				map[moduloValue] = null;
				elementsInMap--;
				return value;
			}
			moduloValue = (moduloValue + jumpConstant) % map.length;
		}
		return null;
	}

	/**
	 * Returns ClosedHashIterator for the ClosedHashMap
	 *
	 * @return
	 */
	@Override
	public Iterator<KeyValuePair<V>> iterator() {
		return new ClosedHashIterator();
	}

	/**
	 * Iterator for the ClosedHashMap
	 */
	class ClosedHashIterator implements Iterator{
		int currentPosition;

		private ClosedHashIterator(){
			currentPosition = 0;
		}

		@Override
		public boolean hasNext() {
			int searchIndex = currentPosition + 1;
			while(map[searchIndex] == null){
				if(searchIndex == map.length -1){
					return false;
				}
				searchIndex++;
			}
			return true;
		}

		@Override
		public KeyValuePair<V> next() {
			if(!hasNext()){
				throw new IllegalStateException();
			}
			int searchIndex = currentPosition + 1;
			while(map[searchIndex] == null){
				searchIndex++;
			}
			currentPosition = searchIndex;
			return map[searchIndex];
		}
	}


}
