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
public class Nodo<T> {
    private T Elemento;
    private Nodo Siguiente;
    
    public Nodo(T obj){
        Elemento=obj;
        Siguiente=null;
    }

    Nodo() {
        Elemento=null;
        Siguiente=null;
    }

    public T getElemento() {
        return Elemento;
    }

    public void setElemento(T elemento) {
        this.Elemento = elemento;
    }

    public Nodo getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.Siguiente = siguiente;
    }
    
    
}
