/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;


import Clases.ColaJugadores;
import Clases.Player;
import Interfaz.MarcoJuego;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interfaz.Punto;
import java.io.IOException;
import java.net.ServerSocket;
import com.fasterxml.jackson.databind.ObjectMapper;


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
  private static final ColaJugadores cola = new ColaJugadores();
  
  public static void main(String[] args) throws Exception{
    System.out.println("The server is running");
    listener = new ServerSocket(PORT);  //Escuchando el socket
    new Handler("juego").start();
    new Handler("cola").start();
    
  }
  
  
  /**
   * Clase encargada de manejar los hilos
   * @author kevv87
   */
  private static class Handler extends Thread{
      private final String tipo;

        public String getTipo() {
            return tipo;
        }
      
      public Handler(String tipo){
          this.tipo = tipo;
      }

      
      /**
       * Metodo encargado de la funcion de cada hilo distinto
       */
      @Override
      public void run(){
          if (tipo.equals("juego")){
              try {
                  juego();
              } catch (IOException | InterruptedException ex) {
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
            Player new_player = new Player(listener.accept(), listener.accept());
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
    String msj;
    Punto punto1;
    Punto punto2;
    ObjectMapper mapper = new ObjectMapper();
    
    // Server loop
    while(true){
        boolean found = false;
        // Esperando jugadores
        System.out.println("Esperando jugadores");
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
        
        Thread listenp1 = new Thread(){
            @Override
            public void run(){
                try {
                    while(true){
                        System.out.println("Waiting...");
                        String line = player1.getComandos_in().readLine(); 
                        if(line == null){
                            break;
                        }else if("END".equals(line)){
                            
                            player2.getComandos_out().println("END");
                            break;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread listenp2 = new Thread(){
            @Override
            public void run(){
                try {
                    while(true){
                        System.out.println("Waiting");
                        String line = player2.getComandos_in().readLine();
                        System.out.println("Received");
                        if(line == null){
                            break;
                        }else if("END".equals(line)){
                            player1.getComandos_out().println("END");
                            break;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        listenp1.start();  //hilos que se encargan de estar verificando si algun jugador salio
        listenp2.start();
      
          //Gameloop
          while(true){
              
              String color;
              if(current == 1){
                 color = "red";
              }else{
                 color = "blue";
              }
              
              if(current == 1){
                  current_player = player1;
              }else{
                  current_player = player2;
              }
              send(current_player,"YT");  // Establece turno
              
              msj = listen(current);
              
              if(msj==null || msj.equals("END")){
                  break;
              }
              
              String id1 = msj.substring(0,2);
              String id2 = msj.substring(3);
              
              if( !listenp1.isAlive() || !listenp2.isAlive()){  //Alguno de los dos jugadores salio del juego, cierra el socket.
                  stop_socket();
                  break;
              }
              
              

              send(current_player,"NYT");
              broadcast("DWL");
              
              //Some dumb chino logic
              
              String id_tosend1 = Integer.toString(Integer.parseInt(id1,8));
              String id_tosend2 = Integer.toString(Integer.parseInt(id2,8));
              
              broadcast(id_tosend1);
              broadcast(id_tosend2);
              broadcast(color);

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
      
      player.getGame_out().println(msg);
  }
  
  /**
   * Emite un mensaje a ambos jugadores.
   * @param msg Mensaje a emitir.
   */
  public static void broadcast(String msg){

      player1.getGame_out().println(msg);
      player2.getGame_out().println(msg);
  }
  
  /**
   * Se detiene a escuchar un cierto socket hasta recibir una respuesta
   * @param player El jugador cuyo socket se desea escuchar.
   * @return El mensaje que le envia el cliente
     * @throws java.io.IOException
   */
  public synchronized static String listen(int player) throws IOException{
      Player emitter;
      
      if(player == 1){
          emitter = player1;
      }else{
          emitter = player2;
      }
      
      return emitter.getGame_in().readLine();
  }

}
