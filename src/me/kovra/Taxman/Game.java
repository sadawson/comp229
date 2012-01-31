package me.kovra.Taxman;

/**
 * Game has a <code>Player</code>, <code>Taxman</code> and a <code>Kitty</code>. This class defines how these three interact.
 * @version 1.3
 * @since 1.3
 * @author Simon Dawson
 * @author 40983129
 * @author dawson.sa@gmail.com
 */
public class Game {

    //Attributes
    private Player p;
    private Taxman t;
    private Kitty k;
    private int numberOfCoins;

    //Constructor
    /**
     * Starts a new game of the taxman with <code>n</code> coins
     * @param n
     * @throws TaxmanError
     */
    public Game (int n) throws TaxmanError {
        p = new Player("Player");
        t = new Taxman("Taxman");
        k = new Kitty(n);
        numberOfCoins=n;
    }//end constructor

    //Methods
    /**
     * @return Player in this game
     */
    public Player getP() {
        return p;
    }//end getP

    public int suggestMove() throws TaxmanError {
        if (finished()){
                TaxmanError e = new TaxmanError ("Can't suggest a move for a game that is already finished");
                throw e;
        }
        else{
                Integer largestDifference=null;
                Coin bestMove=null;
                for (int i=0;i<k.getTaxableCoins().size();i++) {
                        Integer taxmanTotal=0;
                        for (int x=0;x<k.getCoins().size();x++) {
                                if (k.getTaxableCoins().get(i).getValue() > k.getCoins().get(x).getValue() &&
                                    k.getTaxableCoins().get(i).getValue()%k.getCoins().get(x).getValue()==0) {
                                        taxmanTotal+=k.getCoins().get(x).getValue();
                                }//end if
                        }//end for
                        /*if (largestDifference==null) {
                                largestDifference=k.getCoins().get(i).getValue()-taxmanTotal;
                        }//end if*/
                        if (largestDifference==null || k.getTaxableCoins().get(i).getValue()-taxmanTotal>largestDifference) {
                                largestDifference=k.getTaxableCoins().get(i).getValue()-taxmanTotal;
                                bestMove=k.getTaxableCoins().get(i);
                        }//end if
                }//end for
                return bestMove.getValue();
        }//end else
    }//end suggestMove


    /**
     * @return the Kitty available in this game
     */
    public Kitty getK() {
        return k;
    }

    /**
     * @return The number of coins there are total in the game
     */
    public int getNumberOfCoins () {
        return numberOfCoins;
    }//end getNumberOfCoins ()

    /**
     * @return Taxman in this game
     */
    public Taxman getT() {
        return t;
    }//end getT

    /**
     * @return <code>true</code> if and only if there are no more taxable coins in the kitty
     */
    public boolean finished() {
        return k.getTaxableCoins().isEmpty();
    }//end finished

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s="";
        s+="Kitty: "+k.toString() + "\n";
        s+="Taxman: "+t.toString() + "\n";
        s+="Player: "+p.toString();
        return s;
    }//end if

    /**
     * If <code>this.Kitty</code> contains a <code>Coin</code> with value </code> n that <code>Coin</code> is removed from <code>this.Kitty</code> and put in the <code>Purse</code> of the <code>Player</code>.
     * @param n
     * @throws TaxmanError
     */
    public void playerTakes(int n) throws TaxmanError {
        boolean foundCoin=false;
        for (int i=0; i<k.getSize();i++) {
                if (k.at(i).getValue()==n) {
                        p.take(k.at(i), k, t);
                        foundCoin=true;
                        i=k.getSize();
                }// end if
        }//end for
        if(!foundCoin) {
                TaxmanError e = new TaxmanError("The value supplied doesn't match a coin");
                throw e;
        }//end if
    }//end playerTakes

    /**
     * @return <code>null</code> should the game not be over but if it is. It returns the <code>Player</code> or <code>Taxman</code>
     */
    public Person winner() {
        if (this.finished()) {
                if (p.getPurse().getValue()>t.getPurse().getValue()) {
                        return p;
                } else {
                        return t;
                }//end if
        }//end if
        else {
                return null;
        }//end if
    }//end if
}

