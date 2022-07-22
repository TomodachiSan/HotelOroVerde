package Controlador;

import DAO.conexion;
import DAO.fhabitacion;
import Modelo.vhabitacion;
import Vistas.frmhabitacion;
import Vistas.frminicio;
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

public class ControlHabitacion implements ActionListener, MouseListener {

    private frmhabitacion vista;
    private String accion = "guardar";

    public ControlHabitacion(frmhabitacion vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        mostrar("");
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnReporte) {
            btnReporteActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnnuevo) {
            btnnuevoActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnsalir) {
            btnsalirActionPerformed(arg0);
        }
    }

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtidhabitacion.setVisible(false);
        this.vista.cbopiso.setEnabled(false);
        this.vista.txtnumero.setEnabled(false);
        this.vista.txtdescripcion.setEnabled(false);
        this.vista.txtcaracteristicas.setEnabled(false);
        this.vista.txtprecio_diario.setEnabled(false);
        this.vista.cboestado.setEnabled(false);
        this.vista.cbotipo_habitacion.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.txtidhabitacion.setText("");
        this.vista.txtprecio_diario.setText("");
        this.vista.txtcaracteristicas.setText("");
        this.vista.txtdescripcion.setText("");
        this.vista.txtnumero.setText("");

    }

    void habilitar() {
        this.vista.txtidhabitacion.setVisible(false);

        this.vista.cbopiso.setEnabled(true);
        this.vista.txtnumero.setEnabled(true);
        this.vista.txtdescripcion.setEnabled(true);
        this.vista.txtcaracteristicas.setEnabled(true);
        this.vista.txtprecio_diario.setEnabled(true);
        this.vista.cboestado.setEnabled(true);
        this.vista.cbotipo_habitacion.setEnabled(true);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        this.vista.txtidhabitacion.setText("");
        this.vista.txtprecio_diario.setText("");
        this.vista.txtcaracteristicas.setText("");
        this.vista.txtdescripcion.setText("");
        this.vista.txtnumero.setText("");
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fhabitacion func = new fhabitacion();
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

        if (vista.txtnumero.getText().trim().length() == 0 || vista.txtdescripcion.getText().trim().length() == 0
                || vista.txtcaracteristicas.getText().trim().length() == 0 || vista.txtprecio_diario.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean rspta1 = EsDouble(vista.txtprecio_diario.getText());
            if (rspta1 == false) {
                JOptionPane.showMessageDialog(null, "El campo precio diario esta rellenado incorrectamente",
                        "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
            } else {
                vhabitacion dts = new vhabitacion();
                fhabitacion func = new fhabitacion();

                dts.setNumero(this.vista.txtnumero.getText());

                int seleccionado = this.vista.cbopiso.getSelectedIndex();
                dts.setPiso((String) this.vista.cbopiso.getItemAt(seleccionado));

                dts.setDescripcion(this.vista.txtdescripcion.getText());
                dts.setCaracteristicas(this.vista.txtcaracteristicas.getText());

                dts.setPrecio_diario(Double.parseDouble(this.vista.txtprecio_diario.getText()));

                seleccionado = this.vista.cboestado.getSelectedIndex();
                dts.setEstado((String) this.vista.cboestado.getItemAt(seleccionado));

                seleccionado = this.vista.cbotipo_habitacion.getSelectedIndex();
                dts.setTipo_habitacion((String) this.vista.cbotipo_habitacion.getItemAt(seleccionado));

                if (accion.equals("guardar")) {
                    if (func.insertar(dts)) {
                        JOptionPane.showMessageDialog(null, "La habitación fue registrada satisfactoriamente");
                        mostrar("");
                        inhabilitar();

                    }

                } else if (accion.equals("editar")) {
                    dts.setIdhabitacion(Integer.parseInt(this.vista.txtidhabitacion.getText()));

                    if (func.editar(dts)) {
                        JOptionPane.showMessageDialog(null, "La habitación fue Editada satisfactoriamente");
                        mostrar("");
                        inhabilitar();
                    }
                }
            }
        }

    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void txtnumeroActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtnumero.transferFocus();
    }

    private void cbopisoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.cbopiso.transferFocus();
    }

    private void txtprecio_diarioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtprecio_diario.transferFocus();
    }

    private void cboestadoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.cboestado.transferFocus();
    }

    private void cbotipo_habitacionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.transferFocus();
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        this.vista.btnguardar.setText("Editar");
        habilitar();
        accion = "editar";

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidhabitacion.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtnumero.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());

        this.vista.cbopiso.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 2).toString());
        this.vista.txtdescripcion.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
        this.vista.txtcaracteristicas.setText(this.vista.tablalistado.getValueAt(fila, 4).toString());
        this.vista.txtprecio_diario.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());

        this.vista.cboestado.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 6).toString());
        this.vista.cbotipo_habitacion.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 7).toString());

    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();

    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();
    }

    private Connection connection = new conexion().conectar();

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptHabitaciones.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Habitaciones");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
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
        if (e.getSource() == vista.tablalistado) {
            tablalistadoMouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
