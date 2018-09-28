package Conectividad;

public class MensajeLinea {
    private int id1;
    private int id2;
    private String color;

    public MensajeLinea(){
        color = null;
    }

    public MensajeLinea(String color, int id1, int id2){
        this.color = color;
        this.id1 = id1;
        this.id2 = id2;
    }

    //Getter & Setter

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
