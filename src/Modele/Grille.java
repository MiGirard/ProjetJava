/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;
import VueControleur.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    private int nbChemin;
    private VueControleur vc = new VueControleur();
    
    
    public Grille(){
        
        grille3x3();
        //grille4x4();
    }
    
    public Grille(int x){
        switch(x){
            case 3:
                grille3x3();
                break;
            case 4:
                grille4x4();
                break;
            default:
                grille3x3();
                break;
        }
    }
    
    public void startDD(int c, int r) {
        // TODO
        System.out.println("startDD : " + c + "-" + r);
        listeCase.clear();
        /*setChanged();
        notifyObservers();*/
    }
    
    public void stopDD(int c, int r) { 
        Chemin chem = new Chemin();
        chem = chem.verifierChemin(listeCase);
        if(chem != null){
            System.out.println("Chemin Ajouté");
            listeChemin.add(chem);
            modifierTab(chem);
        }
        else {
            vc.loose();
        }
        
        setChanged();
        notifyObservers();
        
        if(listeChemin.size() == this.nbChemin){
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
        //listeCase.forEach(System.out::println);
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
        /*setChanged();
        notifyObservers();*/
        listeCase.forEach(System.out::println);
    }
    
    private boolean verifierParcours(ArrayList<Chemin> l){
        boolean [][] tabTest = new boolean[this.tab.length][this.tab[0].length];
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
                if(!tabTest[cs.getColumn()][cs.getRow()]){
                    tabTest[cs.getColumn()][cs.getRow()] = true;
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
    
    private void modifierTab(Chemin ch){
        ListIterator itr = ch.getListe().listIterator();
        String avant = null, apres = null;
        for(int i = 0; i<ch.getListe().size(); i++){
            Case cas = (Case)ch.getListe().get(i);
            if(cas.getLien() != null){
                
                 /******        TEST CASE D'APRES         ******/
                if(((Case)ch.getListe().get(i+1)).getColumn() == cas.getColumn()){  
                    if(((Case)ch.getListe().get(i+1)).getRow() < cas.getRow()){ //Si la case d'après est au dessus
                        apres = "dessus";
                    }else if(((Case)ch.getListe().get(i+1)).getRow() > cas.getRow()){ // Si la case d'après est en dessous
                        apres = "dessous";
                    }
                }else if(((Case)ch.getListe().get(i+1)).getRow() == cas.getRow()){ 
                    if(((Case)ch.getListe().get(i+1)).getColumn() < cas.getColumn()){ //Si la case d'après est à gauche
                        apres = "gauche";
                    }else if(((Case)ch.getListe().get(i+1)).getColumn() > cas.getColumn()){ //Si la case d'après est à droite
                        apres = "droite";
                    }
                }
                
                /******        TEST CASE D'AVANT         ******/
                if(((Case)ch.getListe().get(i-1)).getColumn() == cas.getColumn()){ 
                    if(((Case)ch.getListe().get(i-1)).getRow() < cas.getRow()){ //Case d'avant en dessus
                           avant = "dessus";
                    }else if(((Case)ch.getListe().get(i-1)).getRow() > cas.getRow()){ //Case d'avant au dessous
                        avant = "dessous";
                    }
                }else if(((Case)ch.getListe().get(i-1)).getRow() == cas.getRow()){ 
                    if(((Case)ch.getListe().get(i-1)).getColumn() < cas.getColumn()){ //Case d'avant à gauche
                        avant = "gauche";
                    }else if(((Case)ch.getListe().get(i-1)).getColumn() > cas.getColumn()){ //Case d'avant à droite
                        avant = "droite";
                    }
                }
                System.out.println(avant+" + "+apres);
                Lien lien = determinerLien(avant, apres);
                this.tab[cas.getColumn()][cas.getRow()].setLien(lien);
                cas.setLien(lien);
                
            }
        }
    }
    
    private Lien determinerLien(String avant, String apres){
        switch(avant){
            case "dessus":
                switch(apres){
                    case "dessous":
                        return Lien.TRAIT_VERTICALE;
                    case "gauche":
                        return Lien.ANGLE_SUP_GAUCHE;
                    case "droite":
                        return Lien.ANGLE_SUP_DROIT;
                    default :
                        return Lien.CASE_VIDE;
                }
            case "dessous":
                switch(apres){
                    case "dessus":
                        return Lien.TRAIT_VERTICALE;
                    case "gauche":
                        return Lien.ANGLE_INF_GAUCHE;
                    case "droite":
                        return Lien.ANGLE_INF_DROIT;
                    default :
                        return Lien.CASE_VIDE;
                }
            case "gauche":
                switch(apres){
                    case "dessus":
                        return Lien.ANGLE_SUP_GAUCHE;
                    case "dessous":
                        return Lien.ANGLE_INF_GAUCHE;
                    case "droite":
                        return Lien.TRAIT_HORIZONTALE;
                    default :
                        return Lien.CASE_VIDE;
                }
            case "droite":
                switch(apres){
                    case "dessus":
                        return Lien.ANGLE_SUP_DROIT;
                    case "dessous":
                        return Lien.ANGLE_INF_DROIT;
                    case "gauche":
                        return Lien.TRAIT_HORIZONTALE;
                    default :
                        return Lien.CASE_VIDE;
                }
            default :
                return Lien.CASE_VIDE;
        }

    }
    
    
    
    private void grille3x3(){
        this.tab = new Case[3][3];
        for(int i = 0; i < this.tab.length; i++){
            for(int j = 0; j < this.tab[0].length; j++){
                this.tab[i][j] = new Case(Lien.CASE_VIDE ,i, j);
            }
        }
        this.tab[0][0] = new Case(Symbole.CARRE, 0, 0);
        this.tab[1][2] = new Case(Symbole.CARRE, 1, 2);
        this.tab[2][0] = new Case(Symbole.ETOILE, 2, 0);
        this.tab[2][2] = new Case(Symbole.ETOILE, 2, 2);
        this.nbChemin = 2;
    }
    
    private void grille4x4(){
        this.tab = new Case[4][4];
        for(int i = 0; i < this.tab.length; i++){
            for(int j = 0; j < this.tab[0].length; j++){
                this.tab[i][j] = new Case(Lien.CASE_VIDE ,i, j);
            }
        }
        this.tab[0][0] = new Case(Symbole.CARRE, 0, 0);
        this.tab[3][3] = new Case(Symbole.CARRE, 3, 3);
        this.tab[2][0] = new Case(Symbole.ETOILE, 2, 0);
        this.tab[3][2] = new Case(Symbole.ETOILE, 3, 2);
        this.tab[0][1] = new Case(Symbole.TRIANGLE, 0, 1);
        this.tab[0][3] = new Case(Symbole.TRIANGLE, 0, 3);
        this.nbChemin = 3;
    }
    
    
}
