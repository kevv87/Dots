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
    
    Player first;
    
    public ColaJugadores(){
        first = null;
    }
    
    public void enqueue(Player jugador){
        Player tmp = first;
        first = jugador;
        first.setSiguiente(tmp);
    }
    
    public Player dequeue(){
        Player result = first;
        first = first.getSiguiente();
        return result;
    }
    
    public Player peek(){
        return first;
    }
    
}
