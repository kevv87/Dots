package Conectividad;


public class Mensaje {

    private String accion;
    private Mensaje siguiente;
    private MensajeLinea segmento;


    public Mensaje(){
        accion = "";
        siguiente = null;
        
    }

    public Mensaje(String acc){
        accion = acc;
        siguiente = null;
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
    
    
    
    
}
