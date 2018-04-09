/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Julien
 */
public enum Symbole {
    ETOILE(100, "./img/symbole/etoile.png"),
    CARRE(200, "./img/symbole/carre.png"),
    TRIANGLE(300, "./img/symbole/triangle.png");
    
    private final int value;
    private final String chemin;    
    Symbole(int value, String chemin){
        this.value = value;
        this.chemin = chemin;
    }

    public int getValue() {
        return value;
    }

    public String getChemin() {
        return chemin;
    }
    
    
    
}
