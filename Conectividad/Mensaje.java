package Conectividad;


public class Mensaje {

    private String accion;
    private Mensaje siguiente;


    public Mensaje(){
        accion = "";
        siguiente = null;
        
    }

    public Mensaje(String acc){
        accion = acc;
        siguiente = null;
    }

    //Getter & Setter

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
