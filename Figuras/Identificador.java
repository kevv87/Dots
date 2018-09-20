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
public class Identificador{
    
    private LinkedList<Perimetro> Perimetros;
    private LinkedList<Punto> PuntosCreados;
    private LinkedList<Punto> PuntosCerrados;
    
    //Funcion que recibe los dos puntos unidos por los jugadores
    public void entrada (int ID1, int ID2){ //La interfaz envía los dos puntos unidos por el usuario y entrada los recibe
        
        Punto Punto1 = new Punto(ID1%8, (int)ID1/8);
        Punto Punto2 = new Punto(ID2%8, (int)ID2/8);

        if(PuntosCreados.buscar(Punto1)){
            //Busco algun perimetro donde el punto sea head o ultimo
            Nodo<Perimetro> PerimAux = Perimetros.getInicio();
            while(PerimAux.getSiguiente()!=null){
                if(PerimAux.getElemento().getHead()==Punto1){   //Head
                    PerimAux.getElemento().getPuntos().anadirInicio(Punto2);
                } else if(PerimAux.getElemento().getUltimo()==Punto1){  //Ultimo
                    PerimAux.getElemento().getPuntos().anadirFinal(Punto2);
                }
            }
            PuntosCreados.anadirFinal(Punto2);
        }
        else if(PuntosCreados.buscar(Punto2)){
            //Busco algun perimetro donde el punto sea head o ultimo
            Nodo<Perimetro> PerimAux = Perimetros.getInicio();
            while(PerimAux.getSiguiente()!=null){
                if(PerimAux.getElemento().getHead()==Punto2){   //Head
                    PerimAux.getElemento().getPuntos().anadirInicio(Punto1);
                } else if(PerimAux.getElemento().getUltimo()==Punto2){  //Ultimo
                    PerimAux.getElemento().getPuntos().anadirFinal(Punto1);
                }
            }
            PuntosCreados.anadirFinal(Punto1);
        }
        else{
            Perimetro nuevo = new Perimetro(Punto1, Punto2);
            Perimetros.anadirFinal(nuevo);
        }
        //ALGO DEBE HACERSE CON EL RETURN PUNTAJE DE ESTE METODO
        Nodo<Perimetro> PerimAux = Perimetros.getInicio();
        while(PerimAux.getSiguiente()!=null){
            cerrarPerimetro(PerimAux.getElemento());
            PerimAux=PerimAux.getSiguiente();
        }

    }
    //Funcion para añadir segmentos a la lista del perimetro comparando los puntos del mismo, 
    public LinkedList segmentosPorPts(DoublyLinkedList L, Perimetro Per){
        LinkedList<Segmento> aux_Segmentos = new LinkedList();
        NodoDoble current = L.getInicio();
        while(current.getSiguiente().getSiguiente()!=null){
            //Ordena los puntos, punto A es el que está más hacia la esquina superior izquierda
            Punto Punto_A=current.getElemento();
            Punto Punto_B=current.getElemento().getSiguiente();
            if (Punto_A.getPosY()==Punto_B.getPosY()){  //Se guarda como primer elemento el punto que esté más a la izquierda en caso de estar en misma fila
                if(Punto_B.getPosX()<Punto_A.getPosX()){
                    Punto temp = Punto_A;
                    Punto_A=Punto_B;
                    Punto_B=temp;
                }    
            }else if(Punto_B.getPosY()<Punto_A.getPosY()){
                Punto temp = Punto_A;
                Punto_A=Punto_B;
                Punto_B=temp;
            }
            //Recorrer lista de segmentos que tenga los puntos que busco
            Nodo<Segmento> SegmentoBusco = Per.getSegmentos().getInicio();
            while(Punto_A!=SegmentoBusco.getElemento().getPunto_A() && Punto_B!=SegmentoBusco.getElemento().getPunto_B()){
                SegmentoBusco=SegmentoBusco.getSiguiente();
            }
            aux_Segmentos.anadirFinal(SegmentoBusco.getElemento());
            Per.getSegmentos().eliminar(SegmentoBusco);
        }
        return aux_Segmentos;
    }
        
    
    public int segmentosSuma(Perimetro Per){
        int segmentos=0;
        int colineales=0;
        if(Per.getSegmentos().getInicio()==null){
            return segmentos;
        } else{
            Nodo<Segmento> aux = Per.getSegmentos().getInicio();
            while(aux.getElemento()!=null){
                if(aux.getElemento().isSuma()){
                    if(aux.getElemento().colineales(aux.getElemento(), (Segmento) aux.getSiguiente().getElemento())){   //es necesario el casting?
                        ++colineales;
                    }
                    ++segmentos;
                }
                aux=aux.getSiguiente();
            }
        }
        return (2*segmentos + colineales);
    }

