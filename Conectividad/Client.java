package Conectividad;

import Clases.Player;
import Interfaz.JuegoController;

import gnu.io.CommPortIdentifier;
import Matriz.ListaSimple;

import javafx.scene.paint.Color;
import static java.awt.image.ImageObserver.ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase encargada de la parte grafica del juego.
 * @author kevv87
 * @version 0.2
 */
public class Client{
    
    //Socket referido al juego
    private static Socket socket_game;
    private BufferedReader in_game; // entrada
    private static PrintWriter out_game; //salida
    
    //Socket referido a comandos
    private static Socket socket_comandos;
    private BufferedReader in_comandos; // entrada
    private static PrintWriter out_comandos; //salida

    private final ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper mapper2 = new ObjectMapper(); //para métodos estáticos
    private static boolean alive=true;
    private static ListaSimple puntos_a_enviar = new ListaSimple();

    

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    //Jugadores
    private final Player myPlayer;
    private Player oponente;

    
    // Variables necesarias para la conexion con arduino
    private OutputStream output=null;
    private gnu.io.SerialPort serialPort;
    private final String PUERTO= "/dev/ttyUSB0";
    private static final  int TIMEOUT=2000; //Milisegundos
    private static final int DATA_RATE = 9600;
    
    private final JuegoController interfaz;  // referencia a la interfaz
    
