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
    
    private final LinkedList<Punto> Matriz = genMatriz();
    private LinkedList<Perimetro> PerimetrosCerrados;
    private LinkedList<Segmento> SegmentosHechos;
    private boolean inFigCerrada;
    
    public Recorrido(){
        PerimetrosCerrados = new LinkedList();
        SegmentosHechos = new LinkedList();
        inFigCerrada=false;
    }
    //Funcion que retorna la lista de puntos para cerrar el camino
    public LinkedList BuscaCaminos(Punto Origen, Punto Anterior, Punto Actual){  //Punto de origen, punto anterior, punto actual
        LinkedList<Punto> Camino = new LinkedList();
        Nodo<Punto> Marcador = new Nodo(Origen);
        Camino.anadirFinal(Origen);
        Marcador.setElemento(Actual);
        boolean Continuar=true;

        LinkedList<Punto> Posibilidades= new LinkedList<>();
        Posibilidades.SumarListas(Posibilidades, Marcador.getElemento().getReferencias());    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
        Posibilidades.eliminar(Anterior);   //Elimina el punto del que viene en caso de usar recursividad

        while(Continuar){
            //Primera condicion de finalizacion, cerró la figura
            if(Marcador.getElemento()==Origen){ //Cambiar por origen
                Perimetro nuevo = new Perimetro(Camino);
                nuevo.getPuntos().setInicio(Camino.getInicio());
                nuevo.UnirPerimetros(PerimetrosCerrados, nuevo);
                PerimetrosCerrados.anadirFinal(nuevo);
                Continuar=false;
            //Segunda condicion de finalizacion, no pudo cerrar
            } else if(Posibilidades.getTamanio()==0){ //Eliminar anterior
                Camino=null;                
                Continuar=false;
            //Recorre los puntos según sus referencias    
            } else{
                while(Posibilidades.getTamanio()==1 && Marcador.getElemento()!=Origen){     //1- Si solo hay una posibilidad, siga avanzando
                    Camino.anadirFinal(Marcador.getElemento());
                    Punto Eliminar = Marcador.getElemento();
                    Anterior = Marcador.getElemento();
                    Marcador.setElemento(Posibilidades.getInicio().getElemento());

                    Posibilidades= new LinkedList<>();
                    Posibilidades.SumarListas(Posibilidades, Marcador.getElemento().getReferencias());;    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
                    Posibilidades.eliminar(Eliminar);

                }
                while(Posibilidades.getTamanio()>1 && Marcador.getElemento()!=Origen){      //Si hay más de una posibilidad, evalúe
                    if(Camino.isIn(Marcador.getElemento())==false){
                        Camino.anadirFinal(Marcador.getElemento());
                    }
                    if(pertenecePer(Marcador.getElemento())!=null && inFigCerrada == false){     //Validacion de si se topa con una figura cerrada
                        if(pertenecePer(Origen)!=null){     //Si contiene al origen, se cerró
                            //Camino.anadirFinal(Marcador.getElemento());
                            Perimetro nuevo = new Perimetro(Camino);
                            nuevo.UnirPerimetros(PerimetrosCerrados, nuevo);
                            PerimetrosCerrados.anadirFinal(nuevo);
                            Posibilidades.setTamanio(0);
                            Continuar=false;
                        }
                        else{       //Si no contiene al origen, se parte de los vértices de la figura
                            System.out.println("No contiene origen, pero lo valida");
                            Perimetro Per_Aux = pertenecePer(Marcador.getElemento());
                            Posibilidades = new LinkedList<>();
                            Posibilidades.SumarListas(Posibilidades, Per_Aux.getPuntos());
                            inFigCerrada=true;
                        }
                    }

                    else if(pertenecePer(Marcador.getElemento())==null && inFigCerrada == false){ //si esta ante una bifurcacion, que no corresponde a una figura cerrada

                        boolean Seguir = true;

                        while(Posibilidades.getTamanio() > 0 && Seguir == true){

                            Nodo<Punto> Marcador1 = Posibilidades.getInicio();
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
                            Nodo<Punto> Pos = new Nodo(Marcador.getElemento());  //Busca de las bifurcaciones, cuál se acerca más al punto Origen
                             //!!!verificar distintos
                            Posibilidades.anadirInicio(Anterior); //Para eliminar conexion con segmento adjunto
                            LinkedList<Punto> Conexiones = new LinkedList<>();
                            Conexiones.SumarListas(Conexiones,Pos.getElemento().getReferencias());
                            Conexiones.RestarListas(Conexiones, Posibilidades);     //Para continuar, se deben eliminar las conexiones dentro del perímetro hecho
                            Posibilidades.eliminar(Anterior);//Vuelve al valor original de posibilidades

                            LinkedList<Punto> verticesPerimetro = new LinkedList<>();
                            verticesPerimetro.SumarListas(verticesPerimetro,Posibilidades);

                            if(Conexiones.getTamanio()==0){
                                Posibilidades.eliminar(Pos.getElemento());
                            }
                            else{
                                Nodo<Punto> Marcador1 = Conexiones.getInicio();
                                LinkedList X = BuscaCaminos(Origen, Pos.getElemento(), Marcador1.getElemento());
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

                                if(Conexiones.getTamanio()==0){
                                    Posibilidades.eliminar(Pos.getElemento());
                                }
                                else{
                                    Nodo<Punto> Marcador1 = Conexiones.getInicio();
                                    LinkedList X = BuscaCaminos(Origen, Pos.getElemento(), Marcador1.getElemento());
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
    public void Entrada(int ID1, int ID2){      //Con base en los valores de su ID, busca el punto equivalente en la Matriz lógica

        Nodo nodoA = buscarPto(ID1%10, (int)ID1/10);
        Nodo nodoB= buscarPto(ID2%10, (int)ID2/10);
        Punto PuntoA = (Punto)nodoA.getElemento();
        Punto PuntoB = (Punto)nodoB.getElemento();
        ((Punto)nodoA.getElemento()).addReferencia(PuntoB);
        ((Punto)nodoB.getElemento()).addReferencia(PuntoA);

        if(BuscaCaminos(PuntoA, PuntoA, PuntoB)!=null){
            System.out.println("YUJU!");
            ImpresionLista(BuscaCaminos(PuntoA, PuntoA, PuntoB));
        } else{
            System.out.println("No se cerró");
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
    public LinkedList<Punto> genMatriz(){
        LinkedList<Punto> Matriz = new LinkedList();
        for(int Filas=0; Filas<8; Filas++){
            for(int Columnas=0; Columnas<8; Columnas++){
                Punto punto = new Punto(Filas, Columnas);
                Matriz.anadirFinal(punto);
            }
        }
        return Matriz;
    }
    
    //Funcion que busca un punto en la matriz
    public Nodo buscarPto(int X, int Y){
        Nodo<Punto> Aux = Matriz.getInicio();
        while(!(Aux.getElemento().getPosX()==X && Aux.getElemento().getPosY()==Y)){
            Aux=Aux.getSiguiente();
        }
        return Aux;
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

    public LinkedList<Segmento> getSegmentosHechos() {
        return SegmentosHechos;
    }

    public void setSegmentosHechos(LinkedList<Segmento> SegmentosHechos) {
        this.SegmentosHechos = SegmentosHechos;
    }

    public boolean isInFigCerrada() {
        return inFigCerrada;
    }

    public void setInFigCerrada(boolean inFigCerrada) {
        this.inFigCerrada = inFigCerrada;
    }
    
}