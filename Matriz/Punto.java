package Matriz;

/**
 * Clase Punto
 */
public class Punto {

    private int id; //
    private int n_fila;
    private int n_columna;



    private int boolInt; // integer que sirve de bool (0, 1 ,2)
    private ListaSimple listaRelaciones; //lista que guarda relacion de punto con puntos adyacentes
    private Punto siguiente; //puntero a siguiente punto.


    /**
     * Constructor por defecto
     */
    public Punto(){
        this.id = 0;
        boolInt = 0;
        listaRelaciones = new ListaSimple();
        siguiente = null;
    }

    /**
     * Constructor
     * @param id posicion en X del punto.
     */

    public Punto(int id){
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

    public int getN_fila() { return n_fila; }

    public int getN_columna() { return n_columna; }
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

    public void setN_columna(int n_columna) { this.n_columna = n_columna; }

    public void setN_fila(int n_fila) { this.n_fila = n_fila; }
}
