import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Roundrobin {

    private Queue<Proceso> procesos;
    private int quantum;
    private Stack<Proceso> historial;

    public Roundrobin() {
        procesos = new LinkedList<Proceso>();
        quantum = 35;
        historial = new Stack<Proceso>();
    }

    public Queue<Proceso> getProcesos() {
        return procesos;
    }

    public Stack<Proceso> getHistorial() {
        return historial;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public Queue<Proceso> datosQuemadosCola() {

        Queue<Proceso> procesosDatosQuemados = new LinkedList<>();

        procesosDatosQuemados.offer(new Proceso("P1", 1717796121, 23));
        procesosDatosQuemados.offer(new Proceso("P2", 1717791120, 150));
        procesosDatosQuemados.offer(new Proceso("P3", 1727796124, 54));
        procesosDatosQuemados.offer(new Proceso("P4", 1717712135, 18));
        procesosDatosQuemados.offer(new Proceso("P5", 1717701144, 37));
        procesosDatosQuemados.offer(new Proceso("P6", 1717796337, 87));

        procesos.addAll(procesosDatosQuemados);
        return procesosDatosQuemados;
    }



    public String proceso(){
        String texto = "";
        int tTotal = 0;
        int conmutaciones = 0;
        while (!procesos.isEmpty()) {
            Proceso proceso = procesos.poll(); // sacar el primer proceso de la procesos

            if (proceso.getTiempo() <= quantum) {
                // el proceso se ejecuta completamente antes de que se acabe el quantum
                texto += "Tiempo " + tTotal + ": " + proceso.getIdProceso() +" entra a ejecucion con " + proceso.getTiempo() + "ms\n";
                tTotal += proceso.getTiempo();
                proceso.setTiempo(0);
                texto += "Tiempo " + tTotal + ": " + proceso.getIdProceso() +" termina su ejecucion\n";
                historial.push(proceso);
            } else {
                // el proceso se ejecuta parcialmente antes de que se acabe el quantum
                texto += "Tiempo " + tTotal + ": " + proceso.getIdProceso() +" entra a ejecucion con " + proceso.getTiempo() + "ms\n";
                tTotal += quantum;
                proceso.setTiempo(proceso.getTiempo() - quantum);
                procesos.offer(proceso); // colocar el proceso al final de la procesos
                // contar el número de conmutaciones
                if (procesos.size()>1){
                    texto += "Tiempo " + tTotal + ": " + proceso.getIdProceso() +" se conmuta. Pendiente por ejecutar " + proceso.getTiempo() + "ms\n";
                    conmutaciones++;
                }else{
                    texto += "Tiempo " + tTotal + ": " + proceso.getIdProceso() + " continua ejecutandose\n";
                }

            }

            // si la procesos está vacía, se terminó la ejecución de todos los procesos
            if (procesos.isEmpty()) {
                texto += "\nESTADÍSTICAS GENERADAS:\n";
                texto += "Total tiempo de ejecución de todos los procesos: " + tTotal + " ms.\n";
                texto += "Total de conmutaciones: " + conmutaciones + "\n\n";
            }
        }
        return texto;
    }
}
