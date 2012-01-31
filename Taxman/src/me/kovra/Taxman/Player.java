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
 * The player of the game of the taxman
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Player extends Person {

    //Constructor
    public Player(String name) {
        super(name);
    }//End Constructor

    //Method
    /**
     * Takes <code>_c</code> checks if that coin is part of the taxable coins in the <code>Kitty _k</code>.
     * If it is taxable. Puts <code>Coin _c</code> in <code>this.Purse</code>.
     * Takes all coins in the kitty that were divisors of <code>Coin _c</code> and puts them into the taxmans purse.
     * Calls the Kitty to update
     * If that was the last move of the game takes all the coins in the kitty and puts them into the taxmans purse
     * @param _c
     * @param _k
     * @param _t
     * @throws TaxmanError
     */
    public void take(Coin _c, Kitty _k, Taxman _t) throws TaxmanError{
        if (_k.getTaxableCoins().contains(_c)) {
                super.take(_c, _k);
                for (int i = 1; i<_c.getValue(); i++) {
                        if (_c.getValue()%i==0) {
                                _t.take(i, _k);
                        }// end if
                }//end for
                _k.update();
                if(_k.getTaxableCoins().isEmpty()) {
                        //that was the last time the player takes coins taxman gets the rest
                        _t.take(_k);
                }//end if
        }//end if
        else {
                TaxmanError e = new TaxmanError("The Taxman must always get something. Player can only take a coin if there is at least one proper divisor remaining in the game.");
                throw e;
        }//end if
    }//end take

}//end class Player

