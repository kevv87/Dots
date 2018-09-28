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
public class Recorrido {
    
    //private LinkedList<Perimetro> PerimetrosCerrados = new LinkedList(); 
    //private LinkedList<Segmento> SegmentosHechos = new LinkedList();
    
    //Funcion que retorna la lista de puntos para cerrar el camino
    public LinkedList BuscaCaminos(Punto Origen, Punto Anterior, Punto Actual){  //Punto de origen, punto anterior, punto actual
        LinkedList<Punto> Camino = new LinkedList();
        Nodo<Punto> Marcador = new Nodo(Origen);
        Camino.anadirFinal(Origen);
        Marcador.setElemento(Actual);

        //reduce tamaño
        Marcador.getElemento().getReferencias().eliminar(Origen);

        boolean Continuar=true;

        LinkedList<Punto> Posibilidades=Marcador.getElemento().getReferencias();    //Lista de posibles bifurcaciones al camino en el punto en el que estoy
        Posibilidades.eliminar(Anterior);   //Elimina el punto del que viene en caso de usar recursividad
        while(Continuar){

            //Primera condicion de finalizacion, cerró la figura

            if(Marcador.getElemento()==Origen){ //Cambiar por origen

                //Perimetro nuevo = new Perimetro(Camino);
                //PerimetrosCerrados.anadirFinal(nuevo);
                Continuar=false;

            //Segunda condicion de finalizacion, no pudo cerrar
            } else if(Marcador.getElemento().getReferencias().getTamanio()==0){ //**Eliminar anterior
                System.out.println("No cerró");
                Camino=null;                
                Continuar=false;

            //Recorre los puntos según sus referencias    
            } else{

                while(Posibilidades.getTamanio()==1 && Marcador.getElemento()!=Origen){
                    Camino.anadirFinal(Marcador.getElemento());
                    Punto Eliminar = Marcador.getElemento();
                    Marcador.setElemento(Marcador.getElemento().getReferencias().getInicio().getElemento());
                    Posibilidades=Marcador.getElemento().getReferencias();
                    Posibilidades.eliminar(Eliminar);
                }
                while(Posibilidades.getTamanio()>1){ //&& Marcador.getElemento()!=Origen){
                    if(Camino.buscar(Marcador.getElemento())==false){
                        Camino.anadirFinal(Marcador.getElemento());
                    }
                    Nodo<Punto> Marcador1 = new Nodo(BuscarMenorD(Marcador.getElemento(), Posibilidades));  //Busca de las bifurcaciones, cuál se acerca más al punto donde se cierra
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
        return Camino;
    }
    
    //Funcion que recibe dos puntos del usuario
    public void Entrada(int ID1, int ID2){
        Punto PuntoA = new Punto(ID1%8, (int)ID1/8);
        Punto PuntoB = new Punto(ID2%8, (int)ID2/8);
        if(BuscaCaminos(PuntoA, PuntoA, PuntoB)!=null){
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
         Nodo<Punto> Aux = Lista.getInicio().getSiguiente();
         while(Aux.getSiguiente()!=null){
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
  
}