public class Proceso {
    private String idProceso;
    private int cedula;
    private int tiempo;

    public Proceso(String idProceso, int cedula, int tiempo) {
        this.idProceso = idProceso;
        this.cedula = cedula;
        this.tiempo = tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getIdProceso() {
        return idProceso;
    }

    public int getCedula() {
        return cedula;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String imprimirDatos() {
        return "Id: " + idProceso + "\nCedula: " + cedula + "\nTiempo: " + tiempo + "\n\n";
    }
}
