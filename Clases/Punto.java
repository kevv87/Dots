package Clases;

/**
 * Clase PuntoGr
 */
public class Punto {

    private int id; // valor de columna de matriz.
    private int boolInt; // integer que sirve de bool (0, 1 ,2)
    private ListaSimple listaRelaciones; //lista que guarda relacion de punto con puntos adyacentes
    private Punto siguiente; //puntero a siguiente punto.

    /**
     * Constructor
     * @param columna ID del punto.
     */
    Punto(int columna){
        id = columna;
        boolInt = 0;
        listaRelaciones = new ListaSimple();
        siguiente = null;
    }

    // GETTERS
    public int getID() {
        return id;
    }

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

    public void setID(int id) {
        this.id = id;
    }

}
