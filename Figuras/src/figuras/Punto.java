/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

/**
 *
 * @author Sebastián
 */

/**
 * Clase Punto
 */
public class Punto {

    private int PosX; // valor de columna de matriz.
    private int PosY;
    private Punto Siguiente; //puntero a siguiente punto.
    private boolean EnUso;  //si pertenece a un perimetro cerrado

    /**
     * Constructor
     * @param columna posicion en X del punto.
     */
    Punto(int columna, int fila){
        PosX = columna;
        PosY= fila;
        Siguiente = null;
        EnUso = false;
    }

    // GETTERS
    public int getPosX() {
        return PosX;
    }

    public Punto getSiguiente() {
        return Siguiente;
    }

    public int getPosY() {
        return PosY;
    }

    public boolean isEnUso() {
        return EnUso;
    }
    


    //SETTERS


    public void setSiguiente(Punto siguiente) {
        this.Siguiente = siguiente;
    }

    public void setPosX(int posX) {
        this.PosX = posX;
    }

    public void setPosY(int posY) {
        this.PosY = posY;
    }

    public void setEnUso(boolean EnUso) {
        this.EnUso = EnUso;
    }
    

}
