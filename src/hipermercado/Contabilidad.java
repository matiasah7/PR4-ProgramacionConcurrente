package hipermercado;

public class Contabilidad {

    private float saldo;

    public Contabilidad() {
        saldo = 0;
    }

    public void aņadeSaldo (float precioProducto){
        saldo += precioProducto;
    }

    public float dameSaldo (){
        return saldo;
    }
}
