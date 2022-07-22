package Controlador;

import DAO.conexion;
import DAO.flogin;
import DAO.ftrabajador;
import Modelo.vtrabajador;
import Vistas.FrmVistaTrabajador;
import Vistas.frminicio;
import Vistas.frmlogin;
import Vistas.frmtrabajador;
import Vistas.frmvistaproducto;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ControlLogin implements ActionListener, MouseListener {

    private frmlogin vista;

    public ControlLogin(frmlogin vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnbuscartrabajador.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        mostrar("");
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnbuscartrabajador) {
            btnbuscarTrabajadorActionPerformed();
        }

        if (arg0.getSource() == vista.btnsalir) {
            this.vista.dispose();
        }
        if (arg0.getSource() == vista.btnnuevo) {
            btnnuevoActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btneliminar) {
            btneliminarActionPerformed(arg0);
        }
    }

    private String accion = "guardar";

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(4).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(4).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(4).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtidpersona.setVisible(false);
        this.vista.txtestado.setVisible(false);
        this.vista.txttrabajador.setEnabled(false);
        this.vista.txtsueldo.setEnabled(false);
        this.vista.cboacceso.setEnabled(false);
        this.vista.txtlogin.setEnabled(false);
        this.vista.txtpassword.setEnabled(false);
        this.vista.cboestado.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btneliminar.setEnabled(false);
        this.vista.btnbuscartrabajador.setEnabled(false);
        this.vista.txtsueldo.setText("");
        this.vista.txtlogin.setText("");
        this.vista.txtpassword.setText("");

    }

    void habilitar() {
        this.vista.txtidpersona.setVisible(false);
        this.vista.txtestado.setVisible(false);
        this.vista.txttrabajador.setEnabled(false);
        this.vista.txtsueldo.setEnabled(true);
        this.vista.cboacceso.setEnabled(true);
        this.vista.txtlogin.setEnabled(true);
        this.vista.txtpassword.setEnabled(true);
        this.vista.cboestado.setEnabled(true);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        this.vista.btneliminar.setEnabled(true);
        this.vista.btnbuscartrabajador.setEnabled(true);
        this.vista.txtsueldo.setText("");
        this.vista.txtlogin.setText("");
        this.vista.txtpassword.setText("");

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            flogin func = new flogin();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {

        if (vista.txttrabajador.getText().trim().length() == 0 || vista.txtlogin.getText().trim().length() == 0
                || vista.txtpassword.getText().trim().length() == 0 || vista.txtsueldo.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean rspta1 = EsDouble(vista.txtsueldo.getText());
            if (rspta1 == false) {
                JOptionPane.showMessageDialog(null, "El campo sueldo esta rellenado incorrectamente",
                        "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
            } else {
                vtrabajador dts = new vtrabajador();
                flogin func = new flogin();

                dts.setIdpersona(Integer.parseInt(this.vista.txttrabajador.getText()));

                dts.setSueldo(Double.parseDouble(this.vista.txtsueldo.getText()));
                int seleccionado = this.vista.cboacceso.getSelectedIndex();
                dts.setAcceso((String) this.vista.cboacceso.getItemAt(seleccionado));
                dts.setLogin(this.vista.txtlogin.getText());
                dts.setPassword(this.vista.txtpassword.getText());

                seleccionado = this.vista.cboestado.getSelectedIndex();
                dts.setEstado((String) this.vista.cboestado.getItemAt(seleccionado));

                if (accion.equals("guardar")) {
                    if (func.editar(dts)) {
                        JOptionPane.showMessageDialog(null, "el trabajador fue registrado satisfactoriamente");
                        mostrar("");
                        inhabilitar();

                    }

                } else if (accion.equals("editar")) {
                    dts.setIdpersona(Integer.parseInt(this.vista.txtidpersona.getText()));

                    if (func.editar(dts)) {
                        JOptionPane.showMessageDialog(null, "El Trabajador fue Editado satisfactoriamente");
                        mostrar("");
                        inhabilitar();
                    }
                }
            }
        }

    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        this.vista.btnguardar.setText("Editar");
        habilitar();
        this.vista.btneliminar.setEnabled(true);
        accion = "editar";

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidpersona.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtestado.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());
        this.vista.txttrabajador.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtsueldo.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());
        this.vista.cboacceso.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 2).toString());
        this.vista.txtlogin.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
        this.vista.txtpassword.setText(this.vista.tablalistado.getValueAt(fila, 4).toString());
        this.vista.cboestado.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 5).toString());

    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btnbuscarTrabajadorActionPerformed() {
        FrmVistaTrabajador form = new FrmVistaTrabajador();
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = frminicio.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlVistaTrabajador control = new ControlVistaTrabajador(form);
        control.op = 0;
    }

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (!this.vista.txtidpersona.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Trabajador?", "Confirmar", 2);

            if (confirmacion == 0) {
                flogin func = new flogin();
                vtrabajador dts = new vtrabajador();
                dts.setIdpersona(Integer.parseInt(this.vista.txtidpersona.getText()));
                dts.setEstado(this.vista.txtestado.getText());
                func.eliminar2(dts, dts.getEstado());
                mostrar("");
                inhabilitar();

            }

        }
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void txtsueldoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtsueldo.transferFocus();
    }

    private Connection connection = new conexion().conectar();

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {
        /*Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptTrabajadores.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Trabajadores");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }*/
    }

    public boolean EsDouble(String num) {
        try {
            double d = Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vista.tablalistado) {
            tablalistadoMouseClicked(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
