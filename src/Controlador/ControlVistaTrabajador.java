package Controlador;

import DAO.ftrabajador;
import Vistas.FrmConsultaAlquilerTrabajador;
import Vistas.FrmVistaTrabajador;
import Vistas.frmlogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlVistaTrabajador implements ActionListener, MouseListener {
    
    private FrmVistaTrabajador vista;
    public byte op;
    
    public ControlVistaTrabajador(FrmVistaTrabajador vista) {
        this.vista = vista;
        ComponentesInicio();
    }
    
    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        mostrar("");
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed();
        }
        if (arg0.getSource() == vista.btnsalir) {
            this.vista.dispose();
        }
    }
    
    public void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            ftrabajador func = new ftrabajador();
            modelo = func.mostrar(buscar);
            
            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"ERROR PUE:"+ e);
        }
    }
    
    private void btnbuscarActionPerformed() {
        
        mostrar(this.vista.txtbuscar.getText());
    }
    
    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(3).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(3).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(4).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(4).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(5).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(5).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(6).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(6).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(7).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(7).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        this.vista.tablalistado.getColumnModel().getColumn(8).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(8).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(8).setPreferredWidth(0);
        
    }
    
    private void tablalistadoMousePressed(MouseEvent evt) {
        if (op == 0) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();
                String cod;
                
                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                
                frmlogin.txttrabajador.setText(cod);
                this.vista.dispose();
                
            }            
        }else if (op == 1) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();
                String cod;
                String valor;
                
                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                valor = this.vista.tablalistado.getValueAt(fila, 1).toString() + " " + this.vista.tablalistado.getValueAt(fila, 2).toString();
                
                FrmConsultaAlquilerTrabajador.txtidtrabajador.setText(cod);
                FrmConsultaAlquilerTrabajador.txttrabajador.setText(valor);
                this.vista.dispose();
                
            }
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
