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
 * The taxman object
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Taxman extends Person {
    /**
     * @param name
     */
    public Taxman(String name) {
        super(name);
    }//end constructor

    /**
     * Takes all the coins in <code>Kitty _k</code> and puts them into the <code>Purse</code> of <code>this</code> taxman
     * @param _k
     */
    public void take(Kitty _k) {
        for (int i = 0; i<_k.getSize();i++) {
                this.getPurse().add(_k.at(i));
        }
        _k.getvCoins().removeAllElements();
    }//end take

}//end class Taxman

