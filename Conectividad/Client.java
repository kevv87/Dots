package Conectividad;

import Interfaz.MarcoJuego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client{

    private BufferedReader in; // entrada
    private PrintWriter out; //salida
    private MarcoJuego pantallaJuego;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public Client() throws Exception{
        pantallaJuego = new MarcoJuego();
    }

    private void run() throws IOException{

      String line;

      // Crea la conexion e inicializa los streams
      String serverAddress = "localhost";  //Ip del server
      Socket socket = new Socket(serverAddress, 9001);  //Creando socket en ip: serverAddress, puerto:9001
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      while(true){  // Debe procesar todos los mensajes del server
          line = in.readLine();  //Lee un mensaje entrante
          if(line == null){  // Maneja los mensaes nulos
          }else if(line.startsWith("MSG")){
            System.out.println(line.substring(4)+"\n");
          }else if(line.startsWith("YT")){
            System.out.println("Mi turno");
            String msg = reader.readLine();
            out.println(msg);
          }
      }
    }


    public static void main(String[] args) throws Exception{
      Client client = new Client();
      client.run();
    }
}
