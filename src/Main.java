import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //Crea una instancia de la clase Browser
        App b = new App();
        //Titulo del JFrame
        b.setTitle("Process GPT");
        //Tamanio del JFrame
        b.setSize(700, 700);
        //Hace visible el browser (JFrame)
        b.setVisible(true);
        //Indica que la aplicación debe finalizar completamente cuando se cierra la ventana JFrame
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}