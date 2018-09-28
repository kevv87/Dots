/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import Matriz.*;

/**
 *
 * @author Sebastián
 */
public class Main {
    public static void main(String[] args){
        Recorrido Buscador = new Recorrido();
        
        Buscador.genMatriz();

        Punto A =new Punto(0,0);
        Punto B = new Punto(0,1);
        Punto C = new Punto(1,0);
        
        A.getReferencias().anadirFinal(B);
        A.getReferencias().anadirFinal(C);
        B.getReferencias().anadirFinal(A);
        B.getReferencias().anadirFinal(C);
        C.getReferencias().anadirFinal(A);
        C.getReferencias().anadirFinal(B);
        
        Buscador.BuscaCaminos(A, A, B);
        
    }
}
