package inventario.controlador.producto;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;


public interface IProducto
{
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void cargar(JTable tblRegistros);
    public void insertar();
    public void menu();
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros, JCheckBox chActivar);
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar);
    public void generarReporte();
    public void buscarProducto( JTextField buscar, JTable tablaProducto);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto);
}
