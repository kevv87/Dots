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
public class Perimetro {
    
    LinkedList<Punto> Puntos;

    Perimetro(LinkedList<Punto> Lista){
        LinkedList<Punto> Puntos=Lista;
    }
    
    public void UnirPerimetros(LinkedList<Perimetro> Perimetros, Perimetro Per){
        if(Perimetros.getTamanio()!=0){
            Nodo<Perimetro> PerAux = Perimetros.getInicio();
            Nodo<Punto> Punto = Per.getPuntos().getInicio();
            while(PerAux.getElemento()!=null){
                while(Punto.getElemento()!=null){
                    if(PerAux.getElemento().getPuntos().isIn(Punto.getElemento())){
                        Per.getPuntos().SumarListas(Per.getPuntos(), PerAux.getElemento().getPuntos());
                        Perimetros.eliminar(PerAux.getElemento());
                        PerAux=PerAux.getSiguiente();
                    } else{
                        Punto=Punto.getSiguiente();
                    }
                }
                PerAux=PerAux.getSiguiente();
                Punto=Per.getPuntos().getInicio();
            }
        }    
    }

    public LinkedList<Punto> getPuntos() {
        return Puntos;
    }

    public void setPuntos(LinkedList<Punto> Puntos) {
        this.Puntos = Puntos;
    }
    
    
}

