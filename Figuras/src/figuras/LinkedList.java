/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

/**
 *
 * @author 
 */
public class LinkedList<T>{
    
    private Nodo<T> Inicio;
    private int Tamanio;
    
    public LinkedList(){    
        Inicio=null;
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
        Nodo<T> nuevo = new Nodo<>(dato);
        if(Inicio==null)
            Inicio = nuevo;
        else{
            Nodo<T> current = Inicio;
            while(current.getSiguiente()!=null)
                current=current.getSiguiente();
                          
            current.setSiguiente(nuevo);
        }
        Tamanio++;
    }
    public boolean buscar(T dato){
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

    public Nodo<T> getInicio() {
        return Inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.Inicio = inicio;
    }

    public int getTamanio() {
        return Tamanio;
    }

    public void setTamanio(int tamanio) {
        this.Tamanio = tamanio;
    }
    
    
    
}
