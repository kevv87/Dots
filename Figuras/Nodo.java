/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

/**
 * Clase encargada de la creación de un nodo genérico
 * @author Sebastián
 */
public class Nodo<T> {  //Nodo generico para usar con distintos elementos
    private T Elemento;
    private Nodo Siguiente;
    
    /**
     * Constructor
     * @param <T> Tipo de dato asignado
     * @param obj Objeto del tipo de dato establecido
     */    
    public Nodo(T obj){
        Elemento=obj;
        Siguiente=null;
    }

    /**
    * Dummy constuctor
    */
    Nodo() {
        Elemento=null;
        Siguiente=null;
    }
    
    //  GETTERS
    public T getElemento() {
        return Elemento;
    }
    
    public Nodo getSiguiente() {
        return Siguiente;
    }
    
    //  SETTERS
    public void setElemento(T elemento) {
        this.Elemento = elemento;
    }

    public void setSiguiente(Nodo siguiente) {
        this.Siguiente = siguiente;
    }
    
    
}
