/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

/**
 *
 * @author Sebastián
 */
public class Recorrido {
    
    private LinkedList<LinkedList> Matriz;
    private LinkedList<Perimetro> PerimetrosCerrados;
    private int Puntaje;
    private boolean inFigCerrada;
    
    public Recorrido(){
        Matriz = genMatriz();
        PerimetrosCerrados = new LinkedList();
        inFigCerrada=false;
    }
    //Funcion que retorna la lista de puntos para cerrar el camino
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
                        } else{
                            Nodo<Punto> Marcador1 = new Nodo(BuscarMenorD(Origen, Posibilidades));  //Busca de las bifurcaciones, cuál se acerca más al punto Origen
                            System.out.println("Posibilidad 4");
                            LinkedList X=BuscaCaminos(Origen, Marcador.getElemento(), Marcador1.getElemento());
                            if(X!=null){    //Aplica recursividad para recorrer los subcaminos
                                Camino.SumarListas(Camino, X);    //Funcion sumar listas
                                Continuar=false;
                            } else{
                                Posibilidades.eliminar(Marcador1.getElemento());
                            }
                        }
                    }    
                }    
            }    
        }
        inFigCerrada=false;
        return Camino;
    }
    
    //Funcion que recibe dos puntos del usuario
    public LinkedList<Punto> Entrada(int ID2, int ID1){      //Con base en los valores de su ID, busca el punto equivalente en la Matriz lógica

        Nodo nodoA = buscarPto(ID1%10, (int)ID1/10);
        Nodo nodoB= buscarPto(ID2%10, (int)ID2/10);
        Punto PuntoA = (Punto)nodoA.getElemento();
        Punto PuntoB = (Punto)nodoB.getElemento();
        ((Punto)nodoA.getElemento()).addReferencia(PuntoB);
        ((Punto)nodoB.getElemento()).addReferencia(PuntoA);
        
        LinkedList<Punto> Camino = BuscaCaminos(PuntoA, PuntoA, PuntoB);
        if(Camino!=null){
            System.out.println("YUJU!");
            ImpresionLista(BuscaCaminos(PuntoA, PuntoA, PuntoB));
            return Camino;
        } else{
            System.out.println("No se cerró");
            return null;
        }
    }
    
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
        }
        return Puntaje;
    }
    
    public boolean isColineales(Punto PuntoA, Punto PuntoB){
        if(PuntoA.getPosX()!=PuntoB.getPosX() && PuntoA.getPosY()!=PuntoB.getPosY()){
            return true;
        } else{
            return false;
        }
    }
    //Funcion que determina la distancia entre dos puntos
    public double Distancia(Punto PuntoA, Punto PuntoB){    //Punto A es el origen al que se debe volver, Punto B es el punto comparado
        double D=Math.sqrt(Math.pow((PuntoA.getPosX()-PuntoB.getPosX()),2)+Math.pow((PuntoA.getPosY()-PuntoB.getPosY()),2));
        return D;
    }
    
    //Funcion que determina de una lista, cual de los puntos se acerca más al que busco.x 
    public Punto BuscarMenorD(Punto Origen, LinkedList<Punto> Lista){
         Nodo<Punto> Menor = Lista.getInicio();
         Nodo<Punto> Aux = Lista.getInicio().getSiguiente();
         while(Aux!=null){
            if(Distancia(Origen, Menor.getElemento())>Distancia(Origen, (Punto) Aux.getElemento())){
                Menor=Aux;
                Aux=Aux.getSiguiente();
            } else{
                Aux=Aux.getSiguiente();
            }
        }
        return Menor.getElemento();
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
    
    //Funcion para generar matriz de puntos
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
    
    //Funcion que busca un punto en la matriz
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
  
    //Funcion que busca si en la lista de perimetros, alguno contiene un punto específico
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

    public LinkedList<Perimetro> getPerimetrosCerrados() {
        return PerimetrosCerrados;
    }

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
