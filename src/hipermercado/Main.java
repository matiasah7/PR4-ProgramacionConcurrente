package hipermercado;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Introduzca el número de clientes: ");
        int numeroClientes = new Scanner(System.in).nextInt();

        System.out.println("Introduzca el número de cajas: ");
        int numeroCajas = new Scanner(System.in).nextInt();

        Cola cola = new Cola();
        Contabilidad contabilidad = new Contabilidad();

        ArrayList<Caja> listaCajas = new ArrayList<Caja>();

        for (int i = 0; i < numeroCajas; i++) {
            listaCajas.add(new Caja(cola, contabilidad));
        }

        for (Caja caja : listaCajas) {
            caja.start();
        }

        //Duende aqui!

        long tiempoCola = System.nanoTime() / 1000000;
        for (int i = 0; i < numeroClientes; i++) {
            sleep((long) new Random().nextInt(6) * 1000);
            if (System.nanoTime() / 1000000 > tiempoCola + 60000) cola.cerrar();
            cola.añadirFinal();
        }

        for (Caja caja : listaCajas) {
            try {
                caja.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
