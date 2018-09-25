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
    public void eliminar(Perimetro Per, NodoDoble N){
        NodoDoble aux = Per.getPuntos().getInicio();
        while(aux!=null){
            if(aux.getSiguiente()==N){
                NodoDoble temp=aux.getSiguiente().getSiguiente();
                aux.setSiguiente(temp);
                temp.setAnterior(null);
            } else{
                aux=aux.getSiguiente();
            }
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
    //_________________________________________
    //Las dos listas nuevas van a tener como inicio el punto que buscan y como final el punto que buscan
    //esta funcion recorre a partir del Head hasta el punto donde cerró la figura y retorna una lista que corresponde a los puntos del nuevo perimetro cerrado, quita la figura formada del resto del perimetro
    public DoublyLinkedList recorrerAdelante(Punto dato, Perimetro Per){
        DoublyLinkedList NuevaLista = new DoublyLinkedList();
        NuevaLista.anadirInicio(dato);
        NodoDoble auxiliar = Per.getPuntos().getInicio();
        while(auxiliar.getSiguiente().getElemento()!=dato){
            NuevaLista.anadirFinal(auxiliar.getElemento());
            Per.getPuntos().eliminar(Per, auxiliar);
            auxiliar=auxiliar.getSiguiente();
        }
        NuevaLista.anadirFinal(dato);
        return NuevaLista;
    }
    //funcion recorre a partir de ultimo elemento hasta llegar al punto donde cierra, borra del perimetro original el nodo que es del perimetro cerrado nuevo y retorna la lista de los Puntos del area cerrada
    public DoublyLinkedList recorrerAtras(Punto dato, Perimetro Per){
        DoublyLinkedList NuevaLista = new DoublyLinkedList();
        NuevaLista.anadirInicio(dato);
        NodoDoble auxiliar = Per.getPuntos().getUltimo();
        while(auxiliar.getElemento()!=dato){
            NuevaLista.anadirInicio(auxiliar.getElemento());
            Per.getPuntos().eliminar(Per, auxiliar);
            auxiliar=auxiliar.getAnterior();
        }
        NuevaLista.anadirInicio(dato);
        return NuevaLista;
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
