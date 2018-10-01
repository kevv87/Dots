/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

/**
 * Clase encargada de determinar perimetros cerrados
 * @author Sebastián
 */
public class Recorrido {
    private final LinkedList<LinkedList> Matriz;
    private LinkedList<Perimetro> PerimetrosCerrados;
    private boolean inFigCerrada;
    
    /**
     * Constructor
     */
    public Recorrido(){
        Matriz = genMatriz();
        PerimetrosCerrados = new LinkedList();
        inFigCerrada=false;
    }
    
    /**
     * Retorna lista de puntos de un perimetro nuevo que acaba de cerrar
     * @param Origen Punto donde se comienza el recorrido
     * @param Anterior Conexion de donde viene el punto Actual
     * @param Actual Punto donde se sigue el recorrido
     * @return  LinkedList de los puntos que componen el perimetro nuevo
     */
    public LinkedList BuscaCaminos(Punto Origen, Punto Anterior, Punto Actual){  //Punto de origen, punto anterior, punto actual
        LinkedList<Punto> Camino = new LinkedList();
        Nodo<Punto> Marcador = new Nodo();
        Camino.anadirFinal(Origen);
        Marcador.setElemento(Actual);
        boolean Continuar=true;

        LinkedList<Punto> Posibilidades= new LinkedList<>();
        Posibilidades.SumarListas(Posibilidades, Marcador.getElemento().getReferencias());    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
        Posibilidades.eliminar(Anterior);   //Elimina el punto del que viene en caso de usar recursividad
        System.out.println(Posibilidades.getTamanio());
        
        while(Continuar){
            //      _________________________________________________
            //_____/Primer condicion de finalizacion, cerro la figura
            if(Marcador.getElemento()==Origen){ //Cambiar por origen
                Perimetro nuevo = new Perimetro(Camino);
                nuevo.getPuntos().setInicio(Camino.getInicio());
                nuevo.UnirPerimetros(PerimetrosCerrados, nuevo);
                PerimetrosCerrados.anadirFinal(nuevo);
                Continuar=false;
            //      _______________________________
            //_____/Segunda condicion, no cerro
            } else if(Posibilidades.getTamanio()==0){ //Eliminar anterior
                Camino=null;                
                Continuar=false;
            //      _______________________________
            //_____/Tercer condicion, debe recorrer
            } else{
                // A) Si solo existe una posibilidad, continue avanzando
                while(Posibilidades.getTamanio()==1 && Marcador.getElemento()!=Origen){
                    Camino.anadirFinal(Marcador.getElemento());
                    Anterior = Marcador.getElemento();
                    Marcador.setElemento(Posibilidades.getInicio().getElemento());

                    Posibilidades= new LinkedList<>();
                    Posibilidades.SumarListas(Posibilidades, Marcador.getElemento().getReferencias());;    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
                    Posibilidades.eliminar(Anterior);
                }
                // B) Si hay mas de una posibilidad, evalue los casos
                while(Posibilidades.getTamanio()>1 && Marcador.getElemento()!=Origen){
                    Perimetro PerActual = pertenecePer(Marcador.getElemento());
                    if(Camino.isIn(Marcador.getElemento())==false){
                        Camino.anadirFinal(Marcador.getElemento());
                    }
                    // B.1) Si se topa con una figura cerrada
                    if(PerActual!=null && inFigCerrada == false){
                        // B.1.1) Si el perimetro contiene al origen
                        if(PerActual.getPuntos().isIn(Origen)==true){
                            Perimetro nuevo = new Perimetro(Camino);
                            nuevo.UnirPerimetros(PerimetrosCerrados, nuevo);
                            PerimetrosCerrados.anadirFinal(nuevo);
                            Posibilidades.setTamanio(0);
                            Continuar=false;
                        }
                        // B.1.2) Si no contiene al origen, se sigue el recorrido a partir de los vertices de la figura
                        else{
                            System.out.println("No contiene origen, pero lo valida");
                            Posibilidades = new LinkedList<Punto>();
                            Posibilidades.SumarListas(Posibilidades, PerActual.getPuntos());
                            inFigCerrada=true;
                        }
                    }
                    // B.2) Si llega a una bifurcacion, pero no es de una figura cerrada
                    else if(pertenecePer(Marcador.getElemento())==null){
                        boolean Seguir = true;
                        
                        while(Posibilidades.getTamanio() > 0 && Seguir == true){
                            Nodo<Punto> Marcador1 = Posibilidades.getInicio();
                            System.out.println("Posibilidad 1");
                            LinkedList X = BuscaCaminos(Origen, Marcador.getElemento(), Marcador1.getElemento());
                            if(X!=null){
                                Seguir=false;
                                Camino.SumarListas(Camino, X);
                                return Camino;
                            } else{
                                Posibilidades.eliminar(Marcador1.getElemento());
                            }
                        }
                    }
                    
                    else{
                        if(inFigCerrada){
                            //PRIMER CASO (para elimiar caso en el que pueda retornarse por el segmento que conecta la figura cerrada con el segmento adjunto
                            boolean Seguir = true;
                            Nodo<Punto> Pos = new Nodo(Posibilidades.getInicio().getElemento());  //Busca de las bifurcaciones, cuál se acerca más al punto Origen
                             //!!!verificar distintos
                            Posibilidades.anadirInicio(Anterior); //Para eliminar conexion con segmento adjunto
                            LinkedList<Punto> Conexiones = new LinkedList<>();
                            Conexiones.SumarListas(Conexiones,Pos.getElemento().getReferencias());
                            Conexiones.RestarListas(Conexiones, Posibilidades);     //Para continuar, se deben eliminar las conexiones dentro del perímetro hecho
                            Posibilidades.eliminar(Anterior);//Vuelve al valor original de posibilidades

                            LinkedList<Punto> verticesPerimetro = new LinkedList<>();
                            verticesPerimetro.SumarListas(verticesPerimetro, Posibilidades);

                            if(Conexiones.getTamanio()==0){
                                Posibilidades.eliminar(Pos.getElemento());
                            }
                            else{
                                Nodo<Punto> Marcador1 = Conexiones.getInicio();
                                System.out.println("Posibilidad 2");                                
                                LinkedList X = BuscaCaminos(Origen, null, Marcador1.getElemento());
                                if(X!=null){
                                    Seguir=false;
                                    Camino.SumarListas(Camino, X);
                                    return Camino;

                                } else{
                                    Conexiones.eliminar(Marcador1.getElemento());
                                }
                            }//SE ITERA
                            while(Posibilidades.getTamanio()>0 && Seguir==true){
                                Pos = new Nodo(Posibilidades.getInicio().getElemento());  //Busca de las bifurcaciones, cuál se acerca más al punto Origen
                                Conexiones = new LinkedList<>();
                                Conexiones.SumarListas(Conexiones,Pos.getElemento().getReferencias());
                                Conexiones.RestarListas(Conexiones, verticesPerimetro);     //Para continuar, se deben eliminar las conexiones dentro del perímetro hecho
                                System.out.println("Conexiones tiene:");
                                ImpresionLista(Conexiones);
                                if(Conexiones.getTamanio()==0){
                                    Posibilidades.eliminar(Pos.getElemento());
                                }
                                else{
                                    
                                    Nodo<Punto> Marcador1 = Conexiones.getInicio();
                                    System.out.println("Posibilidad 3");
                                    System.out.println("Pos es " + Pos.getElemento().getPosX()+Pos.getElemento().getPosY());
                                    System.out.println("Marcador 1 es " + Marcador1.getElemento().getPosX()*10 +Marcador1.getElemento().getPosY());
                                    LinkedList X = BuscaCaminos(Origen, null, Marcador1.getElemento());
                                    if(X!=null){
                                        Seguir=false;
                                        Camino.SumarListas(Camino, X);
                                    return Camino;

                                    } else{
                                        Conexiones.eliminar(Marcador1.getElemento());
                                    }
                                }
                            }
                        }
                    }    
                }    
            }    
        }
        inFigCerrada=false;
        return Camino;
    }
    
