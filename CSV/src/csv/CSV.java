/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Navarro Miranda <mauricio@navarromiranda.mx>
 */
public class CSV {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            Fecha fecha = null, fechaAnterior = null;
            String linea;
            String fechaString;
            
            Integer leidos;
            leidos = 0;
            Long promedio = (long) 0;
            

            FileReader fr = new FileReader("entrada.csv");
            BufferedReader br = new BufferedReader(fr);
            

            while((linea = br.readLine()) != null){
                String entradas[] = linea.split((String)"|");
                
                int ID;
                ID = Integer.parseInt(entradas[0]);           
                
                fechaAnterior = fecha;
                fechaString = entradas[1];
                fecha = new Fecha(fechaString);
                
                System.out.println(procesarFecha(fecha, fechaAnterior, leidos, promedio));
                leidos++;
             }
            

            
        } catch (ParseException | FileNotFoundException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private static String procesarFecha(Fecha fecha, Fecha fechaAnterior, Integer leidos, Long promedio){
        if(fechaAnterior == null){
            promedio = fecha.getEpochSegundos();
            return "|"+fecha.getEpochSegundos()+"|"+promedio+"|"+promedio;
        }
        promedio = (promedio*(leidos/(leidos+1)))                + fecha.getEpochSegundos()/(leidos+1);
        int diferencia = (int) (fecha.getEpochSegundos() - fechaAnterior.getEpochSegundos());
        
        return "|"+fecha.getEpochSegundos()+"|"+diferencia+"|"+promedio;
        
        
    }
    
}