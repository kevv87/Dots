package Dots.Clases;

public class Matriz {

    private ListaPuntos head;

    /**
     * Constructor por defecto
     */
    Matriz(){
        head = null;
    }

    //GETTER
    public ListaPuntos getHead() {
        return head;
    }

    //SETTER
    public void setHead(ListaPuntos head) {
        this.head = head;
    }
}