    /**
     * Retorna la lista del camino de puntos si los últimos puntos ingresador cierran una figura
     * @param ID1 Numero en base octal cuya decena es la coordenada en X del punto y la unidad es la coordenada en Y del primer punto iingresado
     * @param ID2 Numero en base octal cuya decena es la coordenada en X del punto y la unidad es la coordenada en Y del segundo punto ingresado
     * @return LinkedList de los puntos con los que cerró la figura
     */
    public LinkedList<Punto> Entrada(int ID2, int ID1){      //Con base en los valores de su ID, busca el punto equivalente en la Matriz lógica

        Nodo nodoA = buscarPto(ID1%10, (int)ID1/10);
        Nodo nodoB= buscarPto(ID2%10, (int)ID2/10);
        Punto PuntoA = (Punto)nodoA.getElemento();
        Punto PuntoB = (Punto)nodoB.getElemento();
        ((Punto)nodoA.getElemento()).addReferencia(PuntoB);
        ((Punto)nodoB.getElemento()).addReferencia(PuntoA);
        
        LinkedList<Punto> Camino = BuscaCaminos(PuntoA, PuntoA, PuntoB);
        if(Camino!=null){
            LinkedList<Punto> Area = new LinkedList();
            if(Camino.getInicio().getElemento().getReferencias().isIn(Camino.getUltimo().getElemento())==false){
                Perimetro PerInicio = pertenecePer(Camino.getInicio().getElemento());
                Perimetro PerUltimo = pertenecePer(Camino.getUltimo().getElemento());
                if(PerInicio == PerUltimo){
                    Area=PerInicio.obtenerSeccion(PerInicio, PuntoA, PuntoB);
                }
            }
            System.out.println("YUJU!");
            LinkedList<Punto> Total = Camino;
            Total.SumarListas(Total, Area);
            ImpresionLista(Total);
            return Camino;
        } else{
            System.out.println("No se cerró");
            return null;
        }
    }
    
