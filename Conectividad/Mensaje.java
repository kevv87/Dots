package Conectividad;

/**
 * Clase mensaje, utilizada para enviar mensajes entre cliente y server consistentes
 * solo de texto plano.
 * @author kevv87
 */
public class Mensaje {

    private String accion;  // Mensaje
    private Mensaje siguiente;
    
    /**
     * Dummy constructor
     */
    public Mensaje(){
        accion = "";
        siguiente = null;
        
    }
    
    /**
     * Constructor.
     * @param acc Mensaje.
     */
    public Mensaje(String acc){
        accion = acc;
    }

    //Getters & Setters

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Mensaje getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Mensaje siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
