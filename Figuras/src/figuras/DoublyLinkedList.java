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
public class DoublyLinkedList {
    
    private NodoDoble Inicio;
    private NodoDoble Ultimo;
    
    public DoublyLinkedList(){
        Inicio=null;
        Ultimo=null;
    }
    
    public void anadirInicio(Punto dato){
        NodoDoble nuevo = new NodoDoble(dato);
        if(Inicio==null){
            Inicio=nuevo;
            Ultimo=nuevo;
        } else{
            nuevo.setSiguiente(Inicio);
            Inicio.setAnterior(nuevo);
            Inicio=nuevo;
        }
    }
    
    public void anadirFinal(Punto dato){
        NodoDoble nuevo = new NodoDoble(dato);
        if(Inicio==null){
            Inicio=nuevo;
            Ultimo=nuevo;
        } else{
            Ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(Ultimo);
            Ultimo=nuevo;
        }
    }
    
    public boolean buscarPunto(Punto punto){
        boolean dentro=false;
        if(Inicio==null){
            return dentro;
        } else{
            NodoDoble current = Inicio;
            while(current.getSiguiente()!=null && dentro==false){
                if(current.getElemento()==punto){
                    dentro=true;
                } else{
                    current=current.getSiguiente();
                }
            }
            return dentro;
        }
    }
    
    public LinkedList recorrerAdelante(Punto dato, Perimetro Per){
        LinkedList<Punto> NuevaLista = new LinkedList();
        NuevaLista.anadirInicio(dato);
        NodoDoble auxiliar = Per.getPuntos().getInicio();
        while(auxiliar.getSiguiente().getElemento()!=dato){
            NuevaLista.anadirFinal(dato);
            Per.getSegmentos().eliminarNodo(auxiliar);
            auxiliar=auxiliar.getSiguiente();
        }
    }

    public NodoDoble getInicio() {
        return Inicio;
    }

    public void setInicio(NodoDoble Inicio) {
        this.Inicio = Inicio;
    }

    public NodoDoble getUltimo() {
        return Ultimo;
    }

    public void setUltimo(NodoDoble Ultimo) {
        this.Ultimo = Ultimo;
    }
    
    
   
}
