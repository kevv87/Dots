/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Clase encargada de correr el servidor y llevar a cabo toda la logica del juego
 * @author kevv87
 * @version 0.1
 */
public class Server{

  private static final int PORT = 9001;  // puerto
  private static Player player1 = null;
  private static Player player2 = null;
  private static ServerSocket listener;
  private static int current = 1;
  
  public static void main(String[] args) throws Exception{
    System.out.println("The chat server is running");
    listener = new ServerSocket(PORT);  //Escuchando el socket

    boolean found = false;
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
      
      //Configuracion inicial de clientes
      send(1,"CLRBLU");  // el jugador 1 tiene color azul
      send(2,"CLRRED");  // el jugador 2 tiene color rojo
      
      
      //Gameloop
      while(true){
          send(current,"YT");  // es el turno del jugador 1
          
          String punto1 = listen(current);
          while("".equals(punto1)){
              punto1 = listen(current);
          }
          String punto2 = listen(current);
          while("".equals(punto2)){
              punto2 = listen(current);
          }
          send(current,"NYT");
          System.out.println(punto1+","+punto2);
          send(current, "DWL"+punto1+","+punto2);
          
          current *= -1;  // Cambio de turno
      }



  }
  
  /**
   * Detiene los sockets y libera los puertos.
   */
  public static void stop() throws IOException{
      listener.close();
  }
  
  /**
   * Envia un mensaje al cliente dado.
   * @param player El numero del jugador al cual se le quiere entregar el mensaje
   * @param msg El mensaje a enviar.
   */
  public static void send(int player, String msg){
      Player recipient;
      
      if(player == 1){
          recipient = player1;
      }else{
          recipient = player2;
      }
      
      recipient.getOut().println(msg);
  }
  
  /**
   * Se detiene a escuchar un cierto socket hasta recibir una respuesta
   * @param player El jugador cuyo socket se desea escuchar.
   * @return El mensaje que le envia el cliente
   */
  public static String listen(int player) throws IOException{
      Player emitter;
      
      if(player == 1){
          emitter = player1;
      }else{
          emitter = player2;
      }
      
      return emitter.getIn().readLine();
  }

}
