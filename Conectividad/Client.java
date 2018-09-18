package Conectividad;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
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
    private Color color;

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
          System.out.println(line);
          Mensaje mensaje = mapper.readValue(line, Mensaje.class);
          String msj = mensaje.getAccion();


          // Protocolo.
          if(msj == null){  // Maneja los mensajes nulos
          }else if(msj.startsWith("MSG")){  //Imprima en consola
            System.out.println(line.substring(4)+"\n");
          }else if(msj.startsWith("YT")){  // Es su turno
            pantallaJuego.setActivo(true);
          }else if(msj.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
          }else if(msj.startsWith("DWL")){  //Dibuje una linea
              System.out.println(msj);
              int punto1 = Integer.parseInt(msj.substring(3,4));
              int punto2 = Integer.parseInt(msj.substring(5,6));

              LaminaJuego lamina = pantallaJuego.getLamina();
              lamina.addLine(punto1, punto2, color);
          }else if(msj.startsWith("CLR")){  // Cambie su color
              if(msj.substring(3).equals("BLU")){
                  color = Color.BLUE;
                  System.out.println("entré: "+msj);
              }else{
                  System.out.println("entré: "+msj);
                  color = Color.RED;
              }
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