    public int cerrarPerimetro(Perimetro Per){
        
        Perimetro aux_Perimetro = new Perimetro(null, null);    //Perimetro cerrado final con el que se trabaja
        DoublyLinkedList aux_Puntos = new DoublyLinkedList();   //Es doble porque si no, habría que hacer una iteracion para añadir, uno por uno, los Puntos en la lista Puntos
        LinkedList<Segmento> aux_Segmentos = new LinkedList();  
        Nodo<Perimetro> PerCerrado = new Nodo(); //Perimetro cerrado nuevo por añadir a la lista
        Nodo<Segmento> aux = new Nodo();
        //Cerrar por inicio==final
        if(Per.getUltimo().getSiguiente()==Per.getHead()){
            aux_Perimetro=Per;
            aux_Puntos=Per.getPuntos();
            aux_Segmentos=Per.getSegmentos();
        } else{
            //Cerrar por final.siguiente==elemento en el medio
            if(Per.getPuntos().buscarPunto(Per.getPuntos().getUltimo().getSiguiente().getElemento())){
                aux_Perimetro.setHead(Per.getPuntos().getUltimo().getSiguiente().getElemento());    //Asigna primer valor de la lista de Puntos
                aux_Perimetro.setPuntos(Per.getPuntos().recorrerAtras(aux_Perimetro.getHead(), Per));   //Se asigna lista de puntos al auxiliar y estos se borran del perimetro original abierto
                aux_Perimetro.setSegmentos(segmentosPorPts(aux_Perimetro.getPuntos(), Per)); //Se asigna lista de segmentos de acuerdo a los puntos que contiene y actualiza los segmentos del perimetro abierto
            }
            //Cerrar por inicio.anterior==elemento en el medio
            else if(Per.getPuntos().buscarPunto(Per.getPuntos().getInicio().getAnterior().getElemento())){
                aux_Perimetro.setHead(Per.getPuntos().getInicio().getAnterior().getElemento());
                aux_Perimetro.setPuntos(Per.getPuntos().recorrerAdelante(aux_Perimetro.getHead(), Per));
                aux_Perimetro.setSegmentos(segmentosPorPts(aux_Perimetro.getPuntos(), Per));
            }
            //Cerrar por inicio y final pertenecientes a un perimetro abierto o cerrado.
            else if(PuntosCerrados.buscar(Per.getPuntos().getInicio().getAnterior().getElemento()) &&PuntosCerrados.buscar(Per.getPuntos().getUltimo().getSiguiente().getElemento())){
                Nodo<Perimetro> nuevo = Perimetros.getInicio();
                boolean Encontrado = false;
                while(nuevo.getSiguiente()!=null && Encontrado!=false){
                    if(nuevo.getElemento().getPuntos().buscarPunto(Per.getPuntos().getInicio().getAnterior().getElemento()) && nuevo.getElemento().getPuntos().buscarPunto(Per.getPuntos().getInicio().getAnterior().getElemento())){
                        Encontrado=true;
                    }
                }
            }
            //Cerrar por composicion de perimetros
            else{                
                return 0;
            }
        }
        aux_Perimetro.setPuntaje(segmentosSuma(aux_Perimetro));
        aux_Perimetro.setCerrado(true);
        return aux_Perimetro.getPuntaje();
    }
    
    //GETTERS
    public LinkedList<Perimetro> getPerimetros() {
        return Perimetros;
    }

    public LinkedList<Punto> getPuntosCreados() {
        return PuntosCreados;
    }

    public LinkedList<Punto> getPuntosCerrados() {
        return PuntosCerrados;
    }
    //STTERS
    public void setPerimetros(LinkedList<Perimetro> Perimetros) {
        this.Perimetros = Perimetros;
    }

    public void setPuntosCreados(LinkedList<Punto> PuntosCreados) {
        this.PuntosCreados = PuntosCreados;
    }

    public void setPuntosCerrados(LinkedList<Punto> PuntosCerrados) {
        this.PuntosCerrados = PuntosCerrados;
    }
    
    
}    
    