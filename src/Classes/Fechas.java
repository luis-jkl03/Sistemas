
package Classes;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Fechas {
    
    public Fechas(){
        
    }
    
    public Date fecha(){
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Date nue = new Date(Fecha.toString());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        //return formatoFecha.format(fecha);
        return date;
    }
    
    public Date fechaConDate(long f){
        java.sql.Date date = new java.sql.Date(f);
        //Date nue = new Date(Fecha.toString());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        //return formatoFecha.format(fecha);
        return date;
    }
}
