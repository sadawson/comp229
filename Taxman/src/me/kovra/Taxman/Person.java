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
 * This defines some the behaviour inherient to both the taxman and the player.
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Person {
    //Attributes
    private Purse p = new Purse();
    String name;

    //Constructor
    public Person (String _name) {
        name = _name;
    }//end constructor

    //Method
    public void take (Coin _c, Kitty _k) {
        _k.move(_c, p);
    }//end take

    /**
     * Removes first coin found that has value _n in _k and puts it this.purse
     * @param _n
     * @param _k
     */
    public void take (int _n, Kitty _k) {
        for (int i=0; i<_k.getSize();i++) {
                if (_k.at(i).getValue()==_n) {
                        _k.move(_k.at(i), p);
                        i=_k.getSize()+1;
                }//end if
        }//end for
    }//end take

    /**
     * @return the purse of the person
     */
    public Purse getPurse() {
        return p;
    }//end getPurse

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Coins- "+p.toString()+"Total- "+p.getValue();
    }//end toString

}//end class Person

