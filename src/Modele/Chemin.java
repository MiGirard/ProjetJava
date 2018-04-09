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
    
    public Chemin(ArrayList<Case> l){ 
        this.liste = l;
    }
    
    public static Chemin verifierChemin(ArrayList<Case> l){
        if(l.get(0).getSymbole() == null || l.get(l.size() - 1).getSymbole() == null){ //Teste si les cases de début et de fin contiennent bien un symbole
            System.out.println("Mauvais début/fin");
            return null;
        }
        else if(l.get(0).getSymbole() != l.get(l.size() - 1).getSymbole()){ //Teste si ces symboles sont identitique sinon le joueur perd
            System.out.println("Symboles non identiques");
            return null;
        }
        
        for(int i = 0; i < l.size(); i++){ //Teste si les cases intermédiaires ont bien toutes un lien
            ArrayList<Case> interm = l;
            interm.remove(i);
            for(int j = 0; j < interm.size(); j++){ //Teste si la case testé est déja présente dans le chemin 
                if(l.get(i).equals(interm.get(j))){
                    System.out.println("Même(s) case(s) présente(s) plusieurs fois");
                    return null;
                }
            }
            if(l.get(i).getLien() == null && l.get(i).getSymbole() == null){
                System.out.println("Pas de symbole et de lien");
                return null;
            }
        }
        
        System.out.println("Chemin ajouté");
        Chemin ch = new Chemin(l);
        return ch;
        
    }

    public List getListe() {
        return liste;
    }

    public void setListe(List liste) {
        this.liste = liste;
    }
    
    
    
}
