/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Navarro Miranda <mauricio@navarromiranda.mx>
 */
public class CSV {
           
    private static Long leidos =(long) 0;
    private static Long suma = (long) 0;
    private static Long promedio = (long) 0;
    

    /**
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String entrada;
        
        if(args.length>0){
            entrada=args[0];
        }
        else{
            entrada = "Archivo1.csv";
        }
            
        
        try {
            Fecha fecha = null, fechaAnterior = null;
            String linea;
            String fechaString;
            
            FileReader fr = new FileReader(entrada);
            BufferedReader br = new BufferedReader(fr);
            PrintWriter archivoFinal = new PrintWriter(new FileWriter("ArchivoFinal.csv"));            

            while((linea = br.readLine()) != null){
                String entradas[] = linea.split("\\|");
                
                int ID;
                ID = Integer.parseInt(entradas[0]);           
                
                fechaAnterior = fecha;
                fechaString = entradas[1];
                fecha = new Fecha(fechaString);
                
                String salida = linea + procesarFecha(fecha, fechaAnterior);
                archivoFinal.println(salida);
                leidos++;
             }
            archivoFinal.close();
        } catch (ParseException | FileNotFoundException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    /**
     * 
     * @param fecha la nueva fecha leida
     * @param fechaAnterior la fecha anterior, para calcular la diferencia
     * @return String que contiene |epoch|diferencia|promedio
     * TODO     Dar formato al promedio (incluir un decimal de precisión)
     */
    private static String procesarFecha(Fecha fecha, Fecha fechaAnterior){
        if(fechaAnterior == null){
            promedio = fecha.getEpochSegundos();
            suma = fecha.getEpochSegundos();
            return "|"+fecha.getEpochSegundos()+"|"+promedio+"|"+promedio;
        }
        suma+=fecha.getEpochSegundos();
        promedio = (suma/(leidos+1));
//        promedio = (promedio*( leidos/(leidos+1) )) + fecha.getEpochSegundos()/(leidos+1);
        int diferencia = (int) (fecha.getEpochSegundos() - fechaAnterior.getEpochSegundos());
        return "|"+fecha.getEpochSegundos()+"|"+diferencia+"|"+promedio;        
    }
    
}