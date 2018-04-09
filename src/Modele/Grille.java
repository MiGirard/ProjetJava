/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author p1506391
 */
public class Grille extends Observable {
    
    private List liste = new ArrayList<Case>();    
    private Case[][] tab;
    
    private int lastC, lastR;
    
    
    public Grille(){
        tab = new Case[3][3];
        for(int i = 0; i < this.tab.length; i++){
            for(int j = 0; j < this.tab[0].length; j++){
                tab[i][j] = new Case(Lien.CASE_VIDE ,i, j);
            }
        }
        tab[0][0] = new Case(Symbole.CARRE, 0, 0);
        tab[1][2] = new Case(Symbole.CARRE, 1, 2);
        tab[2][0] = new Case(Symbole.ETOILE, 2, 0);
        tab[2][2] = new Case(Symbole.ETOILE, 2, 2);
    }
    
    public void startDD(int c, int r) {
        // TODO
        System.out.println("startDD : " + c + "-" + r);
        setChanged();
        notifyObservers();
        liste.clear();
        liste.add(new Case(this.tab[c][r].getSymbole(), c, r));
    }
    
    public void stopDD(int c, int r) {
        // TODO
        liste.add(new Case(this.tab[c][r].getSymbole(), c, r));
        Chemin ch = Chemin.verifierChemin((ArrayList<Case>)liste);
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
        setChanged();
        notifyObservers();
        /*for(Case ca: (ArrayList<Case>)liste){
            System.out.println("Column : " + ca.getColumn() + ", Row : "+ca.getRow());
        }*/
    }
    
    public void parcoursDD(int c, int r) {
        // TODO
        liste.add(new Case(this.tab[c][r].getLien(), c,r));
        lastC = c;
        lastR = r;
        System.out.println("parcoursDD : " + c + "-" + r);
        setChanged();
        notifyObservers();
    }
    
    private boolean verifierParcours(ArrayList<Case> l){
        
        return false;
    }
    
    public void setTab(Case[][] tab){
        this.tab = tab;
    }
    
    public Case[][] getTab(){
        return this.tab;
    }

    public int getLastC() {
        return lastC;
    }

    public int getLastR() {
        return lastR;
    }
    
    
}
