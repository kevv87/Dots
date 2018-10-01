package Conectividad;

/**
 * Clase mensaje, utilizada para enviar mensajes entre cliente y server consistentes
 * solo de texto plano.
 * @author kevv87
 */
public class Mensaje {

    private String protocolo;
    private String accion;
    private String puntaje;
    private Mensaje siguiente;
    private MensajeLinea segmento;

    
    /**
     * Dummy constructor
     */
    public Mensaje(){
        accion = "";
        protocolo = "";
        siguiente = null;
        
    }
    
    /**
     * Constructor.
     * @param protocolo Del mensaje.
     * @param acc Mensaje.
     */
    public Mensaje(String protocolo, String acc){
        this.protocolo = protocolo;
        accion = acc;
        siguiente = null;
    }
    
    /**
     * Constructor.
     * @param protocolo Del mensaje.
     * @param acc Mensaje.
     * @param puntaje Objeto mensaje con el protocolo de a quien se le asigna el puntaje y la accion los puntos.
     */
    public Mensaje(String protocolo, String acc, String puntaje){
        this.protocolo = protocolo;
        accion = acc;
        siguiente = null;
        this.puntaje = puntaje;
    }

    public Mensaje(String msj, MensajeLinea mensajeLinea){
        accion = msj;
        siguiente = null;
        segmento = mensajeLinea;
    }

    //Getter & Setter


    public MensajeLinea getSegmento() {
        return segmento;
    }

    public void setSegmento(MensajeLinea segmento) {
        this.segmento = segmento;
    }

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

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
    
    
    
    
    
    
}
