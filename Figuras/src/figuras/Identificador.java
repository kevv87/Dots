/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

/**
 *
 * @author Sebastián
 */
public class Identificador{
    
    private LinkedList<Perimetro> Perimetros;
    private LinkedList<Segmento> Segmentos;
    private LinkedList<Punto> PuntosCerrados;
    
    //Funcion para añadir segmentos a la lista del perimetro comparando los puntos del mismo, 
    public LinkedList segmentosPorPts(DoublyLinkedList L, Perimetro Per){
        LinkedList<Segmento> aux_Segmentos = new LinkedList();
        NodoDoble current = L.getInicio();
        while(current.getElemento()!=)
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
                aux_Perimetro.setSegmentos(Per.getSegmentos().segmentosPorPts(aux_Perimetro.getPuntos()), Per); //Se asigna lista de segmentos de acuerdo a los puntos que contiene y actualiza los segmentos del perimetro abierto
            }
            //Cerrar por inicio.anterior==elemento en el medio
            else if(Per.getPuntos().buscarPunto(Per.getPuntos().getInicio().getAnterior().getElemento())){
                
            }
            //Cerrar por inicio y final pertenecientes a un mismo perimetro *ya sea abierto o cerrado*
            else if(PuntosCerrados.buscar(Per.getHead()) && PuntosCerrados.buscar(Per.getUltimo())){
                
            }
            else{
                return 0;
            }
        }
        aux_Perimetro.setPuntaje(segmentosSuma(aux_Perimetro));
        aux_Perimetro.setCerrado(true);
        return aux_Perimetro.getPuntaje();
    }
}    
    