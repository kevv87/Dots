package Conectividad;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;

import gnu.io.CommPortIdentifier;

import Interfaz.Punto;

import java.awt.Color;
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


/**
 * Clase encargada de la parte grafica del juego.
 * @author kevv87
 * @version 0.2
 */
public class Client{

    private BufferedReader in; // entrada
    private static PrintWriter out; //salida
    private final MarcoJuego pantallaJuego;
    private final ObjectMapper mapper = new ObjectMapper();

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    
    // Variables necesarias para arduino
    //Variables de conexion
    private OutputStream output=null;
    private gnu.io.SerialPort serialPort;
    private final String PUERTO= "/dev/ttyUSB0";
    
    private static final  int TIMEOUT=2000; //Milisegundos
    
    private static final int DATA_RATE = 9600;
    
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
        }catch(Exception e){
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
        }catch( Exception e){
            System.out.println("Hubo un problema con el arduino");
        }
    }
    /**
     * Constructor
     * @param serverAddress Direccion IP del server
     * @throws java.lang.Exception
     */
    public Client(String serverAddress) throws Exception{
        pantallaJuego = new MarcoJuego();
        init(serverAddress);
        run_juego();
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
    public void init(String serverAddress) throws IOException{
        // Crea la conexion e inicializa los streams
      Socket socket = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      System.out.println("Conectado");
    }
    
    
    
    /**
     * Metodo que maneja toda la logica del cliente, desde crear la conexion hasta manejar el protocolo
     */
    private void run_juego() throws IOException, MismatchedInputException, Exception{
      String line;

      

      while(true){  // Debe procesar todos los mensajes del server
          line = in.readLine();  //Lee un mensaje entrante
          
          // Protocolo.
          if(line == null){  // Maneja los mensajes nulos
          }else if(line.startsWith("MSG")){  //Imprima en consola
          }else if(line.startsWith("YT")){  // Es su turno
            pantallaJuego.setActivo(true);
          }else if(line.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
          }else if(line.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
          }else if(line.startsWith("DWL")){  //Dibuje una linea
              System.out.println(line);
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
          }else if(line.startsWith("ENC")){
              System.out.println("Estoy en cola");
          }else if(line.startsWith("NEC")){
              System.out.println("Sali de cola!");
          }else{
              System.out.println("mensaje no identificado");     
              System.out.println(line);
              

          }
      }
    }
    
    /**
     * Envia un mensaje al servidor.
     * @param msg Mensaje a enviar al servidor
     */
    public static void send(String msg){  // Es estatico porque ocupo poder mandar lugares desde cualquier lugar del codigo
        out.println(msg);
    }
}
