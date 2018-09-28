/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;


import Clases.ColaJugadores;
import Clases.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interfaz.Punto;
import java.io.IOException;
import java.net.ServerSocket;
import com.fasterxml.jackson.databind.ObjectMapper;

import Figuras.Recorrido;




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
  private static final ColaMensajes cola_mensajes_p1 = new ColaMensajes();
  private static final ColaMensajes cola_mensajes_p2 = new ColaMensajes();
  
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
                catch (Exception ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);}

          }else if(tipo.equals("cola")){
              try {
                  cola();
              } catch (IOException ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }
                catch (Exception ex) {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }

      
  }
  
  
  /**
   * Hilo encargado de la cola
     * @throws java.io.IOException
   */
  public static void cola() throws IOException, Exception{
      try{ 
        while(true){
            Player new_player = new Player(listener.accept(), listener.accept());
            new_player.setName(new_player.getComandos_in().readLine());
            new_player.setImage(new_player.getComandos_in().readLine());
            cola.enqueue(new_player);
            send(new_player,"ENC");  // Le dice al jugador que esta en cola. !!!!!!!!!!!!!!!!
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

        broadcast("NEC");  //!!!!!!!!!!! Les dice a ambos jugadores que acaban de salir de la cola.

        String json_tosend = mapper.writeValueAsString(player2);
        player1.getGame_out().println(json_tosend);
        json_tosend = mapper.writeValueAsString(player1);
        player2.getGame_out().println(json_tosend);

        Player current_player;
        
        Thread listenp1 = new Thread(){
            @Override
            public void run() {
                try {
                    while(true){

                        String jsonMessage = player1.getComandos_in().readLine();
                        Mensaje jsonToClass = mapper.readValue(jsonMessage, Mensaje.class);
                        String line = jsonToClass.getAccion();

                        if(line == null){
                            break;
                        }else if("END".equals(line)){
                            cola_mensajes_p2.enqueue(new Mensaje("END"));
                            break;
                        }else if("GST".equals(line)){
                            if(cola_mensajes_p1.getTamanio() == 0){
                                jsonToClass.setAccion("0");
                                jsonMessage = mapper.writeValueAsString(jsonToClass);
                                player1.getComandos_out().println(jsonMessage);
                            }else{


                                String mensajeToJson = mapper.writeValueAsString(cola_mensajes_p1.dequeue());
                                player1.getComandos_out().println(mensajeToJson);
                                System.out.println(cola_mensajes_p1.peek());
                                mensajeToJson = mapper.writeValueAsString(cola_mensajes_p1.dequeue());
                                player1.getComandos_out().println(mensajeToJson);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread listenp2 = new Thread(){
            @Override
            public void run(){
                try {
                    while(true){

                        String jsonMessage = player2.getComandos_in().readLine();
                        Mensaje jsonToClass = mapper.readValue(jsonMessage, Mensaje.class);
                        String line = jsonToClass.getAccion();

                        if(line == null){
                            break;
                        }else if("GST".equals(line)){  // Get State
                            if(cola_mensajes_p2.getTamanio() == 0){

                                jsonToClass.setAccion("0");
                                jsonMessage = mapper.writeValueAsString(jsonToClass);
                                player2.getComandos_out().println(jsonMessage);

                            }else{

                                while(cola_mensajes_p2.getTamanio()!=0){
                                    player2.getComandos_out().println(cola_mensajes_p2.dequeue().getAccion());
                                }

                            }
                        }else if("END".equals(line)){
                            cola_mensajes_p1.enqueue(new Mensaje("END"));
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
              try {
                  send(current_player, "YT");  // Establece turno
              }
              catch (Exception ex){
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              msj = listen(current);
              
              if(msj==null || msj.equals("END")){
                  if(current*-1 == 1){
                  current_player = player1;
                }else{
                  current_player = player2;
                }
                try{
                    send(current_player, "YW");
                    break;
                }
                catch (Exception ex){
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

              }
              
              String id1 = msj.substring(0,2);
              String id2 = msj.substring(3);
              
              
              if( !listenp1.isAlive() || !listenp2.isAlive()){  //Alguno de los dos jugadores salio del juego, cierra el socket.
                  stop_socket();
                  break;
              }

              try{
                  send(current_player,"NYT");
              }
              catch (Exception ex){
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
              }

              
              
              //Some dumb chino logic
              System.out.println("Punto 1:"+id1);
              System.out.println("Punto 2:"+id2);
              Recorrido recorrido = new Recorrido();
              recorrido.Entrada(Integer.parseInt(id1), Integer.parseInt(id2));
              
              
              int id_tosend1 = (Integer.parseInt(id1,8));
              int id_tosend2 = (Integer.parseInt(id2,8));
              
              MensajeLinea lineToSend = new MensajeLinea(color, id_tosend1, id_tosend2);
              String jsonToSend = mapper.writeValueAsString(lineToSend);
              
              broadcast_queue("DWL");
              broadcast_queue(jsonToSend);

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
  public static void send(Player player, String msg) throws Exception{
      ObjectMapper mapper = new ObjectMapper();
      Mensaje mensaje = new Mensaje(msg);
      String msjToJson = mapper.writeValueAsString(mensaje);
      player.getGame_out().println(msjToJson);
  }
  
  /**
   * Emite un mensaje a ambos jugadores.
   * @param msg Mensaje a emitir.
   */
  public static void broadcast(String msg){
      try{
          send(player1,msg);
          send(player2,msg);//
      }
      catch (Exception ex){
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  /**
   * Agrega un mensaje a la cola de mensajes de cada jugador
     * @param msg A ingresar en cola
   */
  public static void broadcast_queue(String msg){
      cola_mensajes_p1.enqueue(new Mensaje(msg));
      cola_mensajes_p2.enqueue(new Mensaje(msg));
      
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
