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
 * Custom exception object. Stores a message for the user
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class TaxmanError extends Exception
{
    private static final long serialVersionUID = -1001216979081256204L;
    //Attributes
    String message;

    //Constructor
    /**
     * @param _message
     */
    public TaxmanError (String _message) {
        super(_message);
    }//end constructor
}//end class

