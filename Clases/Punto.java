package Clases;

/**
 * Clase Punto
 */
public class Punto {

    private int posX; // valor de columna de matriz.
    private int boolInt; // integer que sirve de bool (0, 1 ,2)
    private ListaSimple listaRelaciones; //lista que guarda relacion de punto con puntos adyacentes
    private Punto siguiente; //puntero a siguiente punto.

    /**
     * Constructor
     * @param columna posicion en X del punto.
     */
    Punto(int columna){
        posX = columna;
        boolInt = 0;
        listaRelaciones = new ListaSimple();
        siguiente = null;
    }

    // GETTERS
    public int getPosX() {
        return posX;
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

    public void setPosX(int posX) {
        this.posX = posX;
    }

}
