/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.ListaSimple;
import java.awt.Polygon;

/**
 *
 * @author kevv87
 */
public class ListaPoligonos extends ListaSimple{
    /**
     * Sobreescribiendo el metodo para que el tipo del valor que devuelva sea una Linea.
     * @param pos Posicion en el array, debe estar entre 0 y n-1
     * @return Linea en la posicion pos
     * @throws java.lang.Exception
     */
    @Override
    public Polygon getValor(int pos) throws Exception{
        Polygon result = (Polygon)super.getValor(pos);
        return result;
    }
}
