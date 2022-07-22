package Controlador;

import DAO.conexion;
import Vistas.*;
import static Vistas.FrmConsultaAlquilerTrabajador.txtidtrabajador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ControlConsultaAlquilerTrabajador implements ActionListener {

    private FrmConsultaAlquilerTrabajador vista;
    private Connection connection = new conexion().conectar();

    public ControlConsultaAlquilerTrabajador(FrmConsultaAlquilerTrabajador vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnImprimir.addActionListener(this);
        this.vista.btnbuscacliente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    
        if (arg0.getSource() == vista.btnImprimir) {
            btnImprimirActionPerformed();
        }
        if (arg0.getSource() == vista.btnbuscacliente) {
            btnbuscaclienteActionPerformed();
        }

    }

    private void btnImprimirActionPerformed() {
        if (this.vista.dcFechaini.getDate() != null && this.vista.dcFechafin.getDate() != null && !txtidtrabajador.equals("")) {
            Map p = new HashMap();

            Date fecha_inicial = this.vista.dcFechaini.getDate();
            Date fecha_final = this.vista.dcFechafin.getDate();
            String idtrabajador = txtidtrabajador.getText();

            p.put("fecha_ini", fecha_inicial);
            p.put("fecha_fin", fecha_final);
            p.put("idtrabajador", idtrabajador);

            JasperReport report;
            JasperPrint print;
            try {

                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptPagosRealizados.jrxml");
                print = JasperFillManager.fillReport(report, p, connection);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Consulta de Pagos");
                view.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona las fechas y el trabajador");
        }

    }

    private void btnbuscaclienteActionPerformed() {
        FrmVistaTrabajador form = new FrmVistaTrabajador();
        form.toFront();
        form.setVisible(true);
        ControlVistaTrabajador control = new ControlVistaTrabajador(form);
        control.op = 1;
    }
}
