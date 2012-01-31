/*                                                                            *\
   (c)2009 Simon Anthony Dawson
   This program is free software; you can redistribute it and/or modify it
   under the terms of the GNU General Public License as published by the Free
   Software Foundation; either version 2, or (at your option) any later
   version.

   This program is distributed in the hope that it will be useful, but
   WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
   or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
   for more details.

   This notice must stay intact for legal use
\*                                                                            */
package me.kovra.Taxman;

import java.util.Iterator;
import java.util.Vector;

/**
 * A wad is the collective noun for cash. This will define methods specific to a collection of coins.
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Wad {
    //Attributes
    private Vector <Coin> vCoins = new Vector <Coin>();


    //Constructors
    /**
     * Constructor for an empty Wad
     */
    public Wad() {
    }//end Wad()

    /**
     * Constructor for a Wad filled with coins from value 1 to <code>n</code> inclusive
     * @param n
     */
    public Wad (int n) throws TaxmanError {
        if (n<0) {
                TaxmanError e = new TaxmanError("Game can't have a negative number of coins.");
                throw e;
        } else {
                Coin c;
                for (int i = 0; i < n; i++) {
                        c = new Coin(i+1);
                        vCoins.add(c);
                }//end for i
        }//end else
    }//end Constructor

    //Methods
    /**
     * @return the vector of Coins in the wad
     */
    public Vector<Coin> getvCoins() {
        return vCoins;
    }

    /**
     * Removes all the coin objects from the Wad that have the same value as <code>Coin _c</code>
     * @param _c
     */
    public void removeCoins (Coin _c) {
        for (int i=0;i<vCoins.size();i++) {
                if (vCoins.elementAt(i).equals(_c)) {
                        vCoins.remove(i);
                }//end if
        }//end for
    }//end removeCoins

    /**
     * Adds a Coin to the Wad
     * @param c
     */
    public void add (Coin c){
        this.vCoins.add(c);
    }//end add

    /**
     * @return the sum of call the <code>Coin.getValue()</code> calls in the Wad
     */
    public int getValue() {
        int returnValue=0;
        if (vCoins.isEmpty()) {
                returnValue=0;
        }//end if
        else
        {
                for (int i=0; i<getSize();i++) {
                        returnValue+=vCoins.elementAt(i).getValue();
                }// end for
        }//end else
        return returnValue;
    }//end getValue

    /**
     * @return the number of Coins that are in this Wad
     */
    public int getSize(){
        return vCoins.size();
    }//end getSize

    /**
     * Removes _c from this Wad and puts it into _w
     * @param _c
     * @param _w
     */
    public void move (Coin _c, Wad _w) {
        if (!vCoins.contains(_c)) {
                //throw exception
        }//end if
        else {
                vCoins.remove(_c);
                _w.add(_c);
        }//end else
    }//end void move

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s="";
        Iterator <Coin> it = vCoins.iterator ();
        while (it.hasNext ()) {
           s+=String.valueOf(it.next().getValue())+", ";
        }//end while
        return s;
    }//end toString

    /**
     * @param _c
     * @return
     */
    public boolean contains (Coin _c) {
        return vCoins.contains(_c);
    }//end contains

    /**
     * @param n
     * @return the Coin at index <code> n
     * @throws IndexOutOfBoundsException
     */
    public Coin at(int n) throws IndexOutOfBoundsException {
        if (n<0||n>this.getSize()){
                IndexOutOfBoundsException e = new IndexOutOfBoundsException("Index out of bounds");
                throw e;
        }//end if
        else {
                return vCoins.elementAt(n);
        }//end else
    }//end at(int n)
}//end class Wad

