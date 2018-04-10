/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;
import VueControleur.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 *
 * @author p1506391
 */
public class Grille extends Observable {
    
    private ArrayList<Case> listeCase = new ArrayList<Case>();
    private ArrayList<Chemin> listeChemin = new ArrayList<Chemin>();

    private Case[][] tab;
    private int lastC, lastR = -1;
    VueControleur vc = new VueControleur();
    
    
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
        listeCase.clear();
        notifyObservers();
    }
    
    public void stopDD(int c, int r) { 
        Chemin chem = new Chemin();
        chem = chem.verifierChemin(listeCase);
        if(chem != null){
            System.out.println("Chemin Ajouté");
            listeChemin.add(chem);
            System.out.println(listeChemin);
        }
        else {
            vc.loose();
        }     
        if(listeChemin.size() == 2){
            System.out.println(listeChemin.get(0));
            System.out.println(listeChemin.get(1));
            if(verifierParcours(listeChemin)){
                vc.win();
            }
            else{
                vc.loose();
            }
        }
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
        listeCase.forEach(System.out::println);
        setChanged();
        notifyObservers();
        /*for(Case ca: (ArrayList<Case>)liste){
            System.out.println("Column : " + ca.getColumn() + ", Row : "+ca.getRow());
        }*/
    }
    
    public void parcoursDD(int c, int r) {
        // TODO
        listeCase.add(new Case(this.tab[c][r].getSymbole(), this.tab[c][r].getLien(), r, c));
        lastC = c;
        lastR = r;
        System.out.println("parcoursDD : " + c + "-" + r);
        setChanged();
        notifyObservers();
        listeCase.forEach(System.out::println);
    }
    
    private boolean verifierParcours(ArrayList<Chemin> l){
        boolean [][] tabTest = new boolean[tab.length][tab[0].length];
        for(int i = 0; i<tabTest.length; i++){
            for(int j = 0; j<tabTest[i].length; j++){
                tabTest[i][j] = false;
            }
        }
        
        for(int i = 0; i < l.size();i++){
            Chemin ch = l.get(i);
            List listecs =  ch.getListe();
            for(int j = 0; j < listecs.size(); j++){
                Case cs = (Case) listecs.get(j);
               // System.out.println(tabTest[cs.getRow()][cs.getColumn()]);
                if(!tabTest[cs.getRow()][cs.getColumn()]){
                    tabTest[cs.getRow()][cs.getColumn()] = true;
                    //System.out.println(l.get(i));
                    //System.out.println(tabTest[cs.getRow()][cs.getColumn()]);
                }
                else{
                   // System.out.println("Chemin déja pris");
                    //System.out.println(i+ "," +j);
                   // System.out.println(l.get(i));
                    return false;
                }
            }  
        }
        
         for(int i = 0; i<tabTest.length; i++){
            for(int j = 0; j<tabTest[i].length; j++){
                if(tabTest[i][j] == false){
                    System.out.println("Grille pas parcouru en entier");
                    return false;
                }
            }
        }
        return true;
    }
    
    public void setTab(Case[][] tab){
        this.tab = tab;
    }

    public List getListeChemin() {
        return listeChemin;
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
