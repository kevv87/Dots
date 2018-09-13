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
public class Main {
    
    private LinkedList PerimetrosCerrados;
    private LinkedList SegmentosCerrados;
    
    public int segmentosSuma(Perimetro Per){
        int segmentos=0;
        int colineales=0;
        if(Per.getSegmentos().getInicio()==null){
            return segmentos;
        } else{
            Nodo<Segmento> aux = new Nodo();
            aux=Per.getSegmentos().getInicio();
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
        return 2*segmentos + colineales;
    }

    public int cerrarPerimetro(Perimetro Per){
        Nodo<Perimetro> nuevo = new Nodo();
        Nodo<Segmento> aux = new Nodo();
        //Cerrar por inicio==final
        if(Per.getUltimo().getSiguiente()==Per.getHead()){
            nuevo.setElemento(Per);
        }
        //Cerrar por final.siguiente==elemento en el medio
        else if(Per.getPuntos().buscarPunto(Per.getPuntos().getUltimo().getSiguiente().getElemento())){
        }
        //Cerrar por inicio.anterior==elemento en el medio
        else if(Per.getPuntos().buscarPunto(Per.getPuntos().getInicio().getAnterior().getElemento())){
            
        }
        //Cerrar por inicio y final pertenecientes a un mismo perimetro ya cerrado
        Per.setPuntaje(segmentosSuma(Per));
        PerimetrosCerrados.anadirFinal(nuevo);
        return Per.getPuntaje();
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                    
        }
}
