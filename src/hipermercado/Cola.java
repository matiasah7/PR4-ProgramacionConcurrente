package hipermercado;

import java.util.ArrayList;

public class Cola {

    ArrayList<Cliente> colaClientes;
    private int maxClientes;
    private int estadoCola = 0;  // 0 --> Abierta  1 --> Cerrada

    public Cola() {
        colaClientes = new ArrayList<Cliente>();
        maxClientes = 0;
    }

    public int getMaxClientes() {
        return maxClientes;
    }

    public void añadirFinal() {
        if(estaAbierta()) colaClientes.add(new Cliente());
    }

    public void añadirPrincipio() {
        colaClientes.add(0, new Cliente());
    }

    public Cliente sacar() {
        if (hayClientesCola()) {
            Cliente cliente = colaClientes.get(0);
            colaClientes.remove(0);
        } else {
            if (!estaAbierta()) {
                //Semaforo que espera 10 segundos. Si llega un cliente en esos 10 pasa. Si no.

            }
        }
        return null;
    }

    public void cerrar(){
        estadoCola = 1;
    }

    private boolean hayClientesCola(){
        return (!colaClientes.isEmpty());
    }

    private boolean estaAbierta() {
        return (estadoCola == 0);
    }


}
