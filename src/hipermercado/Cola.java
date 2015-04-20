package hipermercado;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Cola {

    private ArrayList<Cliente> colaClientes;
    private int actualClientes;
    private int maxClientes;
    private boolean colaAbierta;

    public Cola() {
        colaClientes = new ArrayList<Cliente>();
        maxClientes = 0;
        actualClientes = 0;
        colaAbierta = true;
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
        colaClientes.add(0, cliente);
    }

    public synchronized Cliente sacar() throws InterruptedException {
        if (hayClientesCola()) {
            Cliente cliente = colaClientes.get(0);
            colaClientes.remove(0);
            actualClientes--;
            return cliente;
        } else {
            if (estaAbierta()) {
                return esperarSegundos();
            }
        }
        return null;
    }

    public void cerrar() {
        colaAbierta = false;
    }

    private Cliente esperarSegundos() throws InterruptedException {
        long tiempo = System.currentTimeMillis();
        while (!hayClientesCola()) {
            if (System.currentTimeMillis() == tiempo + 1000) return null;
        }
        if (hayClientesCola()) return colaClientes.get(0);
        return null;
    }

    private boolean hayClientesCola() {
        return (!colaClientes.isEmpty());
    }

    private boolean estaAbierta() {
        return (colaAbierta);
    }

}
