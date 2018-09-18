package Conectividad;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
import Interfaz.Punto;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;


/**
 * Clase encargada de la parte grafica del juego.
 * @author kevv87
 * @version 0.2
 */
public class Client{

    private BufferedReader in; // entrada
    private static PrintWriter out; //salida
    private MarcoJuego pantallaJuego;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Constructor
     */
    public Client() throws Exception{
        pantallaJuego = new MarcoJuego();
    }
    
    /**
     * Metodo que maneja toda la logica del cliente, desde crear la conexion hasta manejar el protocolo
     */
    private void run() throws IOException, MismatchedInputException, Exception{
      ObjectMapper mapper = new ObjectMapper();
      String line;

      // Crea la conexion e inicializa los streams
      String serverAddress = "localhost";  //Ip del server
      Socket socket = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      while(true){  // Debe procesar todos los mensajes del server
          line = in.readLine();  //Lee un mensaje entrante
          Mensaje mensaje = new Mensaje();
          if(line != null){
              mensaje = mapper.readValue(line, Mensaje.class);
          }else{
              line = in.readLine();
          }
          System.out.println(mensaje);
          String msj = mensaje.getAccion();


          // Protocolo.
          if(msj == null){  // Maneja los mensajes nulos
          }else if(msj.startsWith("MSG")){  //Imprima en consola
          }else if(msj.startsWith("YT")){  // Es su turno
            pantallaJuego.setActivo(true);
          }else if(msj.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
          }else if(msj.startsWith("DWL")){  //Dibuje una linea
              System.out.println(msj);
              Punto punto1 = mapper.readValue(in.readLine(), Punto.class);
              Punto punto2 = mapper.readValue(in.readLine(), Punto.class);
              String colorm = in.readLine();
              Color color;
              if("red".equals(colorm)){
                  color = Color.RED;
              }else{
                  color = Color.BLUE;
              }
              
              LaminaJuego lamina = pantallaJuego.getLamina();
              lamina.addLine(punto1, punto2, color);
          }
      }
    }


    public static void main(String[] args) throws Exception{
      Client client = new Client();
      client.run();
    }
    
    /**
     * Envia un mensaje al servidor.
     * @param msg Mensaje a enviar al servidor
     */
    public static void send(String msg){  // Es estatico porque ocupo poder mandar lugares desde cualquier lugar del codigo
        out.println(msg);
    }
}
