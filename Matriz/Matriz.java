package Matriz;

import java.sql.SQLOutput;

public class Matriz {

    private int cant_Filas;
    private int cant_columnas;
    private ListaPuntos headMatriz;

    /**
     * Constructor por defecto
     */
    
    public Matriz(){
        cant_Filas = 0;
        cant_columnas = 0;
        headMatriz = null;

    }

    //GETTER
    public ListaPuntos getHead() {
        return headMatriz;
    }


    public int getCant_Filas() {
        return cant_Filas;
    }


    public int getCant_columnas() {
        return cant_columnas;
    }

    //SETTER
    public void setHead(ListaPuntos head) {
        this.headMatriz = head;
    }

    public void setCant_columnas(int cant_columnas) {
        this.cant_columnas = cant_columnas;
    }

    public void setCant_Filas(int cant_Filas) {
        this.cant_Filas = cant_Filas;
    }

    /**
     * Inicializa la matriz con puntos a los que se les asigna integers ordenados de menor a mayor.
     * @param numFila numero de la fila (según el eje y de la matriz).
     */

    public void agregarFilaAlFinal(int numFila) {
        // Define un nuevo Punto.
        ListaPuntos nuevaFila = new ListaPuntos(numFila);
        // Consulta si la lista esta vacia.
        if (this.headMatriz == null) {
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
        this.cant_Filas++;
    }

    /**
     * Inicializa la matriz con puntos a los que se les asigna integers ordenados de menor a mayor.
     * @param c_Filas cantidad de filas que desea que tenga la matriz.
     * @param c_Columnas cantidad de columnas que desea que tenga la matriz.
     * @return true si se inicializo con exito.
     */

    public boolean inicializaMatriz(int c_Filas, int c_Columnas){

        //valida que la matriz sea minimo de 2x2

        if (c_Filas >= 2 && c_Columnas > 2){

            this.cant_columnas = c_Columnas; //Modifica atributos de la clase matriz
            this.cant_Filas = c_Filas;
            int id = 0;

            agregarFilaAlFinal(id); //primeramente añade una fila, para poder tener acceso a la posicion de memoria del head
            ListaPuntos tmpH = headMatriz;

            //headMatriz = new ListaPuntos(0);
            //ListaPuntos tmpH = headMatriz;

            for(int i = 1; i <= c_Filas; i++){ //por cada fila

                for(int j=0; j<c_Columnas; j++){ //añade c_Columnas de puntos
                    tmpH.agregarAlFinal(id++);
                }

                if (i <= (c_Filas-1)){ // cuando se tiene la cantidad de filas desedas, deje de agregar filas
                    agregarFilaAlFinal(i);
                    tmpH = tmpH.getSiguiente();
                }

            }

            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Muestra en consola la matriz.
     */

    public void printMatriz(){

        ListaPuntos tmp = headMatriz;
        while(tmp!=null){

            Punto tmp2 = tmp.getHead();

            while (tmp2!=null){

                System.out.print(tmp2.getId() + " ");
                tmp2 = tmp2.getSiguiente();
            }
            System.out.println( " ");
            tmp = tmp.getSiguiente();
        }

    }

}
