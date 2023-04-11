import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class App extends JFrame{
    private JTabbedPane tabbedPane1;
    private JTextField a1AutorTextField;
    private JTextArea nombreAndreiFloresCedulaTextArea;
    private JTextField a2AutorTextField;
    private JTextArea nombreAlexeyRuedaCedulaTextArea;
    private JButton btndatos;
    private JTextArea textAreaDatos;
    private JButton insertarDatosButton;
    private JTextArea textAreaDatosQuemados;
    private JTextField textFieldId;
    private JTextField textFieldCedula;
    private JTextField textFieldTiempo;
    private JButton insertarDatos;
    private JTextField textFieldQuantum;
    private JButton btnQuantum;
    private JButton btnEjecutar;
    private JTextArea textArea1;
    private JButton mostrarHistorialButton;
    private JButton consultarUltimoPasoButton;
    private JButton eliminarHistorialButton;
    private JTextArea textAreaHistorial;

    Roundrobin r = new Roundrobin();

    public App() {

        //Establece el panel que se mostrará en una ventana JFrame
        setContentPane(tabbedPane1);

        btndatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirDatosCola();
            }
        });


        insertarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirDatosQuemadosCola();
            }
        });

        insertarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarDtos();
            }
        });

        btnQuantum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarQuantum();
            }
        });

        btnEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarProceso();
            }
        });

        mostrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });

        consultarUltimoPasoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ultimoElementoStack();
            }
        });

        eliminarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarHIstorial();
            }
        });
    }

    private void insertarDtos(){
        if (textFieldId.getText().matches("[a-zA-Z0-9]+") && !textFieldId.getText().isBlank() ){

            if (textFieldCedula.getText().matches("[0-9]*") && !textFieldCedula.getText().isBlank() && textFieldCedula.getText().length() == 10){

                if (textFieldTiempo.getText().matches("[0-9]*") && !textFieldTiempo.getText().isBlank()){

                    r.getProcesos().offer(new Proceso(textFieldId.getText(), Integer.parseInt(textFieldCedula.getText()), Integer.parseInt(textFieldTiempo.getText()
                    )));
                    JOptionPane.showMessageDialog(null, "Proceso agregado con exito");

                }else{
                    JOptionPane.showMessageDialog(null, "Ingrese solo numeros.");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Ingrese 10 numeros");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Ingrese solo caracteres o numeros");
        }
    }

    private void ejecutarProceso(){
        String text = r.proceso();
        if (!text.isEmpty()){
            textArea1.setText(text);
            insertarDatosButton.setEnabled(true);
            textAreaDatosQuemados.setText("");
        }else{
            textArea1.setText("No existen datos");
        }

    }

    private void eliminarHIstorial(){
        if (!r.getHistorial().isEmpty()){
            r.getHistorial().clear();
            textAreaHistorial.setText("");
            JOptionPane.showMessageDialog(null, "Se ha borrado el historial con exito");
        }else{
            textAreaHistorial.setText("Aun no existe un historial");
        }
    }

    private void ultimoElementoStack(){
        if (!r.getHistorial().isEmpty()){
            textAreaHistorial.setText("Id: " + r.getHistorial().peek().getIdProceso() + "\nCedula: " + r.getHistorial().peek().getCedula() + "\n\n");
        }else{
            textAreaHistorial.setText("Aun no existe un historial");
        }
    }

    private void mostrarHistorial(){
        if (!r.getHistorial().isEmpty()){
            textAreaHistorial.setText("");
            ListIterator<Proceso> iterador = r.getHistorial().listIterator(r.getHistorial().size());
            while (iterador.hasPrevious()) {
                Proceso el = iterador.previous();
                textAreaHistorial.append("Id: " + el.getIdProceso() + "\nCedula: " + el.getCedula() + "\n\n");
            }
        }else{
            textAreaHistorial.setText("Aun no existe un historial");
        }
    }

    private void actualizarQuantum(){
        if (textFieldQuantum.getText().matches("[0-9]*") && !textFieldQuantum.getText().isBlank()){
            if (Integer.parseInt(textFieldQuantum.getText()) != r.getQuantum()){
                r.setQuantum(Integer.parseInt(textFieldQuantum.getText()));
                JOptionPane.showMessageDialog(null, "Quantum actualizado con exito");
            }else{
                JOptionPane.showMessageDialog(null, "El quantum ya tiene ese valor");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }

    }


    private void imprimirDatosQuemadosCola() {
        textAreaDatosQuemados.setText("Se han insertado los siguientes datos: \n\n");
        for (Proceso proceso : r.datosQuemadosCola()) {
            textAreaDatosQuemados.append(proceso.imprimirDatos());
        }
        insertarDatosButton.setEnabled(false);
    }

    public void imprimirDatosCola(){
        if (!r.getProcesos().isEmpty()){
            textAreaDatos.setText("");
            for (Proceso proceso : r.getProcesos()) {
                textAreaDatos.append(proceso.imprimirDatos());
            }
        }else{
            textAreaDatos.setText("Aun no hay datos.");
        }

    }
}
