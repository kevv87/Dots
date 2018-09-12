package Matriz;

public class Matriz {

    private int cant_Filas;
    private int cant_columnas;
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

    public void inicializaMatriz(int c_Filas, int c_columnas){

        ListaPuntos tmp = headMatriz;

        for(int i = 0; i<c_Filas; i++){

        }


    }

    public void agregarFilaAlFinal(int numFila) {
        // Define un nuevo Punto.
        ListaPuntos nuevaFila = new ListaPuntos(numFila);
        // Consulta si la lista esta vacia.
        if (headMatriz == null) {
            // Inicializa la lista agregando como inicio al nuevo punto.
            headMatriz = nuevaFila;
            // Caso contrario recorre la lista hasta llegar al ultimo punto
            // y agrega el nuevo.
        } else {
            // Crea una copia de la lista.
            ListaPuntos tmp = headMatriz;
            // Recorre la lista hasta llegar al ultimo punto.
            while (tmp.getSiguiente() != null) {
                tmp = tmp.getSiguiente();
            }
            // Agrega el nuevo punto al final de la lista.
            tmp.setSiguiente(nuevaFila);
        }
        // Incrementa el contador de tamaño de la lista
        cant_Filas++;
    }

}
