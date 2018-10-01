/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

/**
 * Clase encargada de crear un perimetro correspondiente a una figura cerrada
 * @author Sebastián
 */
public class Perimetro {
    
    LinkedList<Punto> Puntos;
    
    /**
     * Constructor
     * @param Lista Lista de puntos que componen al perimetro
     */
    public Perimetro(LinkedList<Punto> Lista){
        Puntos=Lista;
    }
    
    /**
     * Une en un solo perimetro todos los que compartan puntos
     * @param Perimetros Lista de perimetros cerrados
     * @param Per Nuevo perimetro formado
     */
    public void UnirPerimetros(LinkedList<Perimetro> Perimetros, Perimetro Per){
        if(Perimetros.getInicio()!=null){
            Nodo<Perimetro> PerAux = Perimetros.getInicio();
            Nodo<Punto> Punto = Per.getPuntos().getInicio();
            boolean CambioPto = false;
            while(PerAux!=null){
                while(Punto!=null && CambioPto==false){
                    if(PerAux.getElemento().getPuntos().isIn(Punto.getElemento())){
                        Per.getPuntos().SumarListas(Per.getPuntos(), PerAux.getElemento().getPuntos());
                        Perimetros.eliminar(PerAux.getElemento());
                        CambioPto=true;
                    } else{
                        Punto=Punto.getSiguiente();
                    }
                }
                PerAux=PerAux.getSiguiente();
                Punto=Per.getPuntos().getInicio();
            }
        }    
    }
    
    /**
     * Retorna una lista de puntos del perimetro
     * @param Per Perimetro al cual pertenecen PuntoA y PuntoB
     * @param PuntoA Punto en la lista de puntos de Per
     * @param PuntoB Punto en la lista de puntos de Per
     * @return LinkedList de los puntos secuenciales entre PuntoA y PuntoB
     */    
    public LinkedList<Punto> obtenerSeccion(Perimetro Per, Punto PuntoA, Punto PuntoB){
        LinkedList<Punto> Camino = new LinkedList();
        Punto Inicio = new Punto(0,0);
        Punto Fin = new Punto(0,0);
        Nodo<Punto> Marcador1 = Per.getPuntos().getInicio();
        while(Marcador1.getElemento()!=PuntoA || Marcador1.getElemento()!=PuntoB){
            Marcador1=Marcador1.getSiguiente();
        }
        if (Marcador1.getElemento()==PuntoA){
            Inicio = PuntoA;
            Fin = PuntoB;
        } else{
            Inicio = PuntoB;
            Fin = PuntoA;
        }    
        Nodo<Punto> Marcador2 = Marcador1.getSiguiente();
        while(Marcador2.getElemento()!=Fin){
            Camino.anadirFinal(Marcador2.getElemento());
            Marcador2=Marcador2.getSiguiente();
        }
        return Camino;
    }
    
    //GETTER
    public LinkedList<Punto> getPuntos() {
        return Puntos;
    }
    
    //SETTER
    public void setPuntos(LinkedList<Punto> Puntos) {
        this.Puntos = Puntos;
    }    
    
}

