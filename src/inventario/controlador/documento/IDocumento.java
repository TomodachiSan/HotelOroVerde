package inventario.controlador.documento;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;



public interface IDocumento
{
    public void cargar(JTable tblRegistros);
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void menu();
    public void insertar();
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros, JCheckBox chActivar);
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar);
    public void buscarDocumento( JTextField buscar, JTable tablaDocumento);
    public void seleccionarFila(JTextField buscar, JTable tablaDocumento);
}
