package Dots.Clases;

public class ListaPuntos {

    private int posY; //posicion de la fila
    private int tamanio; //cantidad de puntos en la fila
    private Punto head; //puntero a primer punto de la fila
    private ListaPuntos siguiente;//puntero a siguiente fila

    /**
     * Constructor
     * @param posY posicion de la fila
     */
    ListaPuntos(int posY){
        this.posY = posY;
        head = null;
        siguiente = null;
    }

    //GETTERS

    public int getPosY() {
        return posY;
    }

    public Punto getHead() {
        return head;
    }

    public ListaPuntos getSiguiente() {
        return siguiente;
    }

    public int getTamanio() {
        return tamanio;
    }


    //SETTERS

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setHead(Punto head) {
        this.head = head;
    }

    public void setSiguiente(ListaPuntos siguiente) {
        this.siguiente = siguiente;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * Agrega un punto al final de la lista
     * @param valor valor en X (columna) del punto.
     */

    public void agregarAlFinal(int valor) {
        // Define un nuevo Punto.
        Punto nuevo = new Punto(valor);
        // Consulta si la lista esta vacia.
        if (head == null) {
            // Inicializa la lista agregando como inicio al nuevo punto.
            head = nuevo;
            // Caso contrario recorre la lista hasta llegar al ultimo punto
            // y agrega el nuevo.
        } else {
            // Crea una copia de la lista.
            Punto tmp = head;
            // Recorre la lista hasta llegar al ultimo punto.
            while (tmp.getSiguiente() != null) {
                tmp = tmp.getSiguiente();
            }
            // Agrega el nuevo punto al final de la lista.
            tmp.setSiguiente(nuevo);
        }
        // Incrementa el contador de tamaño de la lista
        tamanio++;
    }

}
