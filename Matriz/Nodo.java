package Matriz;

/**Class Nodo
 * Cada nodo representa una posicion de puntos adyacentes a un punto en especifico.
 */

public class Nodo {

    private Object valor;
    private Nodo siguiente;

    /**
     * Constructor que inicializamos el valor de las variables.
     */
    Nodo(){
        valor = 0;
        siguiente = null;
    }


    /**
     * Constructor:
     * @param dato: dato de cualquier tipo
     */
    //compila
    Nodo(int dato){
        valor = dato;
        siguiente = null;
    }


    //GETTERS
    public Object getValor() {
        return valor;
    }


    public Nodo getSiguiente() {
        return siguiente;
    }

    //SETTERS
    public void setSiguiente(Nodo next) {
        this.siguiente = next;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
