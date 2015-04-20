package hipermercado;
public class Main{

    public static void main(String[] args){

        Cola cola = new Cola();

        Contabilidad contabilidad = new Contabilidad();

        Caja caja1 = new Caja(cola, contabilidad);
        Caja caja2 = new Caja(cola, contabilidad);
        Caja caja3 = new Caja(cola, contabilidad);

        for (int i = 0; i < 15; i++) {
            cola.añadirFinal();
        }

        caja1.start();
        caja2.start();
        caja3.start();

        try {
            caja1.join();
            caja2.join();
            caja3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
