package Conectividad;

/**
 * Clase encargada de manejar mensajes de lineas entre el servidor y el cliente
 * @author kevv87
 */
public class MensajeLinea {
    private int id1;  // Id del primer punto de la linea
    private int id2;  // Id del segundo punto de la linea
    private String color;  // Color de la linea

    /**
     * Dummy constructor
     */
    public MensajeLinea(){
        color = null;
    }
    
    /**
     * Constructor
     * @param color Color de la linea
     * @param id1 Id del primer punto
     * @param id2 Id del segundo punto
     */
    public MensajeLinea(String color, int id1, int id2){
        this.color = color;
        this.id1 = id1;
        this.id2 = id2;
    }

    //Getters & Setters

    public String getColor() {
        return color;
    }

    public int getId1(){
        return id1;
    }

    public int getId2(){
        return id2;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIds(int id1,int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

}
