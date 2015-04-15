package hipermercado;

public class Caja extends Thread{

    private final Cola cola;
    private final Contabilidad contabilidad;
    private boolean abierta;

    public Caja(Cola cola, Contabilidad contabilidad) {
        this.cola = cola;
        this.contabilidad = contabilidad;
        abierta = true;
    }

    public void atenderCliente() throws InterruptedException {
        if (llamarCliente() == null || abierta == false) return;
        sleep((long) (damePrecioCliente() / 10));

        contabilidad.añadeSaldo(damePrecioCliente());
    }

    private Cliente llamarCliente() throws InterruptedException {
        Cliente cliente = cola.sacar();
        if (cliente == null) abierta = false;
        return cliente;
    }

    private double damePrecioCliente() throws InterruptedException {
        return llamarCliente().damePrecioCarro();
    }

}
