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

    ////[FACADE]/////[FUNCIONES PRINCIPALES]//////////////////////////////////////

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
                    tmpH.agregarAlFinal(id++,i-1,j);


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
     * Conecta dos puntos: agrega a la lista de conexiones de ambos puntos la respectiva relacion entre ellos.
     * @param id1 ID del primer punto.
     * @param id2 ID del segundo punto.
     */
    public void conectarSegmento(int id1, int id2){

        Punto punto1 = buscarPunto(id1);
        Punto punto2 = buscarPunto(id2);

        boolean conexionValida = validarConexionSegmento(punto1,punto2);

        if (conexionValida){

            punto1.getListaRelaciones().agregarAlInicio(punto2.getId());
            punto2.getListaRelaciones().agregarAlInicio(punto1.getId());
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

    //[FUNCIONES SECUNDARIAS/AUXILIARES]////////////////////////

    /**
     * Inicializa la matriz con puntos a los que se les asigna integers ordenados de menor a mayor.
     * @param numFila numero de la fila (según el eje 'y' de la matriz).
     */

    private void agregarFilaAlFinal(int numFila) {
        // Define una nueva Fila
        ListaPuntos nuevaFila = new ListaPuntos(numFila);
        // Consulta si la matriz esta vacia.
        if (this.headMatriz == null) {
            // Inicializa la matriz como inicio de la nueva fila.
            headMatriz = nuevaFila;
            // Caso contrario recorre la matriz hasta llegar a la ultima fila.
            // y agrega el nuevo.
        } else {
            // Referencia un puntero temporal al inicio de la matriz.
            ListaPuntos tmp = headMatriz;
            // Recorre la matriz hasta llegar a la ultima fila.
            while (tmp.getSiguiente() != null) {
                tmp = tmp.getSiguiente();
            }
            // Agrega la nueva fila al final de la matriz.
            tmp.setSiguiente(nuevaFila);
        }
        // Incrementa el contador de cantidad de filas de la matriz.
        this.cant_Filas++;
    }


    /**
     * Busca un punto en la matriz, dado el ID.
     * @param id ID del punto deseado.
     * @return Punto buscado.
     */

    private Punto buscarPunto(int id){

        ListaPuntos tmp = headMatriz;

        while (tmp != null){
            Punto tmp2 = headMatriz.getHead();

            while (tmp2!= null){
                if(tmp2.getId() == id){
                    return tmp2;
                }
                tmp2 = tmp2.getSiguiente();
            }

            tmp = tmp.getSiguiente();
        }
        return null;
    }

    /**
     * Valida el tamano del segmento entre dos puntos.
     * @param punto1 punto de referencia.
     * @param punto2 punto anexo.
     * @return true si la longitud es menor o igual a 1 tanto en 'X' como 'Y'.
     */
    private boolean validarLongitudSegmento(Punto punto1, Punto punto2){

        int distSegX = Math.abs(punto1.getN_columna() - punto2.getN_columna()); //distancia en eje X del segmento
        int distSegY = Math.abs(punto1.getN_fila() - punto2.getN_fila()); // distancia en eje Y del segmento

        if (distSegX <= 1 && distSegY <= 1){
            return true; //es un segmento aceptable;
        }

        else{
            return false; //la distancia del segmento es mayor a la permitida
        }
    }


    /**
     * Valida si el segmento no existe, y si no se encuentra dentro de un area
     * @param punto1 punto 1
     * @param punto2 punto 2
     * @return true si la conexion es posible
     */
    private boolean validarConexionSegmento(Punto punto1, Punto punto2){ // si el segmento ya existe o si se encuentra dentro de un area.

        //primero valida longitud del segmento
        boolean bool_longitud = validarLongitudSegmento(punto1, punto2);

        if (bool_longitud){

            boolean existeConexion = punto1.getListaRelaciones().buscar(punto2.getId());

            if (!existeConexion){  //valida si el segmento ya existe .

                // [AGREGAR] valida si se encuentra dentro de un area.
                return true;

            }
            else{
                return false;
            }
        }
        return false;
    }




}
