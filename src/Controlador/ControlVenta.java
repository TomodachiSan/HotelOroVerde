package Controlador;

import DAO.conexion;
import DAO.funciones;
import DAO.fventa;
import Modelo.vventa;
import Vistas.frmDetalle_Venta;
import Vistas.frmVenta;
import Vistas.frminicio;
import Vistas.frmvistacliente;
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

public class ControlVenta implements ActionListener, MouseListener {

    private Connection connection = new conexion().conectar();

    public frmVenta vista;

    public ControlVenta(frmVenta vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.btnComprobante.addActionListener(this);
        mostrar("");
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == vista.btnComprobante) {
            btnComprobanteActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnsalir) {
            btnsalirActionPerformed(arg0);
        }
    }

    public Integer idventa;

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.btnComprobante.setEnabled(false);
    }

    void habilitar() {
        this.vista.btnComprobante.setEnabled(true);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fventa func = new fventa();
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
        habilitar();

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());
        idventa = Integer.parseInt(this.vista.tablalistado.getValueAt(fila, 0).toString());
    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void btnComprobanteActionPerformed(java.awt.event.ActionEvent evt) {
        Map p = new HashMap();

        p.put("idpago", idventa.toString());

        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptComprobanteVenta2.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Comprobante Venta");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
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
