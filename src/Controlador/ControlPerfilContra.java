package Controlador;

import DAO.*;
import Modelo.*;
import Vistas.frminicio;
import Vistas.frmperfilcontra;
import Vistas.frmperfildatos;
import Vistas.frmusuariologin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlPerfilContra implements ActionListener {

    private frmperfilcontra vista;
    private DefaultTableModel modelo;

    public ControlPerfilContra(frmperfilcontra vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnguardar.addActionListener(this);
        this.vista.txtidpersona.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnguardar) {
            btnguardar();
        }

    }

    private void btnguardar() {

        if (vista.txtContraActual.getText().trim().length() == 0 || vista.txtContraNueva.getText().trim().length() == 0
                || vista.txtConfirmarContra.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            String contraact = this.vista.txtContraActual.getText();
            String contranueva = this.vista.txtContraNueva.getText();
            String contraconf = this.vista.txtConfirmarContra.getText();
            Integer id = Integer.parseInt(this.vista.txtidpersona.getText());
            vtrabajador dts = new vtrabajador();
            flogin func = new flogin();
            String contra = func.mostrarContraseña(id);

            if (contra.equalsIgnoreCase(contraact)) {
                if (contranueva.equalsIgnoreCase(contraconf)) {
                    if (func.editarContraseña(id, contranueva)) {
                        JOptionPane.showMessageDialog(null, "La contraseña fue cambiada satisfactoriamente");
                        LimpiarEntradas();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña nueva y la contraseña confirmada no coinciden");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña actual ingresada es incorrecta");
            }
        }

    }

    public void LimpiarEntradas() {
        this.vista.txtContraActual.setText("");
        this.vista.txtContraNueva.setText("");
        this.vista.txtConfirmarContra.setText("");
    }

}
