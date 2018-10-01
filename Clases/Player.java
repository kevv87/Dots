package Clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase encargada de manejar cierta informacion de los jugadores, como sus sockets,
 * nombres e imagenes.
 * @author kevv87
 */
public class Player{

  private String name;  // Nombre del jugador
  
  // Socket que refiere al juego
  @JsonIgnore private BufferedReader game_in;
  @JsonIgnore private PrintWriter game_out;
  
  //Socket que refiere a comandos que deben estar siendo siempre escuchado
  @JsonIgnore private BufferedReader comandos_in;
  @JsonIgnore private PrintWriter comandos_out;
  
  private Player siguiente; // Siguiente jugador, en caso de estar en cola
  private String image_url;  // Url de la imagen del jugador
  
  private int number;
  
  private int puntaje;
  
    /**
     * Constructor
     * @param socket1 Socket del juego, discreto
     * @param socket2 Socket de comandos, continuo
     * @throws java.io.IOException
     */
    public Player(Socket socket1, Socket socket2) throws IOException{
      Socket socket_game;
      Socket socket_comandos;
      socket_game = socket1;
      socket_comandos = socket2;
      game_in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
      game_out = new PrintWriter(socket1.getOutputStream(), true);
      comandos_in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
      comandos_out = new PrintWriter(socket2.getOutputStream(), true);
      siguiente = null;
    }
  
    /**
     * Constructor
     * @param name Nombre del jugador
     * @param image Imagen del jugador
     */
    public Player(String name, String image){
        this.name = name;
        this.image_url = image;
        siguiente=null;
    }
  
  
    // Dummy constructor
    public Player(){
        this.name=null;
        this.image_url=null;
        this.siguiente=null;
    }
    
    // Getters & Setters
    public String getName() {
        return name;
    }

    public BufferedReader getGame_in() {
        return game_in;
    }

    public PrintWriter getGame_out() {
        return game_out;
    }

    public BufferedReader getComandos_in() {
        return comandos_in;
    }

    public PrintWriter getComandos_out() {
        return comandos_out;
    }

    public void setGame_in(BufferedReader game_in) {
        this.game_in = game_in;
    }

    public void setGame_out(PrintWriter game_out) {
        this.game_out = game_out;
    }

    public void setComandos_in(BufferedReader comandos_in) {
        this.comandos_in = comandos_in;
    }

    public void setComandos_out(PrintWriter comandos_out) {
        this.comandos_out = comandos_out;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String url) {
        this.image_url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    
    public void setSiguiente(Player siguiente) {
        this.siguiente = siguiente;
    }

    public Player getSiguiente() {
        return siguiente;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
    
    
    
    
}