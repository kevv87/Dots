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
public class NodoDoble {
    private Punto Elemento;
    private NodoDoble Siguiente;
    private NodoDoble Anterior;
    
    public NodoDoble(Punto P){
        Elemento=P;
        Siguiente=null;
        Anterior=null;
    }

    public Punto getElemento() {
        return Elemento;
    }

    public NodoDoble getSiguiente() {
        return Siguiente;
    }
    
    public NodoDoble getAnterior() {
        return Anterior;
    }

    public void setElemento(Punto elemento) {
        this.Elemento = elemento;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.Siguiente = siguiente;
    }
    
    public void setAnterior(NodoDoble anterior) {
        this.Anterior = anterior;
    }
    
    
}
