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

        Buscador.Entrada(22, 21);       //Problema con Y

    }
}
