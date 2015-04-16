package hipermercado;

import java.sql.Time;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Cola {

    private ArrayList<Cliente> colaClientes;
    private int actualClientes;
    private int maxClientes;
    private int estadoCola = 0;  // 0 --> Abierta  1 --> Cerrada

    public Cola() {
        colaClientes = new ArrayList<Cliente>();
        maxClientes = 0;
        actualClientes = 0;
    }

    public int getMaxClientes() {
        return maxClientes;
    }

    public void añadirFinal() {
        if (estaAbierta()) {
            actualClientes++;
            if (actualClientes > maxClientes) maxClientes = actualClientes;
            colaClientes.add(new Cliente());
        }
    }

    public void añadirPrincipio(Cliente cliente) {
        colaClientes.add(0,cliente);
    }

    public Cliente sacar() throws InterruptedException {
        if (hayClientesCola()) {
            Cliente cliente = colaClientes.get(0);
            colaClientes.remove(0);
            actualClientes--;
        } else {
            if (estaAbierta()) {
                return esperarSegundos();
            }
        }
        return null;
    }

    public void cerrar() {
        estadoCola = 1;
    }

    private Cliente esperarSegundos() throws InterruptedException {
        long tiempo = System.currentTimeMillis();
        while (!hayClientesCola()){
            if(System.currentTimeMillis() == tiempo + 1000) return null;
        }
        if(hayClientesCola()) return colaClientes.get(0);
        return null;
    }

    private boolean hayClientesCola() {
        return (!colaClientes.isEmpty());
    }

    private boolean estaAbierta() {
        return (estadoCola == 0);
    }

}
