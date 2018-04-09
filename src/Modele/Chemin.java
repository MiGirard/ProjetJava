/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julien
 */
public class Chemin {
    
    private List liste;
    
    public Chemin(){
        this.liste = new ArrayList<Case>();
    }
    
    public Chemin(List l){
        this.liste = l;
    }
    
}
