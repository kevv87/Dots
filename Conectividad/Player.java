/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectividad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Player{

  private String name;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public Player(Socket socket) throws IOException{
    this.socket = socket;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
  }

  public BufferedReader getIn(){
    return in;
  }

  public PrintWriter getOut(){
    return out;
  }

}