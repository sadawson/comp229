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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This provides console interaction by the user into the game of the taxman objects
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class TaxmanGUI {
    //Attribute
    Game g = null;
    private JFrame jfMain = null;
    private JPanel jpPane = null;
    private JMenuBar jmbMenu = null;
    private Vector <ExtendedJButton> vjbCoins=null;
    private JTextField jtfStatus = null;

    //Getters for gui
    private JMenuBar getJmbMenu () {
        if (jmbMenu==null) {
                jmbMenu = new JMenuBar();
            JMenu file = new JMenu("File");
            file.setMnemonic(KeyEvent.VK_F);
            JMenuItem fileClose = new JMenuItem("Close", null);
            fileClose.setMnemonic(KeyEvent.VK_C);
            fileClose.setToolTipText("Exit application");
            fileClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });
            JMenuItem restartGame = new JMenuItem("Restart", null);
            restartGame.setMnemonic(KeyEvent.VK_C);
            restartGame.setToolTipText("Restart");
            restartGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    newGame();
                }
            });
            file.add(restartGame);
            file.add(fileClose);
            jmbMenu.add(file);
        }//end if
        return jmbMenu;
    }//end getJmbMenu ()

    private JFrame getJfMain() {
        if (jfMain==null) {
                jfMain = new JFrame();
                jfMain.setSize(new Dimension(100*g.getNumberOfCoins(),100));
                jfMain.setJMenuBar(getJmbMenu());
                System.out.println("I just tried adding the menu ");
                jfMain.setTitle("The taxman cometh");
                jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jfMain.setContentPane(getJpPane());
        }
        return jfMain;
    }//end getJfMain

    private JPanel getJpPane() {
        if (jpPane==null) {
                jpPane= new JPanel();
                jpPane.setLayout(new GridLayout());
                getvjbCoins ();
                for (int i =0; i < vjbCoins.size();i++){
                        jpPane.add(vjbCoins.elementAt(i));
                }//end for
                jpPane.setBackground(new Color(0,0,0));
        }//end if
        return jpPane;
    }//end getJpPane()

    private Vector <ExtendedJButton> getvjbCoins () {
        if (vjbCoins==null) {
                vjbCoins= new Vector <ExtendedJButton>();
                ExtendedJButton x=null;
                for (int i =0;i<g.getNumberOfCoins();i++) {
                        x=new ExtendedJButton(String.valueOf(i+1),i+1);
                        vjbCoins.add(x);
                }//end for
        }//end if
        return vjbCoins;
    }//end getvjbCoins ()

    @SuppressWarnings("unused")
	private JTextField getJtfStatus() {
        if (jtfStatus==null) {
                jtfStatus=new JTextField();
        }//end if
        return jtfStatus;
    }//end getJtfStatus()


    //Constructors
    public TaxmanGUI () {
        newGame();
    }//end Constructor

    //Methods
    public void newGame() {
        try {
                //Define how many coins
                String response = JOptionPane.showInputDialog(null,
                          "How many coins?",
                          "Enter the number of coins to play with.",
                          JOptionPane.QUESTION_MESSAGE);
                if (response == null) {
                        System.exit(0);
                }//end if
                int n = Integer.parseInt(response);

                //Create game object
                g = new Game(n);

                //Get a new gui
                jfMain=null;
                jpPane = null;
                jmbMenu = null;
                vjbCoins=null;
                jfMain=getJfMain();
                jfMain.setVisible(true);
                updateButtons();

        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number");
                newGame();
        } catch (TaxmanError e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                newGame();
        } //end try catches

    }//end newGame

    public void updateButtons () {
        for (int i=0; i<vjbCoins.size();i++) {
                vjbCoins.elementAt(i).setVisible(false);
                vjbCoins.elementAt(i).setText(String.valueOf(i+1));
        }//end for
        for (int i = 0; i<g.getK().getCoins().size(); i++) {
                vjbCoins.elementAt(g.getK().getCoins().elementAt(i).getValue()-1).setVisible(true);
        }//end for
        for (int i = 0; i<g.getK().getTaxableCoins().size(); i++) {
                vjbCoins.elementAt(g.getK().getTaxableCoins().elementAt(i).getValue()-1).setText("_"+String.valueOf(g.getK().getTaxableCoins().elementAt(i).getValue())+"_");
        }//end for
    }//end for

    public void buttonClicked (int value) throws TaxmanError {
        g.playerTakes(value);
        updateButtons ();
        if (g.winner()!=null) {
                String s="";
                if (g.getT().getPurse().getValue()>g.getP().getPurse().getValue()) {
                        s+="Taxman won\n"+g.toString();
                }
                else {
                        s+="Player won\n"+g.toString();
                }
                int n = JOptionPane.showConfirmDialog(null,
                            s+"\n"
                            + "Play again?\n",
                            "Game over",
                            JOptionPane.YES_NO_OPTION);
                if (n==0){ newGame(); }
                else { System.exit(0);}
        }//end if
    }//end buttonClicked


    public static void main(String[] args) {
        @SuppressWarnings("unused")
		final TaxmanGUI vApplication = new TaxmanGUI();

    }


    /**
     * This provides a simpler interface to the buttons
     * @version 1.3
     * @since 1.3
     * @author Simon Dawson
     * @author 40983129
     * @author dawson.sa@gmail.com
     */
    private class ExtendedJButton extends JButton implements ActionListener
    {
        private static final long serialVersionUID = -5728228960372512148L;
        int value;

        public ExtendedJButton(String name,int _value) {
                super(name);
                value=_value;
                init();
        }//end ExtendedJButton

      private void init() {
        addActionListener(this);
      }//end init

      public void actionPerformed(ActionEvent e) {
          try {
                  buttonClicked(value);
          } catch (TaxmanError e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage());
          }//end try catch
      }//end actionPerformed
    };//end class ExtendedJButton


}

