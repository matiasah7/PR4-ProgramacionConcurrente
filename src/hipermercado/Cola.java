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

    public synchronized void añadirFinal() {
        if (colaAbierta) {
            actualClientes++;
            if (actualClientes > maxClientes) maxClientes = actualClientes;
            Cliente nuevoCliente = new Cliente();
            colaClientes.add(nuevoCliente);
            System.out.println("-> El cliente: " + nuevoCliente.dameNombre() + " se ha unido a la cola " + " en el momento : " + System.nanoTime() / 1000000);
        }
        System.out.println("javito");
    }

    public synchronized void añadirPrincipio(Cliente cliente) {
        colaClientes.add(0, cliente);
    }

    public synchronized Cliente sacar() throws Exception {
        if (hayClientesCola()) {
            Cliente cliente = colaClientes.get(0);
            colaClientes.remove(0);
            actualClientes--;
            System.out.println("<- El cliente: " + cliente.dameNombre() + " ha salido de la cola " + " en el momento : " + System.nanoTime() / 1000000);
            return cliente;
        } else {
            if (colaAbierta) {
                return esperarSegundos();
            }
        }
        return null;
    }

    public void cerrar() {
        colaAbierta = false;
    }

    private synchronized Cliente esperarSegundos() {
        while (!hayClientesCola()) {
            try {
                wait(10000);
                if (!hayClientesCola())return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (hayClientesCola()) {
            System.out.println("<- El cliente: " + colaClientes.get(0).dameNombre() + " ha salido de la cola " + " en el momento : " + System.nanoTime() / 1000000);
            Cliente cliente = colaClientes.get(0);
            colaClientes.remove(0);
            return cliente;
        }
        return null;
    }

    private boolean hayClientesCola() {
        return (!colaClientes.isEmpty());
    }

}
