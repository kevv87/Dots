/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;

/**
 *
 * @author kevv87
 */
public class ColaMensajes {
    
    private Mensaje first;
    private int tamanio;
    
    public ColaMensajes(){
        first = null;
        tamanio = 0;
    }
    
    public void enqueue(Mensaje mensaje){
        if(first == null){
            first = mensaje;
        }else{
            first.setSiguiente(mensaje);
        }
        tamanio++;
    }
    
    public Mensaje dequeue(){
        Mensaje result = first;
        if(first != null){
            first = first.getSiguiente();
        }
        tamanio--;
        return result;
    }
    
    public Mensaje peek(){
        return first;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    public static void main(String args[]){
        ColaMensajes colita = new ColaMensajes();
        colita.enqueue(new Mensaje("Ala"));
        colita.enqueue(new Mensaje("Alas"));
        System.out.println(colita.dequeue().getAccion());
        System.out.println(colita.dequeue().getAccion());
    }
    
    
}
