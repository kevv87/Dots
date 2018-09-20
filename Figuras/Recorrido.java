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
    
    public boolean BuscaCaminos(Punto Origen, Punto Anterior, Punto Actual){  //Punto de origen, punto anterior, punto actual
        LinkedList<Punto> Camino = new LinkedList();
        Nodo<Punto> Marcador = new Nodo(Origen);
        Camino.setInicio(Marcador);
        Camino.anadirFinal(Actual);
        Marcador.setElemento(Actual);
        boolean Continuar=true;
        boolean Cerrado=false;
        LinkedList<Punto> Posibilidades=Marcador.getElemento().getReferencias();    //Lista de posibles bifurcaciones al camino
        Posibilidades.eliminar(Anterior);
        while(Continuar){
            //Primera condicion de finalizacion, cerró la figura
            if(Marcador.getElemento()==Origen){ //Cambiar por origen
                System.out.println(Camino);
                Cerrado=true;
                Continuar=false;
            //Segunda condicion de finalizacion, no pudo cerrar
            } else if(Marcador.getElemento().getReferencias().getTamanio()==0){ //Eliminar anterior
                System.out.println("No cerró");
                Continuar=false;
            //Recorre los puntos según sus referencias    
            } else{
                while(Posibilidades.getTamanio()==1 && Posibilidades.getInicio().getElemento()!=Origen){
                    Camino.anadirFinal(Marcador.getElemento());
                    Marcador.setElemento(Marcador.getElemento().getReferencias().getInicio().getElemento());
                    Posibilidades=Marcador.getElemento().getReferencias();
                }
                while(Posibilidades.getTamanio()>1 && Marcador.getElemento()!=Origen){
                    Nodo<Punto> Marcador1 = new Nodo(null);
                    Marcador1.setElemento(BuscarMenorD(Marcador.getElemento(), Posibilidades));    //Busca de las bifurcaciones, cuál se acerca más al punto donde se cierra
                    if(BuscaCaminos(Origen, Marcador.getElemento(), Marcador1.getElemento())){    //Aplica recursividad para recorrer los subcaminos
                        Camino.anadirFinal(Marcador1.getElemento());    //Funcion sumar listas
                        System.out.println(Camino);
                        Cerrado=true;
                        Continuar=false;
                        return true;
                    }
                }    
            }    
        }
        return Cerrado;
    }
    //Funcion que recibe dos puntos del usuario
    public void Entrada(int ID1, int ID2){
        Punto PuntoA = new Punto(ID1%8, (int)ID1/8);
        Punto PuntoB = new Punto(ID2%8, (int)ID2/8);
        
        if(BuscaCaminos(PuntoA, null, PuntoB)){
            System.out.println("Yuju!");
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
         while(Menor.getSiguiente()!=null){
            if(Distancia(Origen, Menor.getElemento())>Distancia(Origen, (Punto) Menor.getSiguiente().getElemento())){
                Menor=Menor.getSiguiente();
            }
             
        }
        return Menor.getElemento();
    }
  
}