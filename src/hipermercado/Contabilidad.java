package hipermercado;

public class Contabilidad {

    private float saldo;

    public Contabilidad() {
        saldo = 0;
    }

    public void a�adeSaldo (float precioProducto){
        saldo += precioProducto;
    }

    public float dameSaldo (){
        return saldo;
    }
}
