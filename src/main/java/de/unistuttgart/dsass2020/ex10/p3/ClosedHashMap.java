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
 * @param <V>
 *                the type of the values to be added to the HashMap
 */
public class ClosedHashMap<V> extends AbstractHashMap<V> {
	private int jumpConstant;

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

	@Override
	public V put(Integer key, V value) throws IllegalStateException {
		int moduloValue = key % map.length;
		//can be infinite
		while(map[moduloValue] != null){
			if(map[moduloValue].getKey().equals(key)){
				return map[moduloValue].getValue();
			}
			moduloValue = (moduloValue + jumpConstant) % map.length;
		}
		map[moduloValue] = new KeyValuePair<V>(key,value);
		return null;
	}

	@Override
	public boolean containsKey(Integer key) {
		return false;
	}

	@Override
	public V get(Integer key) {
		return null;
	}

	@Override
	public V remove(Integer key) {
		return null;
	}

	@Override
	public Iterator<KeyValuePair<V>> iterator() {
		return null;
	}
}
