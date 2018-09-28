/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

/**
 *
 * @author 
 */
public class LinkedList<T>{ //Lista para nodos genericos
    
    private Nodo<T> Inicio;
    private Nodo<T> Ultimo;
    private int Tamanio;
    
    public LinkedList(){    
        Inicio=null;
        Ultimo=null;
        Tamanio=0;
    }
    
    public void anadirInicio(T dato){
        Nodo<T> nuevo = new Nodo<T>(dato);
        if(Inicio == null)
            this.Inicio = nuevo;
        else{
            nuevo.setSiguiente(Inicio);
            Inicio= nuevo;
        }
        Tamanio++;
    }
    
    public void anadirFinal(T dato){
        Nodo<T> nuevo = new Nodo<T>(dato);
        if(Inicio==null){
            Inicio = nuevo;
        }else{
            Nodo<T> current = Inicio;
            while(current.getSiguiente()!=null)
                current=current.getSiguiente();
                          
            current.setSiguiente(nuevo);
        }
        Ultimo=nuevo;
        Tamanio++;
    }
    public boolean isIn(T dato){
        boolean dentro=false;
        if(Inicio==null){
            return dentro;
        } else{
            Nodo<T> current = new Nodo();
            current=Inicio;
            while(current!=null && dentro==false){
                if(current.getElemento()!=dato){
                    current=current.getSiguiente();
                } else{
                    dentro=true;
                }
            }
            return dentro;
        }
    }

    
    public void eliminar(T dato){
        if(isIn(dato)){
            Nodo<T> aux = Inicio;
            if(aux.getElemento()==dato){
                Inicio=Inicio.getSiguiente();
            }
            else if(Ultimo.getElemento()==dato){
                while(aux.getSiguiente()!=Ultimo){
                    aux=aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
            else{
                while(aux.getSiguiente().getElemento()!=dato){
                    aux=aux.getSiguiente();
                }
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
            }
        }
        Tamanio--;
    }
    public void SumarListas(LinkedList Lista1, LinkedList Lista2){
        Nodo<Punto> Aux = Lista2.getInicio();
        while(Aux!=null){
            if(Lista1.isIn(Aux.getElemento())==false){
                Lista1.anadirFinal(Aux.getElemento());
                Aux=Aux.getSiguiente();
            }
            else{
                Aux=Aux.getSiguiente();
            }
        }
    }
    
    public void RestarListas(LinkedList Lista1, LinkedList Lista2){
        Nodo<Punto> Aux = Lista2.getInicio();
        while(Aux!=null){
            Lista1.eliminar(Aux.getElemento());
            Aux=Aux.getSiguiente();
        }
    }
    

    public Nodo<T> getInicio() {
        return Inicio;
    }

    public Nodo<T> getUltimo() {
        return Ultimo;
    }
    
    public void setInicio(Nodo<T> inicio) {
        this.Inicio = inicio;
    }

    public int getTamanio() {
        return Tamanio;
    }

    public void setUltimo(Nodo<T> Ultimo) {
        this.Ultimo = Ultimo;
    }
    
    public void setTamanio(int tamanio) {
        this.Tamanio = tamanio;
    }
    
    
    
}
