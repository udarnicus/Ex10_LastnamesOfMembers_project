package de.unistuttgart.dsass2020.ex10.p3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.unistuttgart.dsass2020.ex10.p3.ClosedHashMap;
import de.unistuttgart.dsass2020.ex10.p3.AbstractHashMap.KeyValuePair;

public class ClosedHashMapTest {
    @Test
    public void testClosedHashMap(){
        ClosedHashMap<String> closedHashMap = new ClosedHashMap<>(13,5);
        closedHashMap.put(5 , "5");
        closedHashMap.put(43 , "43");
        closedHashMap.put(18 , "18");
        closedHashMap.put(10 , "10");
        closedHashMap.put(108 , "108");
        closedHashMap.put(69 , "69");
        closedHashMap.put(58 , "58");
        closedHashMap.put(95 , "95");
        closedHashMap.put(101 , "101");

        Iterator<KeyValuePair<String>> iterator = closedHashMap.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getKey());
        }
        System.out.println("######################");


        for(int i= 0; i<13; i++){
            System.out.println(closedHashMap.getCellValue(i));
        }




    }


}
