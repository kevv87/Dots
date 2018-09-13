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
public class Perimetro {
    
    private Punto head;
    private Punto ultimo;
    private boolean cerrado;
    private LinkedList segmentos;
    private DoublyLinkedList puntos;
    private int Puntaje;
    
    public Perimetro(Punto A, Punto B){
        if (A.getPosY()==B.getPosY()){  //Se guarda como primer elemento el punto que esté más a la izquierda en caso de estar en misma fila
            if(A.getPosX()<B.getPosX()){
                head=A;
                ultimo=B;
            } else{
                ultimo=A;
                head=A;
            }
        } else{
            if(A.getPosY()<B.getPosY()){    //Se guarda como primer elemento el punto que esté más arriba.
                head=A;
                ultimo=B;
            } else{
                ultimo=A;
                head=B;
            }
        }
        head.setSiguiente(ultimo);
        this.puntos.anadirFinal(head);
        this.puntos.anadirFinal(ultimo);
        Segmento nuevo = new Segmento (head, ultimo);
        segmentos.anadirInicio(nuevo);
        cerrado=false;
    }

    public Punto getHead() {
        return head;
    }

    public void setHead(Punto head) {
        this.head = head;
    }

    public Punto getUltimo() {
        return ultimo;
    }

    public void setUltimo(Punto ultimo) {
        this.ultimo = ultimo;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public LinkedList getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(LinkedList segmentos) {
        this.segmentos = segmentos;
    }

    public DoublyLinkedList getPuntos() {
        return puntos;
    }

    public void setPuntos(DoublyLinkedList puntos) {
        this.puntos = puntos;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int Puntaje) {
        this.Puntaje = Puntaje;
    }
    
}
