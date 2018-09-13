/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


public class Server{

  private static final int PORT = 9001;  // puerto

  public static void main(String[] args) throws Exception{
    System.out.println("The chat server is running");
    ServerSocket listener = new ServerSocket(PORT);  //Escuchando el socket

    boolean found = false;

    Player player1 = null;
    Player player2 = null;

    try{

      // Buscando jugadores
      while(!found){
        player1 = new Player(listener.accept());
        found = true;
      }
      found = false;
      while(!found){
        player2 = new Player(listener.accept());
        found = true;
      }

      player1.getOut().println("MSG algo");

      //Loop de menajes
      while(true){
        /*
        for(int i = 0;i<players.getTamanio();i++){
          players.getValor(i).getOut().println("MSG Va a empezar el juego!");
        }*/
        player1.getOut().println("MSG Pato");
        player1.getOut().println("YT");
        System.out.println(player1.getIn().readLine());  // Se queda esperando el mensaje
// Si lee null, el cliente se desconecto
        player2.getOut().println("MSG Ganso");
      }
    } finally{
      listener.close();
    }



  }

}
