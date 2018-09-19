
import Clases.ColaJugadores;
import Clases.Player;
import Conectividad.Client;



/**
 *
 * @author kevv87
 */
public class Main {
    public static void main(String[] args){
        ColaJugadores cola = new ColaJugadores();
        cola.enqueue(new Player("Kevin"));
        cola.enqueue(new Player("Cvaz"));
        System.out.println(cola.dequeue().getName());
        System.out.println(cola.dequeue().getName());
        
    }
}
