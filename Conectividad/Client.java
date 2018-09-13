package Conectividad;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


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
    private void run() throws IOException, Exception{

      String line;

      // Crea la conexion e inicializa los streams
      String serverAddress = "localhost";  //Ip del server
      Socket socket = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      while(true){  // Debe procesar todos los mensajes del server
          line = in.readLine();  //Lee un mensaje entrante
          
          // Protocolo.
          if(line == null){  // Maneja los mensaes nulos
          }else if(line.startsWith("MSG")){  //Imprima en consola
            System.out.println(line.substring(4)+"\n");
          }else if(line.startsWith("YT")){  // Es su turno
            pantallaJuego.setActivo(true);
          }else if(line.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
          }else if(line.startsWith("DWL")){  //Dibuje una linea
              System.out.println(line);
              int punto1 = Integer.parseInt(line.substring(3, 4));
              int punto2 = Integer.parseInt(line.substring(5,6));
              LaminaJuego lamina = pantallaJuego.getLamina();
              lamina.addLine(punto1, punto2, Color.RED);
          }else if(line.startsWith("CLR")){  // Cambie su color
              if(line.substring(3).equals("BLU")){
                  color = Color.BLUE;
              }else{
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
