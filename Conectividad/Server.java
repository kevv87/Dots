/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;

import Clases.ColaJugadores;
import Clases.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
  private static ColaJugadores cola = new ColaJugadores();
  
  public static void main(String[] args) throws Exception{
    System.out.println("The chat server is running");
    listener = new ServerSocket(PORT);  //Escuchando el socket
    new Handler("juego").start();
    new Handler("cola").start();
    
  }
  
  
  /**
   * Clase encargada de manejar los hilos
   * @author kevv87
   */
  private static class Handler extends Thread{
      private String tipo;
      
      public Handler(String tipo){
          this.tipo = tipo;
      }
      
      /**
       * Metodo encargado de la funcion de cada hilo distinto
       */
      public void run(){
          if (tipo.equals("juego")){
              try {
                  juego();
              } catch (IOException ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }
          }else if(tipo.equals("cola")){
              try {
                  cola();
              } catch (IOException ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }
      
  }
  
  
  /**
   * Hilo encargado de la cola
     * @throws java.io.IOException
   */
  public static void cola() throws IOException{
      try{ 
        while(true){
            Player new_player = new Player(listener.accept());
            cola.enqueue(new_player);
            send(new_player,"ENC");  // Le dice al jugador que esta en cola.
            System.out.println("Nuevo jugador en cola!");
            System.out.println("Tamanno de la cola: "+cola.getTamanio());
            
        }
      }finally{
          stop_socket();
      }
  }
  
  /**
   * Hilo encargado del juego
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
   */
  public static void juego() throws IOException, InterruptedException{
    // Server loop
    while(true){
        boolean found = false;
        // Esperando jugadores
        while(!found){
            if(cola.getTamanio()>=2){
                found = true;
            }else{
                Thread.sleep(1000);  //Delay
            }
        }

        player1 = cola.dequeue();
        player2 = cola.dequeue();  
        broadcast("NEC");  //Les dice a ambos jugadores que acaban de salir de la cola.
        Player current_player;
          //Configuracion inicial de clientes
          send(player1,"CLRBLU");  // el jugador 1 tiene color azul
          send(player2,"CLRRED");  // el jugador 2 tiene color rojo


          //Gameloop
          while(true){
              if(current == 1){
                  current_player = player1;
              }else{
                  current_player = player2;
              }
              send(current_player,"YT");  // Establece turno

              String punto1 = listen(current);
              while("".equals(punto1)){
                  punto1 = listen(current);
              }
              String punto2 = listen(current);
              while("".equals(punto2)){
                  punto2 = listen(current);
              }

              if(punto1 == null || punto2 == null){  //Alguno de los dos jugadores salio del juego, cierra el socket.
                  stop_socket();
                  break;
              }

              send(current_player,"NYT");
              System.out.println(punto1+","+punto2);
              broadcast("DWL"+punto1+","+punto2);

              current *= -1;  // Cambio de turno
        }      
    }
  }
  
  /**
   * Detiene los sockets y libera los puertos.
     * @throws java.io.IOException
   */
  public static void stop_socket() throws IOException{
      listener.close();
  }
  
  /**
   * Envia un mensaje al cliente dado.
   * @param player El numero del jugador al cual se le quiere entregar el mensaje
   * @param msg El mensaje a enviar.
   */
  public static void send(Player player, String msg){
      player.getOut().println(msg);
  }
  
  /**
   * Emite un mensaje a ambos jugadores.
   * @param msg Mensaje a emitir.
   */
  public static void broadcast(String msg){
      player1.getOut().println(msg);
      player2.getOut().println(msg);
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
