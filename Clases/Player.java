/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Player{

  private String name;
  
  // Socket que refiere al juego
  private Socket socket_game;
  private BufferedReader game_in;
  private PrintWriter game_out;
  
  //Socket que refiere a comandos que deben estar siendo siempre escuchados
  private Socket socket_comandos;
  private BufferedReader comandos_in;
  private PrintWriter comandos_out;
  private Player siguiente = null;

    public void setSiguiente(Player siguiente) {
        this.siguiente = siguiente;
    }

    public Player getSiguiente() {
        return siguiente;
    }

  public Player(Socket socket1, Socket socket2) throws IOException{
    this.socket_game = socket1;
    this.socket_comandos = socket2;
    game_in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
    game_out = new PrintWriter(socket1.getOutputStream(), true);
    comandos_in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
    comandos_out = new PrintWriter(socket2.getOutputStream(), true);
  }
  
  public Player(String name){
      this.name = name;
  }

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

    public void setSocket_game(Socket socket_game) {
        this.socket_game = socket_game;
    }

    public void setGame_in(BufferedReader game_in) {
        this.game_in = game_in;
    }

    public void setGame_out(PrintWriter game_out) {
        this.game_out = game_out;
    }

    public void setSocket_comandos(Socket socket_comandos) {
        this.socket_comandos = socket_comandos;
    }

    public void setComandos_in(BufferedReader comandos_in) {
        this.comandos_in = comandos_in;
    }

    public void setComandos_out(PrintWriter comandos_out) {
        this.comandos_out = comandos_out;
    }
    
    

  

}