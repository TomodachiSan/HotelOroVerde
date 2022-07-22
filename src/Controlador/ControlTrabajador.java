package Controlador;

import DAO.conexion;
import DAO.ftrabajador;
import Modelo.vtrabajador;
import Vistas.frmtrabajador;
import Vistas.frmvistaproducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControlTrabajador implements ActionListener, MouseListener {

    private frmtrabajador vista;

    public ControlTrabajador(frmtrabajador vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        mostrar("");
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
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
        if (arg0.getSource() == vista.btnReporte) {
            btnReporteActionPerformed(arg0);
        }
    }

    private String accion = "guardar";

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
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
        this.vista.txtidpersona.setText("");
        this.vista.txtnombre.setText("");
        this.vista.txtapaterno.setText("");
        this.vista.txtamaterno.setText("");
        this.vista.txtnum_documento.setText("");
        this.vista.txtdireccion.setText("");
        this.vista.txttelefono.setText("");
        this.vista.txtemail.setText("");
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
        this.vista.txtidpersona.setText("");
        this.vista.txtnombre.setText("");
        this.vista.txtapaterno.setText("");
        this.vista.txtamaterno.setText("");
        this.vista.txtnum_documento.setText("");
        this.vista.txtdireccion.setText("");
        this.vista.txttelefono.setText("");
        this.vista.txtemail.setText("");

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            ftrabajador func = new ftrabajador();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtnombre.transferFocus();
    }

    private void txtnum_documentoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtnum_documento.transferFocus();
    }

    private void cbotipo_documentoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.cbotipo_documento.transferFocus();
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {

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

                if (accion.equals("guardar")) {
                    if (func.insertar(dts)) {
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
        accion = "editar";

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidpersona.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtnombre.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());

        this.vista.txtapaterno.setText(this.vista.tablalistado.getValueAt(fila, 2).toString());
        this.vista.txtamaterno.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
        this.vista.cbotipo_documento.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 4).toString());
        this.vista.txtnum_documento.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());
        this.vista.txtdireccion.setText(this.vista.tablalistado.getValueAt(fila, 6).toString());
        this.vista.txttelefono.setText(this.vista.tablalistado.getValueAt(fila, 7).toString());
        this.vista.txtemail.setText(this.vista.tablalistado.getValueAt(fila, 8).toString());

    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {
        /*// TODO add your handling code here:
        if (!this.vista.txtidpersona.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Trabajador?", "Confirmar", 2);

            if (confirmacion == 0) {
                ftrabajador func = new ftrabajador();
                vtrabajador dts = new vtrabajador();

                dts.setIdpersona(Integer.parseInt(this.vista.txtidpersona.getText()));
                func.eliminar(dts);
                mostrar("");
                inhabilitar();

            }

        }*/
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void txtapaternoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtapaterno.transferFocus();
    }

    private void txtamaternoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtamaterno.transferFocus();
    }

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtdireccion.transferFocus();
    }

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txttelefono.transferFocus();
    }

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtemail.transferFocus();
    }

    private Connection connection = new conexion().conectar();

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {
        Map p = new HashMap();
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
        }

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
