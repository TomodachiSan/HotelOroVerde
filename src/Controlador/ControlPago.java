package Controlador;

import Modelo.vhabitacion;
import Modelo.vpago;
import Modelo.valquiler;
import DAO.conexion;
import DAO.fconsumo;
import DAO.fhabitacion;
import DAO.fpago;
import DAO.fproducto;
import DAO.falquiler;
import DAO.funciones;
import Vistas.frmPago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
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

public class ControlPago implements ActionListener, MouseListener {

    private Connection connection = new conexion().conectar();
    private String accion = "guardar";
    public int totregistros = 0;
    public static String idalquiler;
    public static String cliente;
    public static String idhabitacion;
    public static String habitacion;
    public static Double totalalquiler;

    private frmPago vista;

    public ControlPago(frmPago vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnImprimir.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        mostrar(idalquiler);
        inhabilitarTodo();
        inhabilitar();
        this.vista.txtidalquiler.setText(idalquiler);
        this.vista.txtidhabitacion.setText(idhabitacion);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnImprimir) {
            btnImprimirActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnnuevo) {
            btnnuevoActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed(arg0);
        }
    }

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setPreferredWidth(0);
    }

    void ocultar_columnasconsumo() {
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.vista.tablalistadoconsumo.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(1).setMinWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(1).setPreferredWidth(0);

        this.vista.tablalistadoconsumo.getColumnModel().getColumn(2).setMaxWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(2).setMinWidth(0);
        this.vista.tablalistadoconsumo.getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtidpago.setVisible(false);

        this.vista.txtidalquiler.setVisible(false);
        this.vista.txtcliente.setEnabled(false);
        this.vista.txtpago_Inicial.setEnabled(false);
        this.vista.txtigv.setEnabled(false);
        this.vista.txttotal_pago.setEnabled(false);
        this.vista.txttotalalquiler.setEnabled(false);
        this.vista.txttotalconsumo.setEnabled(false);
        this.vista.txtidhabitacion.setVisible(false);
        this.vista.txthabitacion.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btnImprimir.setEnabled(false);

        this.vista.txtidpago.setText("");

        this.vista.txtcliente.setText("");
        this.vista.txtpago_Inicial.setText("");
        this.vista.txtigv.setText("");
        this.vista.txttotal_pago.setText("");
        this.vista.txttotalalquiler.setText("");
        this.vista.txttotalconsumo.setText("");
        this.vista.txthabitacion.setText("");

    }

    void inhabilitarBotones() {
        if (!this.vista.txtidpago.getText().equals("")) {
            this.vista.txtidpago.setVisible(false);

            this.vista.txtidalquiler.setVisible(false);
            this.vista.txtcliente.setEnabled(false);
            this.vista.txtpago_Inicial.setEnabled(false);
            this.vista.txtigv.setEnabled(false);
            this.vista.txttotal_pago.setEnabled(false);
            this.vista.txttotalalquiler.setEnabled(false);
            this.vista.txttotalconsumo.setEnabled(false);
            this.vista.txtidhabitacion.setVisible(false);
            this.vista.txthabitacion.setEnabled(false);
            this.vista.btnguardar.setEnabled(false);
            this.vista.btncancelar.setEnabled(false);
            this.vista.btnnuevo.setEnabled(false);
        }
    }

    void habilitar() {
        this.vista.txtidpago.setVisible(false);

        this.vista.txtidalquiler.setVisible(false);
        this.vista.txtcliente.setEnabled(false);
        this.vista.txtpago_Inicial.setEnabled(false);
        this.vista.txtigv.setEnabled(false);
        this.vista.txttotal_pago.setEnabled(false);
        this.vista.txttotalalquiler.setEnabled(false);
        this.vista.txttotalconsumo.setEnabled(false);
        this.vista.txtidhabitacion.setVisible(false);
        this.vista.txthabitacion.setEnabled(false);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        this.vista.btnImprimir.setEnabled(false);
        this.vista.txtidpago.setText("");

        this.vista.txtcliente.setText(cliente);
        this.vista.txthabitacion.setText(habitacion);

        this.vista.txttotalalquiler.setText(Double.toString(totalalquiler));

        fconsumo func = new fconsumo();
        func.mostrar(idalquiler);
        this.vista.txttotalconsumo.setText(Double.toString(func.totalconsumo));
        Double pago_inicial = totalalquiler + func.totalconsumo;
        Double pago_igv = pago_inicial * 0.18;
        Double pago_tot = pago_inicial + pago_igv;
        this.vista.txtpago_Inicial.setText(Double.toString(pago_inicial));
        this.vista.txtigv.setText(Double.toString(pago_igv));
        this.vista.txttotal_pago.setText(Double.toString(pago_tot));
    }

    void inhabilitarTodo() {
        if (totregistros > 0) {
            this.vista.btnguardar.setEnabled(false);
            this.vista.btncancelar.setEnabled(false);
            this.vista.btnImprimir.setEnabled(false);
            this.vista.btnnuevo.setEnabled(false);
        }
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fpago func = new fpago();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Pagos " + Integer.toString(func.totalregistros));
            totregistros = func.totalregistros;

            //Mostrar los datos de los consumos
            fconsumo func2 = new fconsumo();
            modelo = func2.mostrar(buscar);
            this.vista.tablalistadoconsumo.setModel(modelo);
            ocultar_columnasconsumo();

            this.vista.lbltotalregistrosconsumo.setText("Total Consumos " + func2.totalregistros);
            this.vista.lbltotalconsumo.setText("Consumo Total: S/." + func2.totalconsumo);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void txtidalquilerActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtidalquiler.transferFocus();
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {

        vpago dts = new vpago();
        fpago func = new fpago();

        dts.setIdalquiler(Integer.parseInt(this.vista.txtidalquiler.getText()));
        dts.setPago_inicial(Double.parseDouble(this.vista.txtpago_Inicial.getText()));
        dts.setIgv(Double.parseDouble(this.vista.txtigv.getText()));
        dts.setTotal_pago(Double.parseDouble(this.vista.txttotal_pago.getText()));

        if (accion.equals("guardar")) {
            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(null, " El pago por S/. " + this.vista.txttotal_pago.getText()
                        + " del Sr(a) " + this.vista.txtcliente.getText() + " ha sido registrado con exito");

                mostrar(idalquiler);
                inhabilitar();
                inhabilitarTodo();
                this.vista.txtidpago.setText(func.UltimoIdPago().toString());
                inhabilitarBotones();
                //Desocupar la Habitación
                fhabitacion func2 = new fhabitacion();
                vhabitacion dts2 = new vhabitacion();

                dts2.setIdhabitacion(Integer.parseInt(this.vista.txtidhabitacion.getText()));
                func2.desocupar(dts2);

            }

        }
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidpago.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.btnImprimir.setEnabled(true);
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        fpago func = new fpago();
        int c = func.CountConsumos(Integer.parseInt(this.vista.txtidpago.getText()));
        String direccion = "";
        // REALIZAR UNA NUEVO REPORTE SIN CONSUMOS EN rptComprobante.jrxml
        if (c == 0) {
            direccion = "/src/Reportes/rptComprobante.jrxml";
        } else {
            direccion = "/src/Reportes/rptComprobanteConsumo.jrxml";
        }
        if (!this.vista.txtidpago.getText().equals("")) {
            Map p = new HashMap();

            p.put("idpago", this.vista.txtidpago.getText());

            JasperReport report;
            JasperPrint print;
            try {

                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + direccion);
                print = JasperFillManager.fillReport(report, p, connection);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Comprobante");
                view.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, " Registre su Pago Total ");

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
