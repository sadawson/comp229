package me.kovra.Taxman;

public class TaxmanTest {
    public static void main(String [] args) {
        Game g=null;
        try {
                g = new Game(8);
                while (!g.finished()) {
                        int j = g.suggestMove();
                        System.out.println("Suggest: " + j);
                        System.out.println(g.getK().toString());
                        g.playerTakes(j);

                        }
                        System.out.println("Winner"+g.winner().getClass().toString());



        } catch (TaxmanError e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    }


}//end class TaxmanTest

