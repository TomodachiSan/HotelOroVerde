
package DAO;

import java.util.Date;
import java.text.SimpleDateFormat;

public class funciones {
    
    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }
    
    public static String getFechaActual() {
    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
    return formateador.format(ahora);
}
}
