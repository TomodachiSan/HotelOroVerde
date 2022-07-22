package inventario.controlador.consulta;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import inventario.controlador.CKardexMenu;
import inventario.modelo.Consultas;
import inventario.modelo.Producto;
import inventario.modelo.Reporte;
import inventario.vista.consulta.UIExistencia;



public class CExistencia implements IExistencia
{
    private UIExistencia ventana;
    private ArrayList<ArrayList<String>> activosPro;
    private ArrayList<ArrayList<String>> resultado;
    private boolean consultaRealizada;
    
    public CExistencia()
    {
        consultaRealizada = false;
        resultado = new ArrayList<ArrayList<String>>();
        activosPro = Producto.getActivos();
        ventana = new UIExistencia(this);
    }

    @Override
    public void cargar(JComboBox cbxProNom)
    {
        for(int i = 0; i < activosPro.size(); i++)
        {
            cbxProNom.insertItemAt(activosPro.get(i).get(1), i);
        }
    }

    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(activosPro.get(cbxProNom.getSelectedIndex()).get(0));
    }

    @Override
    public void menu()
    {
        new CKardexMenu();
        ventana.dispose();
    }

    @Override
    public void consultar(JTable tblConsultas, JTextField txtProCod, JTextField txtTotal)
    {
        if(txtProCod.getText().length() != 0)
        {
            resultado = Consultas.existenciaProducto(txtProCod.getText());
            DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
            model.setRowCount(0);

            for(int i = 0; i < resultado.size(); i++)
            {
                model.addRow(new Object[]   {
                                                resultado.get(i).get(0),
                                                resultado.get(i).get(1),
                                                resultado.get(i).get(2)
                                            });
            }
            txtTotal.setText(Consultas.existenciaTotal(txtProCod.getText()));
            consultaRealizada = true;
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un producto", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void generarReporte(JTextField txtProCod)
    {
        if(consultaRealizada)
        {
            Producto p = Producto.buscar(txtProCod.getText());
            Reporte.generarReporte1(p, resultado);
        }
        else
            JOptionPane.showMessageDialog(null, "Realize primero una consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
}
