package hipermercado;

public class Caja extends Thread{

    private final Cola cola;
    private final Contabilidad contabilidad;
    private boolean estaAbierta;

    public Caja(Cola cola, Contabilidad contabilidad) {
        this.cola = cola;
        this.contabilidad = contabilidad;
        estaAbierta = true;
    }

    public void atenderCliente() throws InterruptedException {
        Cliente clienteActual = llamarCliente();
        if (clienteActual == null || estaAbierta) return;
        sleep((long) (damePrecio(clienteActual) / 10));
        if (!estaAbierta) {
            cola.añadirPrincipio(clienteActual);
            return;
        }
        contabilidad.añadeSaldo(damePrecio(clienteActual));
    }

    private Cliente llamarCliente() throws InterruptedException {
        Cliente cliente = cola.sacar();
        if (cliente == null) estaAbierta = false;
        return cliente;
    }

    private double damePrecio(Cliente clienteActual) throws InterruptedException {
        return clienteActual.damePrecioCarro();
    }

}
