package Conectividad;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
import gnu.io.CommPortIdentifier;
import java.awt.Color;
import static java.awt.image.ImageObserver.ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;


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

    
    // Variables necesarias para arduino
    //Variables de conexion
    private OutputStream output=null;
    private gnu.io.SerialPort serialPort;
    private final String PUERTO= "/dev/ttyUSB0";
    
    private static final  int TIMEOUT=2000; //Milisegundos
    
    private static final int DATA_RATE = 9600;
    
    
    public void inicializarConexion(){
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
    
    private void enviarDatosArduino(String datos){
        try{
            output.write(datos.getBytes());
        }catch( Exception e){
            System.out.println("Hubo un problema con el arduino");
        }
    }
    /**
     * Constructor
     */
    public Client() throws Exception{
        pantallaJuego = new MarcoJuego();
        inicializarConexion();
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
            enviarDatosArduino("1");
          }else if(line.startsWith("NYT")){  //No es su turno
              pantallaJuego.setActivo(false);
              enviarDatosArduino("0");
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
