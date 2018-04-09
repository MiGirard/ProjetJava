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
public enum Lien {
    TRAIT_HORIZONTALE(0, "./img/lien/trait_horizontal.png"),
    TRAIT_VERTICALE(1, "./img/lien/trait_vertical.png"),
    ANGLE_SUP_GAUCHE(2, "./img/lien/angle_sup_gauche.png"),
    ANGLE_INF_GAUCHE(3, "./img/lien/angle_inf_gauche.png"),
    ANGLE_SUP_DROIT(4, "./img/lien/angle_sup_droit.png"),
    ANGLE_INF_DROIT(5, "./img/lien/angle_inf_droit.png"),
    CASE_VIDE(6, "./img/lien/casevide.png");
    
    private final int value;
    private final String chemin;    
    Lien(int value, String chemin){
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
