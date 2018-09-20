/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;

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

  public static void main(String[] args) throws Exception{
    System.out.println("The chat server is running");
    listener = new ServerSocket(PORT);  //Escuchando el socket
    ObjectMapper mapper = new ObjectMapper();


    boolean found = false;
    // Buscando jugadores
    while(!found){
        player1 = new Player(listener.accept());
        found = true;
        System.out.println("Jugador 1!");
      }
      found = false;
      while(!found){
        player2 = new Player(listener.accept());
        found = true;
        System.out.println("Jugador 2!");
      }

      MensajeLinea mensajeLinea = new MensajeLinea();
      Mensaje mensaje = new Mensaje("CLRBLU");
      String msjJson = mapper.writeValueAsString(mensaje);
      //Configuracion inicial de clientes
      send(1,msjJson);  // el jugador 1 tiene color azul
      mensaje.setAccion("CLRRED");
      msjJson = mapper.writeValueAsString(mensaje);
      send(2,msjJson);  // el jugador 2 tiene color rojo
      System.out.println("Jugador 2 color: " + msjJson);

      //Gameloop
      while(true){
          mensaje.setAccion("YT");
          msjJson = mapper.writeValueAsString(mensaje);
          send(current,msjJson);  // Establece turno

          String punto1 = listen(current);
          while("".equals(punto1)){
              punto1 = listen(current);
          }
          String punto2 = listen(current);
          while("".equals(punto2)){
              punto2 = listen(current);
          }

          if(punto1 == null || punto2 == null){  //Alguno de los dos jugadores salio del juego, cierra el socket.
              stop();
              break;
          }
          mensaje.setAccion("NYT");
          msjJson = mapper.writeValueAsString(mensaje);
          send(current,msjJson);
          System.out.println(punto1+","+punto2);

          mensajeLinea.setAccion("DWL");
          mensajeLinea.setIds(Integer.parseInt(punto1),Integer.parseInt(punto2));
          msjJson = mapper.writeValueAsString(mensajeLinea);
          broadcast(msjJson);

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
