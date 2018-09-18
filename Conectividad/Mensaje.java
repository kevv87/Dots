package Conectividad;


public class Mensaje {

    private  String accion;


    public Mensaje(){
        accion = null;
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
}
