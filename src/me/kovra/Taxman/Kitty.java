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

import java.util.Vector;

/**
 * An extension of a wad. A collection of coins in the game of the taxman from which both the taxman and player take coins.
 * @version 1.3
 * @since 1.1
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Kitty extends Wad {
    //Attributes
    Vector <Coin> taxable= new Vector <Coin>();

    //Constructor
    /**
     * Creates a kitty with <code>n</code> number of <code>Coin</codes> objects. Ranging in value from <code>1</code> to <code>n</code>.
     * Every coin at the game start is also added to the <code>taxable</code> vector.
     * @param n
     * @throws TaxmanError
     */
    public Kitty(int n) throws TaxmanError {
        super(n);
        for (int i = 0; i < this.getSize(); i++) {
                taxable.add(this.at(i));
        }//end for
    }//end constructor

    //Methods
    public Vector <Coin> getCoins () {
        return getvCoins();
    }//end

    /**
     * @return the vector <code>taxable</code> which stores all the coins that are still taxable in the game.
     */
    public Vector <Coin> getTaxableCoins() {
        return taxable;
    }//end t


    /* (non-Javadoc)
     * @see taxman.Wad#toString()
     */
    public String toString() {
        String s="";
        for (int i=0;i<this.getSize();i++) {
                if (taxable.contains(this.at(i))) {
                        s+="_"+String.valueOf(this.at(i).getValue())+"_, ";
                }//end if
                else {
                        s+=String.valueOf(this.at(i).getValue())+", ";
                }//end else
        }//end for
        return s;
    }//end String toString


    /**
     * At any given point at execution this method will add all the <code>Coin</code> objects in the <code>Kitty</code> that are still taxable.
     */
    public void update() {
        taxable=new Vector <Coin>();
        for (int i = 0; i < this.getSize(); i++) {
                for (int x = 0; x < this.getSize();x++) {
                        if (this.at(i).getValue()>this.at(x).getValue()
                                  &&
                                  this.at(i).getValue()%this.at(x).getValue()==0) {
                                taxable.add(this.at(i));
                                x=this.getSize();
                        }//end if
                }//end for x
        }//end for i
    }//end void update
}//end class

