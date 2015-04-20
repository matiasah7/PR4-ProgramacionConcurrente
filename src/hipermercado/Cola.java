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
            Cliente nuevoCliente = new Cliente();
            colaClientes.add(nuevoCliente);
            System.out.println("-> El cliente: " + nuevoCliente.dameNombre() + " se ha unido a la cola.");
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
            System.out.println("<- El cliente: " + cliente.dameNombre() + " ha salido de la cola.");
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
        if (hayClientesCola()) {
            System.out.println("<- El cliente: " + colaClientes.get(0).dameNombre() + " ha salido de la cola.");
            return colaClientes.get(0);
        }
        return null;
    }

    private boolean hayClientesCola() {
        return (!colaClientes.isEmpty());
    }

    private boolean estaAbierta() {
        return (colaAbierta);
    }

}
