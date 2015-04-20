package hipermercado;

public class Caja extends Thread {

    private final Cola cola;
    private final Contabilidad contabilidad;
    private boolean cajaAbierta;
    private long idCaja;
    private Cliente clienteActual;
    private Double cuentaCaja;

    public Caja(Cola cola, Contabilidad contabilidad) {
        this.cola = cola;
        this.contabilidad = contabilidad;
        idCaja = getId();
        cajaAbierta = true;
        cuentaCaja = 0.;
    }

    public void atenderCliente() throws InterruptedException {
        System.out.println("----> La caja " + idCaja + " llama a un cliente para atenderle.");
        clienteActual = llamarCliente();
        if (clienteActual == null) {
            return;
        }
        Thread.sleep((long) (damePrecio(clienteActual) / 10) * 1000);
        if (!cajaAbierta) {
            cola.añadirPrincipio(clienteActual);
            return;
        }
        cuentaCaja += clienteActual.damePrecioCarro();
    }

    private Cliente llamarCliente() throws InterruptedException {
        Cliente cliente = cola.sacar();
        if (cliente == null){
            cerrarCaja();
        }
        return cliente;
    }

    private void cerrarCaja() {
        cajaAbierta = false;
        añadirSaldoContabilidad();
    }

    private double damePrecio(Cliente clienteActual) throws InterruptedException {
        return clienteActual.damePrecioCarro();
    }

    private void añadirSaldoContabilidad(){
        contabilidad.añadeSaldo(cuentaCaja);
        System.out.println("····· La caja " + idCaja + " añadió a la contabilidad " + cuentaCaja);
        System.out.println(":::::: La contabilidad actual es: " + contabilidad.dameSaldo());
    }

    @Override
    public void run() {
        while (cajaAbierta) {
            try {
                atenderCliente();
                if (clienteActual == null) return;
                System.out.println("<---- La caja " + idCaja + " terminó de atender al cliente: " + clienteActual.dameNombre());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
