package Matriz;

public class Matriz {

    private ListaPuntos headMatriz;

    /**
     * Constructor por defecto
     */
    
    Matriz(){
        headMatriz = null;
    }

    //GETTER
    public ListaPuntos getHead() {
        return headMatriz;
    }

    //SETTER
    public void setHead(ListaPuntos head) {
        this.headMatriz = head;
    }
}
