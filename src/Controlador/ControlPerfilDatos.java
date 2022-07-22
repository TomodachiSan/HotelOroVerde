package Controlador;

import DAO.*;
import Modelo.*;
import Vistas.frminicio;
import Vistas.frmperfildatos;
import Vistas.frmusuariologin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlPerfilDatos implements ActionListener {

    private frmperfildatos vista;
    private DefaultTableModel modelo;

    public ControlPerfilDatos(frmperfildatos vista) {
        this.vista = vista;
        ComponentesInicio();
        mostrar();
        inhabilitar();
    }

    public void ComponentesInicio() {
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnmodificar.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.txtidpersona.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnguardar) {
            btnguardar();
        }

        if (arg0.getSource() == vista.btnmodificar) {
            btnmodificarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }

    }

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();
    }

    private void btnguardar() {

        if (vista.txtnombre.getText().trim().length() == 0 || vista.txtapaterno.getText().trim().length() == 0
                || vista.txtamaterno.getText().trim().length() == 0 || vista.txtnum_documento.getText().trim().length() == 0
                || vista.txtdireccion.getText().trim().length() == 0 || vista.txttelefono.getText().trim().length() == 0
                || vista.txtemail.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean rspta1 = EsInteger(vista.txtnum_documento.getText());
            boolean rspta2 = EsInteger(vista.txttelefono.getText());
            if (rspta1 == false || rspta2 == false) {
                JOptionPane.showMessageDialog(null, "Los Campos de Nro Doc. o Telefono esta rellenado incorrectamente",
                        "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
            } else {
                vtrabajador dts = new vtrabajador();
                ftrabajador func = new ftrabajador();

                dts.setNombre(this.vista.txtnombre.getText());

                dts.setApaterno(this.vista.txtapaterno.getText());
                dts.setAmaterno(this.vista.txtamaterno.getText());

                int seleccionado = this.vista.cbotipo_documento.getSelectedIndex();
                dts.setTipo_documento((String) this.vista.cbotipo_documento.getItemAt(seleccionado));
                dts.setNum_documento(this.vista.txtnum_documento.getText());
                dts.setDireccion(this.vista.txtdireccion.getText());
                dts.setTelefono(this.vista.txttelefono.getText());
                dts.setEmail(this.vista.txtemail.getText());

                dts.setIdpersona(Integer.parseInt(this.vista.txtidpersona.getText()));

                if (func.editar(dts)) {
                    JOptionPane.showMessageDialog(null, "El Trabajador fue Editado satisfactoriamente");
                    mostrar();
                    inhabilitar();
                }
            }
        }

    }

    public void mostrar() {
        ftrabajador func = new ftrabajador();
        vpersona dts = func.recuperar(Integer.parseInt(this.vista.txtidpersona.getText()));

        this.vista.txtnombre.setText(dts.getNombre());

        this.vista.txtapaterno.setText(dts.getApaterno());
        this.vista.txtamaterno.setText(dts.getAmaterno());
        this.vista.cbotipo_documento.setSelectedItem(dts.getTipo_documento());
        this.vista.txtnum_documento.setText(dts.getNum_documento());
        this.vista.txtdireccion.setText(dts.getDireccion());
        this.vista.txttelefono.setText(dts.getTelefono());
        this.vista.txtemail.setText(dts.getEmail());
    }

    public boolean EsInteger(String num) {
        Integer intValue = null;
        try {
            intValue = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    void inhabilitar() {
        this.vista.txtidpersona.setVisible(false);

        this.vista.txtnombre.setEnabled(false);
        this.vista.txtapaterno.setEnabled(false);
        this.vista.txtamaterno.setEnabled(false);
        this.vista.cbotipo_documento.setEnabled(false);
        this.vista.txtnum_documento.setEnabled(false);
        this.vista.txtdireccion.setEnabled(false);
        this.vista.txttelefono.setEnabled(false);
        this.vista.txtemail.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
    }

    void habilitar() {
        this.vista.txtidpersona.setVisible(false);

        this.vista.txtnombre.setEnabled(true);
        this.vista.txtapaterno.setEnabled(true);
        this.vista.txtamaterno.setEnabled(true);
        this.vista.cbotipo_documento.setEnabled(true);
        this.vista.txtnum_documento.setEnabled(true);
        this.vista.txtdireccion.setEnabled(true);
        this.vista.txttelefono.setEnabled(true);
        this.vista.txtemail.setEnabled(true);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);

    }

}
