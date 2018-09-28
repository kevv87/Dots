package Conectividad;


public class Mensaje {

    private String accion;
    private int id1;
    private int id2;
    private Mensaje siguiente;


    public Mensaje(){
        accion = "";
        siguiente = null;
        
    }

    public Mensaje(String acc){
        accion = acc;
    }

    //Getter & Setter

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Mensaje getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Mensaje siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
