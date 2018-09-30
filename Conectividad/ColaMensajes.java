package Conectividad;

/**
 * Implementacion de una cola de mensajes
 * @author kevv87
 */
public class ColaMensajes {
    
    private Mensaje first;  //
    private int tamanio;  // Tamanio de la cola
    
    /**
     * Constructor
     */
    public ColaMensajes(){
        first = null;
        tamanio = 0;
    }
    
    /**
     * Encolar.
     * @param mensaje Mensaje a encolar
     */
    public void enqueue(Mensaje mensaje){ 
        if(first == null){
            first = mensaje;
        }else{
            Mensaje aux = first;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(mensaje);
        }
        tamanio++;
    }
    
    /**
     * Desencolar
     * @return Mensaje desencolado.
     */
    public Mensaje dequeue(){ 
        Mensaje result = first;
        if(first != null){
            first = first.getSiguiente();
        }
        tamanio--;
        return result;
    }
    
    /**
     * Retorna lo que esta en la cabeza de la cola sin eliminarlo
     * @return Mensaje en la cabeza de la cola
     */
    public Mensaje peek(){  
        return first;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    
}
