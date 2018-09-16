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
public class Segmento {
    private Punto Punto_A;   //punto A del segmento
    private Punto Punto_B;   //punto B del segmento
    private boolean Suma;
    private int Direccion;  //0,1,2,3 vertical, horizontal, diagonal izq superior a derecha inferior, diagonal izq inferior a derecha superior, respectivamente
    /**
     * Constructor
     * @param Punto_A y Punto_B como puntos del segmento.
     */
    Segmento(Punto A, Punto B){
        if (A.getPosY()==B.getPosY()){  //Se guarda como primer elemento el punto que esté más a la izquierda en caso de estar en misma fila
            if(A.getPosX()<B.getPosX()){
                Punto_A=A;
                Punto_B=B;
                        
            } else{
                Punto_A=B;
                Punto_B=A;
            }
        } else{
            if(A.getPosY()<B.getPosY()){    //Se guarda como primer elemento el punto que esté más arriba.
                Punto_A=A;
                Punto_B=B;
            } else{
                Punto_A=B;
                Punto_B=A;
            }
        }
        if(Punto_A.getPosY()==Punto_B.getPosY()){
            Direccion=0;
        } else if(Punto_A.getPosX()==Punto_B.getPosX()){
            Direccion=1;
        } else if(Punto_A.getPosY()<Punto_B.getPosY()){
            Direccion=2;
        } else{
            Direccion=3;
        }
        Suma=true;
    }

    public boolean colineales(Segmento A, Segmento B){
        if(A.getPunto_A()==B.getPunto_A()||A.getPunto_A()==B.getPunto_B()||A.getPunto_B()==B.getPunto_A()||A.getPunto_B()==B.getPunto_B()){
            if(A.Direccion==B.Direccion){
                return true;
            }
            else{
                return false;
            }
        } else{
            return false;
        }
    }

    public Punto getPunto_A() {
        return Punto_A;
    }

    public void setPunto_A(Punto Punto_A) {
        this.Punto_A = Punto_A;
    }

    public Punto getPunto_B() {
        return Punto_B;
    }

    public int getDireccion() {
        return Direccion;
    }
    

    public void setPunto_B(Punto Punto_B) {
        this.Punto_B = Punto_B;
    }

    public boolean isSuma() {
        return Suma;
    }

    public void setSuma(boolean suma) {
        this.Suma = suma;
    }

    public void setDireccion(int Direccion) {
        this.Direccion = Direccion;
    }
    
       
}      