    /**
     * Inicializa la conexion con el arduino
     */
    public void inicializarConexionArduino(){
        CommPortIdentifier puertoID =null;
        Enumeration puertoEnum = CommPortIdentifier.getPortIdentifiers();
        
        while(puertoEnum.hasMoreElements()){  // Mientras existan puertos
            CommPortIdentifier actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
            if(PUERTO.equals(actualPuertoID.getName())){
                puertoID=actualPuertoID;
                break;
            }
        }
        
        if(puertoID == null){
            System.exit(ERROR);
            return;
        }
        
        try{
            serialPort = (gnu.io.SerialPort) puertoID.open(this.getClass().getName(), TIMEOUT);
            // Parametros puerto serie
            
            serialPort.setSerialPortParams(DATA_RATE, gnu.io.SerialPort.DATABITS_8, gnu.io.SerialPort.STOPBITS_1, gnu.io.SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();
        }catch(PortInUseException | UnsupportedCommOperationException | IOException e){
            System.exit(ERROR);
        }
    }
    
    /**
     * Envia datos al serial al que escucha el arduino
     * @param datos Datos a enviar.
     */
    private void enviarDatosArduino(String datos){
        try{
            output.write(datos.getBytes());
        }catch( IOException e){
            System.out.println("Hubo un problema con el arduino");
        }
    }
    
    /**
     * Constructor
     * @param serverAddress Direccion IP del server
     * @param interfaz Controlador de la interfaz
     * @throws java.lang.Exception
     */
    public Client(String serverAddress, JuegoController interfaz) throws Exception{
        this.interfaz = interfaz;

        myPlayer = new Player((String)interfaz.getUserDataList().getValor(1),(String)interfaz.getUserDataList().getValor(3)); // Crea un nuevo jugador
        init(serverAddress);  // Inicializa la conexion al server
        
        Thread juego = new Thread(){ // Creando el hilo discreto

            @Override
            public void run(){
                try {
                    run_juego();
                } catch (Exception ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        
        // Corre los hilos
        juego.start();
        
        // Arduino
        /*
        try{
            inicializarConexion();
        }catch(Exception e){
            System.out.println("Error con arduino");
        }*/
    }
    
    
    /**
     * Inicializa la conexion con el server
     * @param serverAddress direccion IP del server
     * @throws java.io.IOException
     */
    public void init(String serverAddress) throws IOException, Exception{
        // Crea la conexion e inicializa los streams
      socket_game = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in_game = new BufferedReader(new InputStreamReader(socket_game.getInputStream()));
      out_game = new PrintWriter(socket_game.getOutputStream(), true);
      
      socket_comandos = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in_comandos = new BufferedReader(new InputStreamReader(socket_comandos.getInputStream()));
      out_comandos = new PrintWriter(socket_comandos.getOutputStream(), true);
      
      System.out.println("Conectado, enviando datos del jugador");
      send_comando(myPlayer.getName());
      send_comando(myPlayer.getImage_url());
    }
    
    
    
    /**
     * Maneja el protocolo del juego
     */
    private void run_juego() throws IOException, MismatchedInputException, Exception{
      String line = null;
      String jsonMessage = null;
      Mensaje jsonToClass = new Mensaje();

      while(true){  // Debe procesar todos los mensajes del server
          try{
              jsonMessage = in_game.readLine();//Lee un mensaje entrante
              jsonToClass = mapper.readValue(jsonMessage, Mensaje.class);
              line = jsonToClass.getAccion();

          } catch(Exception e){
              line = null;
          }
          // Protocolo
          if(line == null){  // Maneja los mensajes nulos
          }else if(line.startsWith("MSG")){  //Imprima en consola
          }else if(line.startsWith("YT")){  // Es su turno
            interfaz.setActivo(true);
          }else if(line.startsWith("NYT")){  //No es su turno
              interfaz.setActivo(false);
          }else if(line.startsWith("ENC")){  // En cola
              System.out.println("Estoy en cola");
          }else if(line.startsWith("NEC")){  //Salida de cola
              oponente = mapper.readValue(in_game.readLine(), Player.class);  // Defino mi oponente
              
              // Pone el nombre y la imagen del oponente en la interfaz
              interfaz.setFoeName(oponente.getName());
              interfaz.setFoeImage(oponente.getImage_url());
              System.out.println("Inicia juego");
              Thread comandos = new Thread(){  // Creando el hilo continuo
                  @Override
                  public void run(){
                      try {
                          run_comando();
                      } catch (IOException ex) {
                          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (Exception ex) {
                          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
              };
              comandos.start();
          }else if(line.startsWith("END")){  // Fin del juego
              Client.close();
          }else if(line.startsWith("YW")){  //Yo gano
              System.out.println("I win");
          }else if(line.startsWith("YL")){  // Yo pierdo
              System.out.println("I lose");
          }else{
              System.out.println("mensaje no identificado");     
              System.out.println(line);
          }
      }
    }

    /**
     * Maneja el protocolo de comandos
     * @throws java.io.IOException
     */
    @SuppressWarnings("empty-statement")
    public void run_comando() throws IOException, Exception{
      
        String line = null;

        long n_segundos = 10; // Intervalo entre cada refresh
        String jsonMessage = null;
        Mensaje jsonToClass = new Mensaje();

        // Loop principal del socket continuo
        while(true){
            jsonToClass.setAccion("GST"); // Get estado al server
            jsonMessage = mapper.writeValueAsString(jsonToClass);
            out_comandos.println(jsonMessage);
            try{
               
                jsonMessage = in_comandos.readLine(); // Espera y lee la respuesta
             
                jsonToClass = mapper.readValue(jsonMessage, Mensaje.class);
                line = jsonToClass.getAccion();  //Lee un mensaje entrante

              } catch(IOException e){
                  line = null;
              }
            if(line==null){  // En caso de que se ciere el socket, la respuesta es null y cierra sockets
                close();
                break;
            }else if("END".equals(line)){  // Si la respuesta es END, termina el juego y cierra sockets
                close();

            }else if("DWL".equals(line)){
                line = in_comandos.readLine();

                Mensaje linea= mapper.readValue(line, Mensaje.class);

                int id1 = linea.getSegmento().getId1();
                int id2 = linea.getSegmento().getId2();
                String colorm = linea.getSegmento().getColor();
                Color color;
                if("red".equals(colorm)){
                    color = Color.RED;
                }else{
                    color = Color.BLUE;
                }
                interfaz.addLine(id1, id2, color);
            }else if("0".equals(line)){  // Si la respuesta es 0, es el estado normal, no dibuja nada.
                ;
            }
            
            Thread.sleep(n_segundos);  // Espera n_segundos antes de volver a intentar refrescar
        }
    }
    
    
    /**
     * Envia un mensaje al servidor mediante el protocolo de juego.
     * @param msg Mensaje a enviar al servidor
     */
    public static void send_game(String msg){  // Es estatico porque ocupo poder mandar lugares desde cualquier lugar del codigo
        out_game.println(msg); //NO USAR (HASTA PASAR A FORMATO JSON)
    }
    
    
    /**
     * Envia un mensaje al servidor mediante el protocolo de comandos.
     * @param msg Mensaje a enviar al servidor
     */
    public static void send_comando(String msg) throws Exception{  // Es estatico porque ocupo poder mandar lugares desde cualquier lugar del codigo

        Mensaje jsonToClass = new Mensaje(msg);
        String jsonMessage = mapper2.writeValueAsString(jsonToClass);
        out_comandos.println(jsonMessage);
    }
    
    /**
     * Termina la conexion con el server
     * @throws java.io.IOException
     */
    public static void close() throws Exception{

        send_comando("END");
        socket_game.close();
        socket_comandos.close();
        alive = false;
        System.out.println("Conexion terminada");
        System.exit(0);
    }
    
    /**
     * Getter de alive
     * @return Estado del cliente.
     */
    public static boolean isAlive(){
        return alive;
    }
    
    /**
     * Getter de los puntos a enviar
     * @return Puntos a enviar
     */
    public static ListaSimple getPuntos_a_enviar() {
        return puntos_a_enviar;
    }
}
