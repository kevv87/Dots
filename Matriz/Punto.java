package Matriz;

/**
 * Clase Punto
 */
public class Punto {

    private int id; //
    private int boolInt; // integer que sirve de bool (0, 1 ,2)
    private ListaSimple listaRelaciones; //lista que guarda relacion de punto con puntos adyacentes
    private Punto siguiente; //puntero a siguiente punto.

    /**
     * Constructor
     * @param id posicion en X del punto.
     */
    Punto(int id){
        this.id = id;
        boolInt = 0;
        listaRelaciones = new ListaSimple();
        siguiente = null;
    }

    // GETTERS


    public int getId() { return id; }

    public Punto getSiguiente() {
        return siguiente;
    }

    public int getBoolInt() {
        return boolInt;
    }

    public ListaSimple getListaRelaciones() {
        return listaRelaciones;
    }

    //SETTERS

    public void setBoolInt(int boolInt) {
        this.boolInt = boolInt;
    }

    public void setListaRelaciones(ListaSimple listaRelaciones) {
        this.listaRelaciones = listaRelaciones;
    }

    public void setSiguiente(Punto siguiente) {
        this.siguiente = siguiente;
    }

    public void setId(int id) { this.id = id; }

}
