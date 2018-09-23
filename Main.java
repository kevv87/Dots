import Interfaz.*;
import Matriz.*;

/**
 *
 * @author kevv87
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Matriz m = new Matriz();
        m.inicializaMatriz(8,8);
        m.printMatriz();
        m.conectarSegmento(7,8);
        PantallaJuego juego = new PantallaJuego();

    }
}
