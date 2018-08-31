package Dots.Clases;

public class ListaPuntos {

    private int posY; //posicion de la fila
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



}
