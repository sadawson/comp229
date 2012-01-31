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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This provides console interaction by the user into the game of the taxman objects
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class TaxmanCLI {
    //Attributes
    public static Game g;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
                g = new Game(6);
        } catch (TaxmanError e) {
                System.out.println();
        }
        while (g.winner()==null) {
                // Print state of play
                System.out.println("The current state of play:");
                System.out.println(g.toString());

                try {
                        String playerInput;
                        System.out.print("Please choice a coin to take: ");
                        playerInput=readLine();
                        if (playerInput==null) {
                                System.out.println("End of file detected \n Exiting");
                                return;
                        }//end if
                        int choice = Integer.parseInt(playerInput);

                        g.playerTakes(choice);
                } catch (NumberFormatException e) {
                        System.out.println("The input contained invalid characters.");
                } catch (TaxmanError e) {
                        System.out.println(e.getMessage());
                }//end try/catch
        }//end while

        //print Winner
        if (g.getT().getPurse().getValue()>g.getP().getPurse().getValue()) {
                System.out.println("Taxman won\n"+g.toString());
                //System.out.println("Taxman won... oh no! " + g.getT().getPurse().getValue() + "{"+g.getT().getPurse().toString() + "}" + " to the players "+ g.getP().getPurse().getValue() + "{"+g.getP().getPurse().toString() + "}");
        } else {
                System.out.println("Player won\n"+g.toString());
                System.out.println("Player won... yay! " + g.getP().getPurse().getValue() + " to the taxmans "+ g.getT().getPurse().getValue());
        }//end else
    }//end main

    /**
     *  Utility method gets and checks console input
     * @return
     */
    private static String readLine() {
        String input="";
        try {
                input = in.readLine();
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        return input;
    }//end readLine

}//end class TaxmanCLI

