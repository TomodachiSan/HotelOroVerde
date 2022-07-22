package inventario.controlador.almacen;
  
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import inventario.modelo.Almacen;
import inventario.vista.almacen.UIAlmacenIns;




public class CAlmacenIns implements IAlmacenIns
{
    private UIAlmacenIns ventana;
    
    public CAlmacenIns()
    {
        ventana = new UIAlmacenIns(this);
    }
    
    @Override
    public void cargar(JTextField txtAlmCod)
    {
        txtAlmCod.setText(Almacen.sgteCodigo());
    }
    
    @Override
    public void aceptar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        Almacen a = new Almacen(txtAlmCod.getText(), txtAlmNom.getText(), txtAlmUbi.getText(), "1");
        String err = a.insertar();
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
            new CAlmacen();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void cancelar()
    {
        new CAlmacen();
        ventana.dispose();
    }
}
