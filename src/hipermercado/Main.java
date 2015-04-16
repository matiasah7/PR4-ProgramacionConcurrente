package hipermercado;
public class Main{

    public static void main(String[] args){

        Cola cola = new Cola();
        for (int i = 0; i < 15; i++) {
            cola.añadirFinal();
        }

        Contabilidad contabilidad = new Contabilidad();

        Caja caja1 = new Caja(cola, contabilidad);
        Caja caja2 = new Caja(cola, contabilidad);
        Caja caja3 = new Caja(cola, contabilidad);

        caja1.start();
        caja2.start();
        caja3.start();

    }

}
