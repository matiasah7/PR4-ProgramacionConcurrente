package hipermercado;

import java.util.ArrayList;

public class Cola {

    ArrayList<Cliente> colaClientes;
    private int maxClientes;

    public Cola() {
        colaClientes = new ArrayList<Cliente>();
        maxClientes = 0;
    }

    public int getMaxClientes() {
        return maxClientes;
    }

    public void añadirFinal(){
        colaClientes.add(new Cliente());
    }

    public void añadirPrincipio(){
        colaClientes.add(0, new Cliente());
    }
}
