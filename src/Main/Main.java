package Main;

import Controlador.ControlUsuarioLogin;
import Vistas.*;

public class Main {

    public static frmusuariologin f1;
    public static Controlador.ControlUsuarioLogin control;

    public static void main(String[] args) {
            try {

                    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");     
           
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vistas.frmusuariologin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vistas.frmusuariologin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vistas.frmusuariologin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vistas.frmusuariologin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        f1 = new frmusuariologin();

        f1.setVisible(true);
        f1.setLocationRelativeTo(null);
        control = new ControlUsuarioLogin(f1);
    }

}