    /**
     * Retorna el valor del puntaje segun una lista de puntos
     * @param Camino Lista de puntos que corresponden a los que el jugador ganó
     * @return Puntaje obtenido del nuevo perimetro
     */
    public int calcularPuntaje(LinkedList<Punto> Camino){
        int Puntaje = 0;
        if(Camino!=null){
            LinkedList<Punto> Puntos = new LinkedList();
            Puntos.SumarListas(Puntos, Camino);
            Nodo<Punto> Punto1 = Puntos.getInicio();
            Nodo<Punto> Punto2 = Punto1.getSiguiente();
            while(Punto2!=null){
                if(isColineales(Punto1.getElemento(), Punto2.getElemento())==false){
                    Puntaje++;
                }
                Puntaje+=2;
                Punto1=Punto1.getSiguiente();
                Punto2=Punto2.getSiguiente();
            }
            Puntaje+=2;
        }
        return Puntaje;
    }
    
    /**
     * Retorna true si dos puntos son colineales 
     * @param PuntoA Primer punto que se une con el segundo
     * @param PuntoB Segundo punto que se une con el primero
     * @return true si los dos puntos comparten uno de los ejes
     */    
    public boolean isColineales(Punto PuntoA, Punto PuntoB){
        if(PuntoA.getPosX()!=PuntoB.getPosX() && PuntoA.getPosY()!=PuntoB.getPosY()){
            return false;
        } else{
            return true;
        }
    }
    
    //Funcion para imprimir puntos, debe borrarse
    public void ImpresionLista(LinkedList List){
        if(List!=null){
            Nodo<Punto> Aux = List.getInicio();
            System.out.println("El camino es:");
            while(Aux!=null){
                System.out.println(Aux.getElemento().getPosX()*10 + Aux.getElemento().getPosY());
                Aux=Aux.getSiguiente();
            }
        }
    }
    
    /**
     * Retorna Matriz de juego
     * @return LinkedList de LinkedLists que corresponden a la implementación de matriz
     */        
    public LinkedList<LinkedList> genMatriz(){
        LinkedList<LinkedList> Matriz = new LinkedList();
        int columnas = 0;
        int filas = 0;
        while(columnas<8){
            LinkedList<Punto> Columna = new LinkedList();
            while(filas<8){
                Punto punto = new Punto(columnas, filas);
                Columna.anadirFinal(punto);
                filas++;
            }
            Matriz.anadirFinal(Columna);
            columnas++;
            filas=0;
        }
        return Matriz;
    }
    
    /**
     * Retorna el Nodo que contiene el punto que se busca
     * @param X Columna a la que pertenece el punto
     * @param Y Fila a la que pertenece el punto
     * @return Nodo que contiene al punto
     */    
    public Nodo<Punto> buscarPto(int X, int Y){
        int col = 0;
        Nodo<LinkedList> Columna = Matriz.getInicio();
        while(col!=X){
            Columna=Columna.getSiguiente();
            col++;
        }
        Nodo<Punto> punto = Columna.getElemento().getInicio();
        int fil = 0;
        while(fil!=Y){
            punto=punto.getSiguiente();
            fil++;
        }
        return punto;
    }
  
    /**
     * Retorna Perimetro que contiene el punto actual
     * @param pto Punto que se busca dentro de algún perímetro
     * @return Perimetro que contiene el punto buscado
     */        
    public Perimetro pertenecePer(Punto pto){
        if(PerimetrosCerrados.getInicio()==null){
            return null;
        } else{
            Nodo<Perimetro> Marcador = PerimetrosCerrados.getInicio();
            boolean Encontrado = false;
            Perimetro Resultado = null;
            while(Marcador!=null && Marcador.getElemento()!=null && Encontrado==false){
                if(Marcador.getElemento().getPuntos()!= null && Marcador.getElemento().getPuntos().isIn(pto)){
                    Resultado=Marcador.getElemento();
                    Encontrado=true;
                }
                else{
                    Marcador=Marcador.getSiguiente();
                }
            }
            return Resultado;
        }
    }

    //GETTERS
    public LinkedList<Perimetro> getPerimetrosCerrados() {
        return PerimetrosCerrados;
    }

    //SETTERS
    public void setPerimetrosCerrados(LinkedList<Perimetro> PerimetrosCerrados) {
        this.PerimetrosCerrados = PerimetrosCerrados;
    }

    public boolean isInFigCerrada() {
        return inFigCerrada;
    }

    public void setInFigCerrada(boolean inFigCerrada) {
        this.inFigCerrada = inFigCerrada;
    }
}
