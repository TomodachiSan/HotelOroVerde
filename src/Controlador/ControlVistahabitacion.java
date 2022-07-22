package Controlador;

import DAO.fhabitacion;
import Vistas.frmalquiler;
//import Vistas.frmreserva;
import Vistas.frmvistahabitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlVistahabitacion implements ActionListener, MouseListener {

    private frmvistahabitacion vista;

    public ControlVistahabitacion(frmvistahabitacion vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        mostrar("");
        //this.vista.setLocationRelativeTo(null);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.btnbuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
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
    
    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fhabitacion func = new fhabitacion();
            modelo = func.mostrarvista(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = this.vista.tablalistado.getSelectedRow();
            String cod;
            String valor;
            String costo;
            cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
            valor = this.vista.tablalistado.getValueAt(fila, 1).toString();
            costo = this.vista.tablalistado.getValueAt(fila, 5).toString();
            frmalquiler.txtidhabitacion.setText(cod);
            frmalquiler.txtnumero.setText(valor);
            frmalquiler.txtcosto_Dia.setText(costo);
            
            this.vista.dispose();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vista.tablalistado) {
            tablalistadoMousePressed(e);
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
