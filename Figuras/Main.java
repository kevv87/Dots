
package Figuras;

import Matriz.*;

/**
 *
 * @author Sebastián
 */
public class Main {
    public static void main(String[] args){
        Recorrido Buscador = new Recorrido();
        
        Punto P1 = new Punto(1,1);
        Punto P2 = new Punto(1,2);
        Punto P3 = new Punto(2,2);
        Punto P4 = new Punto(2,1);
        Punto P5 = new Punto(1,3);
        Punto P6 = new Punto(0,2);
        Punto P7 = new Punto(0,3);
        Punto P8 = new Punto(0,0);
                
        P1.getReferencias().anadirFinal(P2);
        P1.getReferencias().anadirFinal(P4);
        P2.getReferencias().anadirFinal(P1);
        P2.getReferencias().anadirFinal(P6);
        P2.getReferencias().anadirFinal(P5);
        P3.getReferencias().anadirFinal(P2);
        P3.getReferencias().anadirFinal(P4);
        P4.getReferencias().anadirFinal(P3);
        P4.getReferencias().anadirFinal(P1);
        P5.getReferencias().anadirFinal(P2);
        P2.getReferencias().anadirFinal(P3);
        P6.getReferencias().anadirFinal(P2);
        P6.getReferencias().anadirFinal(P7);
        P7.getReferencias().anadirFinal(P6);
        
       
        Buscador.ImpresionLista(Buscador.BuscaCaminos(P1, P1, P2));
    }
}
