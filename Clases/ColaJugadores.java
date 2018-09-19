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

    public int getTamanio() {
        return tamanio;
    }
    private int tamanio;
    
    public ColaJugadores(){
        first = null;
        tamanio = 0;
    }
    
    public void enqueue(Player jugador){
        Player tmp = first;
        first = jugador;
        first.setSiguiente(tmp);
        tamanio++;
    }
    
    public Player dequeue(){
        Player result = first;
        first = first.getSiguiente();
        tamanio--;
        return result;
        
    }
    
    public Player peek(){
        return first;
    }
    
}
