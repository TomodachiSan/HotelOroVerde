package inventario.controlador.almacen;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import inventario.modelo.Almacen;
import inventario.vista.almacen.UIAlmacenMod;



public class CAlmacenMod implements IAlmacenMod
{
    private UIAlmacenMod ventana;
    private Almacen a;
    
    public CAlmacenMod(String codigo)
    {
        a = Almacen.buscar(codigo);
        ventana = new UIAlmacenMod(this);
    }
    
    @Override
    public void cargar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        txtAlmCod.setText(a.getAlmCod());
        txtAlmNom.setText(a.getAlmNom());
        txtAlmUbi.setText(a.getAlmUbi());
    }
    
    @Override
    public void aceptar(JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        a.setAlmNom(txtAlmNom.getText());
        a.setAlmUbi(txtAlmUbi.getText());
        String err = a.modificar();
        
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
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
