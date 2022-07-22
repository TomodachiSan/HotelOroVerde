package Controlador;

import DAO.*;
import Modelo.*;
import Vistas.frminicio;
import Vistas.frmusuariologin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlUsuarioLogin implements ActionListener {

    private frmusuariologin vista;
    private DefaultTableModel modelo;

    public ControlUsuarioLogin(frmusuariologin vista) {
        this.vista = vista;
        this.vista.setTitle("Bienvenidos al sistema");
        this.vista.setLocationRelativeTo(null);
        this.vista.btningresar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.jScrollPane1.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btningresar) {
            btningresar();
        } else if (arg0.getSource() == vista.btnsalir) {
            System.exit(0);
        }
    }

    private void btningresar() {
        try {
            DefaultTableModel modelo;
            ftrabajador func = new ftrabajador();
            vtrabajador dts = new vtrabajador();

            dts.setLogin(this.vista.txtusuario.getText());
            dts.setPassword(this.vista.txtpassword.getText());

            modelo = func.login(dts.getLogin(), dts.getPassword());

            this.vista.tablalistado.setModel(modelo);

            if (func.totalregistros > 0) {
                this.vista.dispose();
                frminicio form = new frminicio();
                form.toFront();
                form.setVisible(true);
                frminicio.lblidpersona.setText(this.vista.tablalistado.getValueAt(0, 0).toString());
                frminicio.lblnombre.setText(this.vista.tablalistado.getValueAt(0, 1).toString());
                frminicio.lblapaterno.setText(this.vista.tablalistado.getValueAt(0, 2).toString());
                frminicio.lblamaterno.setText(this.vista.tablalistado.getValueAt(0, 3).toString());
                frminicio.lblacceso.setText(this.vista.tablalistado.getValueAt(0, 4).toString());
                if (!frminicio.lblacceso.getText().equals("Administrador")) {
                    frminicio.mnuarchivo.setEnabled(false);
                    frminicio.mnuconfiguraciones.setEnabled(false);
                }
                ControlInicio cInicio = new ControlInicio(form);
            } else {
                JOptionPane.showMessageDialog(null, "Acceso Denegado", "Acceso al Sistema", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
        }
    }

}
