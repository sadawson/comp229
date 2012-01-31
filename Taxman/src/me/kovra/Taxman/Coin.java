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

/**
 * Most basic object in the game The Taxman, a coin.
 * @version  1.3
 * @since 1.0
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Coin {
    //Attributes
    private int value;

    //Constructors
    /**
     * @param value
     */
    public Coin(int _value) {
        this.value = _value;
    }//end constuctor

    //Methods

    /**
     * @return returns the value of <code>this</code> coin
     */
    public int getValue() {
        return value;
    }//end getValue

    /**
     * This will return true if this coins value is the same as the coins which is passed in the parameter.
     * @param c
     * @return
     */
    public boolean equals (Coin c){
        return this.getValue()==c.getValue();
    }
}//end class coin

