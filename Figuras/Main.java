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
public class Main {     //NOTA: MODULO ES PARA X, DIVICION Y
    public static void main(String[] args){
        Recorrido Buscador = new Recorrido();
        
        Buscador.genMatriz();

        Punto A_ =new Punto(0,0);
        Punto B = new Punto(0,1);
        Punto C = new Punto(1,0);
        
        B.getReferencias().anadirFinal(A_);
        B.getReferencias().anadirFinal(C);

        Buscador.Entrada(00, 10);
        Buscador.Entrada(10, 11);
        Buscador.Entrada(01, 11);
        Buscador.Entrada(00, 11);

        LinkedList Aux = B.getReferencias();
        System.out.println("El tamanio es" + B.getReferencias().getTamanio());
        Aux.eliminar(A_);
        Aux.eliminar(C);
        System.out.println("El tamanio es" + B.getReferencias().getTamanio());


    }
}
