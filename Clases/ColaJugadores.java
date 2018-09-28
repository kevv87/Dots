/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 * La cola de jugadores de la pantalla de espera.
 * @author kevv87
 */
public class ColaJugadores {
    
    private Player first;
    private int tamanio;

    public int getTamanio() {
        return tamanio;
    }
    
    /**
     * Constructor
     */
    public ColaJugadores(){
        first = null;
        tamanio = 0;
    }
    
    
    /**
     * Encolar.
     * @param jugador Instancia de la clase jugador a agregar a la cola
     */
    public void enqueue(Player jugador){
        if(first == null){
            first = jugador;
        }else{
            first.setSiguiente(jugador);
        }
        tamanio++;
    }
    
    /**
     * Desencolar.
     * @return Jugador en la cabeza de la cola
     */
    public Player dequeue(){
        Player result = first;
        first = first.getSiguiente();
        tamanio--;
        return result;
        
    }
    
    /**
     * Revisa el primer elemento de la cola, pero no lo remueve
     */
    public Player peek(){
        return first;
    }
    
}
