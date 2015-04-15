package hipermercado;

public class Contabilidad {

    private Double saldo;

    public Contabilidad() {
        saldo = 0.;
    }

    public void añadeSaldo (Double precioProducto){
        saldo += precioProducto;
    }

    public Double dameSaldo (){
        return saldo;
    }
}
