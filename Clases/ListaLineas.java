package Clases;

import java.awt.geom.Line2D;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Lista que hereda de ListaSimple, destinada a guardar lineas para la interfaz
 * @author kevv87
 */
public class ListaLineas extends ListaSimple{
    
    
    /**
     * Sobreescribiendo el metodo para que el tipo del valor que devuelva sea una Linea.
     * @param pos Posicion en el array, debe estar entre 0 y n-1
     * @return Linea en la posicion pos
     * @throws java.lang.Exception
     */
    @Override
    public Line2D getValor(int pos) throws Exception{
        Line2D result = (Line2D)super.getValor(pos);
        return result;
    }
    
    /**
     * Dadas unas coordenadas de inicio y finalizacion, esta funcion se encarga de determinar
     * si hay alguna linea entre las posiciones dadas.
     * @param x1 Posicion inicial en x
     * @param x2 Posicion final en x
     * @param y1 Posicion inicial en y
     * @param y2 Posicion final en y 
     * @return Boolean que depende de la existencia de la linea en la lista
     * @throws Exception nn
     */
    public boolean isIn(int x1, int y1, int x2, int y2) throws Exception{
        boolean result = false;
        for(int i = 0; i < tamanio; i++){
            if(x1 == getValor(i).getX1() && y1==getValor(i).getY1()){
                result = x2 == getValor(i).getX2() && y2 == getValor(i).getY2();
            } else if(x1 == getValor(i).getX2() && y1 == getValor(i).getY2()){
                result = x2 == getValor(i).getX1() && y2 == getValor(i).getY1();
            }
            }
        return result;
        }
    
}
            
    
