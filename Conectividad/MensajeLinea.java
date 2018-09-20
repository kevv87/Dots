package Conectividad;

public class MensajeLinea {
    private  String accion;
    private int id1;
    private int id2;

    public MensajeLinea(){
        accion = null;
    }

    public MensajeLinea(String acc, int id1, int id2){
        accion = acc;
        this.id1 = id1;
        this.id2 = id2;
    }

    //Getter & Setter

    public String getAccion() {
        return accion;
    }

    public int getId1(){
        return id1;
    }

    public int getId2(){
        return id2;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIds(int id1,int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

}
