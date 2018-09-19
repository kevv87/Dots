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
public class ColaJugadores extends ListaSimple{
    
    
    /**
     * Constructor
     */
    public ColaJugadores(){
        super();
        
    }
    
    /**
     * Introduce a un nuevo jugador en la cola de espera.
     * @param player Jugador a introducir en la cola.
     */
    public void enqueue(Player player){
        insertarPorPosicion(tamanio-1, new Nodo(player));
    }
    
    /**
     * Quita el primer jugador de la cola
     * @return El jugador al frente de la cola
     */
    public Player dequeue(){
        if(tamanio > 0){
        Nodo result = inicio;
        removerPorPosicion(0);
        return (Player) result.getValor();
        }else{
            return null;
        }
    }
    
    /**
     * Retorna el primer elemento de la cola, sin eliminarlo
     * @return Objeto player
     */
    public Player peek(){
        return (Player) inicio.getValor();
    }
    
}
