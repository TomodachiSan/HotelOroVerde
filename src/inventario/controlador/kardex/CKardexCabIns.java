package inventario.controlador.kardex;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import inventario.modelo.Almacen;
import inventario.modelo.KardexCab;
import inventario.modelo.Producto;
import inventario.vista.kardex.UIKardexCabIns;



public class CKardexCabIns implements IKardexCabIns
{
    private UIKardexCabIns ventana;
    ArrayList<ArrayList<String>> productos;
    ArrayList<ArrayList<String>> almacenes;
    
    public CKardexCabIns()
    {
        productos = Producto.getActivos();
        almacenes = Almacen.getActivos();
        ventana = new UIKardexCabIns(this);
    }
    
    @Override
    public void cancelar()
    {
        new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(productos.get(cbxProNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom)
    {
        txtAlmCod.setText(almacenes.get(cbxAlmNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod)
    {
        KardexCab kc = new KardexCab(txtProCod.getText(), txtAlmCod.getText(), "0", "0", "0", "1");
        String err = kc.insertar();
        
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
            new CKardex();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom)
    {
        for(int i = 0; i < productos.size(); i++)
        {
            cbxProNom.insertItemAt(productos.get(i).get(1), i);
        }
        for(int i = 0; i < almacenes.size(); i++)
        {
            cbxAlmNom.insertItemAt(almacenes.get(i).get(1), i);
        }
    }
}
