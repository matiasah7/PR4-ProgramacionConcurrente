package hipermercado;

public class Caja extends Thread {

    private final Cola cola;
    private final Contabilidad contabilidad;
    private boolean cajaAbierta;
    private long idCaja;
    private Cliente clienteActual;

    public Caja(Cola cola, Contabilidad contabilidad) {
        this.cola = cola;
        this.contabilidad = contabilidad;
        idCaja = getId();
        cajaAbierta = true;
    }

    public void atenderCliente() throws InterruptedException {
        System.out.println("La caja " + idCaja + "va a empezar a atender");
        clienteActual = llamarCliente();
        if (clienteActual == null) {
            cajaAbierta = false;
            return;
        }
        Thread.sleep((long) (damePrecio(clienteActual) / 10) * 1000);
        if (!cajaAbierta) {
            cola.añadirPrincipio(clienteActual);
            return;
        }

        System.out.println("La caja " + idCaja + " está atendiendo a " + clienteActual.dameNombre());
        contabilidad.añadeSaldo(damePrecio(clienteActual));
    }

    private Cliente llamarCliente() throws InterruptedException {
        Cliente cliente = cola.sacar();
        if (cliente == null) cajaAbierta = false;
        if(cliente != null)System.out.println("La caja" + idCaja + "llama a un cliente " + cliente.dameNombre());
        return cliente;
    }

    private double damePrecio(Cliente clienteActual) throws InterruptedException {
        return clienteActual.damePrecioCarro();
    }

    @Override
    public void run() {
        while (cajaAbierta) {
            try {
                atenderCliente();
                if (clienteActual == null) return;
                System.out.println("La caja " + idCaja + "termina de atender a " + clienteActual.dameNombre());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
