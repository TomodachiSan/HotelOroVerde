package Controlador;

import DAO.conexion;
import DAO.fproducto;
import Modelo.vproducto;
import Vistas.frmproducto;
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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ControlProducto implements ActionListener, MouseListener {

    private Connection connection = new conexion().conectar();

    private frmproducto vista;

    public ControlProducto(frmproducto vista) {
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
        if (arg0.getSource() == vista.btneliminar) {
            btneliminarActionPerformed(arg0);
        }
    }
    private String accion = "guardar";

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtidproducto.setVisible(false);
        this.vista.txtestado.setVisible(false);
        this.vista.txtnombre.setEnabled(false);
        this.vista.txtdescripcion.setEnabled(false);
        this.vista.txtprecio_venta.setEnabled(false);
        this.vista.txtStock.setEnabled(false);
        this.vista.cbounidad_medida.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btneliminar.setEnabled(false);
        this.vista.txtidproducto.setText("");
        this.vista.txtestado.setText("");
        this.vista.txtprecio_venta.setText("");
        this.vista.txtStock.setText("");
        this.vista.txtnombre.setText("");
        this.vista.txtdescripcion.setText("");

    }

    void habilitar() {
        this.vista.txtidproducto.setVisible(false);
        this.vista.txtestado.setVisible(false);
        this.vista.txtnombre.setEnabled(true);
        this.vista.txtdescripcion.setEnabled(true);

        this.vista.txtprecio_venta.setEnabled(true);
        this.vista.txtStock.setEnabled(true);
        this.vista.cbounidad_medida.setEnabled(true);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        this.vista.btneliminar.setEnabled(true);
        this.vista.txtidproducto.setText("");
        this.vista.txtestado.setText("");
        this.vista.txtprecio_venta.setText("");
        this.vista.txtStock.setText("");
        this.vista.txtnombre.setText("");
        this.vista.txtdescripcion.setText("");

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fproducto func = new fproducto();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        this.vista.btnguardar.setText("Editar");
        habilitar();
        this.vista.btneliminar.setEnabled(true);
        accion = "editar";

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidproducto.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtestado.setText(this.vista.tablalistado.getValueAt(fila, 6).toString());
        this.vista.txtnombre.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());

        this.vista.txtdescripcion.setText(this.vista.tablalistado.getValueAt(fila, 2).toString());
        this.vista.cbounidad_medida.setSelectedItem(this.vista.tablalistado.getValueAt(fila, 3).toString());
        this.vista.txtprecio_venta.setText(this.vista.tablalistado.getValueAt(fila, 4).toString());
        this.vista.txtStock.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());

    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (!this.vista.txtidproducto.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Producto?", "Confirmar", 2);

            if (confirmacion == 0) {
                fproducto func = new fproducto();
                vproducto dts = new vproducto();

                dts.setIdproducto(Integer.parseInt(this.vista.txtidproducto.getText()));
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

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtnombre.transferFocus();
    }

    private void txtprecio_ventaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtprecio_venta.transferFocus();
    }

    private void cbounidad_medidaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.cbounidad_medida.transferFocus();
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {

        if (vista.txtnombre.getText().trim().length() == 0 || vista.txtdescripcion.getText().trim().length() == 0
                || vista.txtprecio_venta.getText().trim().length() == 0 || vista.txtStock.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean rspta1 = EsDouble(vista.txtprecio_venta.getText());
            boolean rspta2 = EsInteger(vista.txtStock.getText());
            if (rspta1 == false || rspta2 == false) {
                JOptionPane.showMessageDialog(null, "El campo precio de venta o stock esta rellenado incorrectamente",
                        "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
            } else {
                vproducto dts = new vproducto();
                fproducto func = new fproducto();

                dts.setNombre(this.vista.txtnombre.getText());
                dts.setDescripcion(this.vista.txtdescripcion.getText());
                dts.setPrecio_venta(Double.parseDouble(this.vista.txtprecio_venta.getText()));
                dts.setStock(Integer.parseInt(this.vista.txtStock.getText()));

                int seleccionado = this.vista.cbounidad_medida.getSelectedIndex();
                dts.setUnidad_medida((String) this.vista.cbounidad_medida.getItemAt(seleccionado));

                if (accion.equals("guardar")) {
                    if (func.insertar(dts)) {
                        JOptionPane.showMessageDialog(null, "El Producto fue registrado satisfactoriamente");
                        mostrar("");
                        inhabilitar();

                    }

                } else if (accion.equals("editar")) {
                    dts.setIdproducto(Integer.parseInt(this.vista.txtidproducto.getText()));

                    if (func.editar(dts)) {
                        JOptionPane.showMessageDialog(null, "El producto fue editado satisfactoriamente");
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

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtStock.transferFocus();
    }

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptProductos.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Productos");
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
