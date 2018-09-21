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
        Marcador.setElemento(Actual);
        boolean Continuar=true;
        boolean Cerrado=false;
        LinkedList<Punto> Posibilidades=Marcador.getElemento().getReferencias();    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
        Posibilidades.eliminar(Anterior);
        while(Continuar){
            //Primera condicion de finalizacion, cerró la figura
            if(Marcador.getElemento()==Origen){ //Cambiar por origen
                Cerrado=true;
                Continuar=false;
            //Segunda condicion de finalizacion, no pudo cerrar
            } else if(Marcador.getElemento().getReferencias().getTamanio()==0){ //Eliminar anterior
                System.out.println("No cerró");
                Continuar=false;
                break;
            //Recorre los puntos según sus referencias    
            } else{
                System.out.println(Posibilidades.getUltimo().getElemento().getPosX()*10 + Posibilidades.getUltimo().getElemento().getPosY());
                while(Posibilidades.getTamanio()==1 && Marcador.getElemento()!=Origen){
                    Camino.anadirFinal(Marcador.getElemento());
                    Punto Eliminar = Marcador.getElemento();
                    Marcador.setElemento(Marcador.getElemento().getReferencias().getInicio().getElemento());
                    System.out.println(Marcador.getElemento().getPosX()*10 +Marcador.getElemento().getPosY());
                    Posibilidades=Marcador.getElemento().getReferencias();
                    Posibilidades.eliminar(Eliminar);
                }
                while(Posibilidades.getTamanio()>1){ //&& Marcador.getElemento()!=Origen){
                    Nodo<Punto> Marcador1 = new Nodo(BuscarMenorD(Marcador.getElemento(), Posibilidades));
                    //Marcador1.setElemento(BuscarMenorD(Marcador.getElemento(), Posibilidades));    //Busca de las bifurcaciones, cuál se acerca más al punto donde se cierra
                    if(BuscaCaminos(Origen, Marcador.getElemento(), Marcador1.getElemento())){    //Aplica recursividad para recorrer los subcaminos
                        System.out.println("1_Esta en"+ Marcador1.getElemento().getPosX() +Marcador1.getElemento().getPosY());                        
                        Camino.anadirFinal(Marcador1.getElemento());    //Funcion sumar listas
                        Cerrado=true;
                        Continuar=false;
                        return true;
                    } else{
                        System.out.println("2_Esta en"+ Marcador1.getElemento().getPosX() +Marcador1.getElemento().getPosY());
                        System.out.println("Posibilidad no cerró");
                        Posibilidades.eliminar(Marcador1.getElemento());
                    }
                }    
            }    
        }
        System.out.println("Cerrado" + "=" + Cerrado);        
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
         Nodo<Punto> Aux = Lista.getInicio();
         while(Aux.getSiguiente()!=null){
            if(Distancia(Origen, Menor.getElemento())>Distancia(Origen, (Punto) Aux.getSiguiente().getElemento())){
                Menor=Aux;
                Aux=Aux.getSiguiente();
            } else{
                Aux=Aux.getSiguiente();
            }
             
        }
        return Menor.getElemento();
    }
  
}