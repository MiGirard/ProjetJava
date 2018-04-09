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
public class Case {
    
    private Symbole symbole;
    private Lien lien;
    private int row;
    private int column;
    

    public Case(Symbole symbole, int row, int column) {
        this.symbole = symbole;
        this.row = row;
        this.column = column;
        this.lien = null;
    }

    public Case(Lien lien, int row, int column) {
        this.lien = lien;
        this.row = row;
        this.column = column;
        this.symbole = null;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public Lien getLien() {
        return lien;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
    }

    public void setLien(Lien lien) {
        this.lien = lien;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
    
    
    
}
