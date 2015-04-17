/*
 * Copyright (C) 2015 Mauricio Navarro Miranda <mauricio@navarromiranda.mx>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
     * En el primer parámetro, recibe el nombre/ruta del fichero de entrada.
     * Por defecto es Archivo1.csv
     * @param args the command line arguments
     * @throws java.io.IOException
     * 
     * Lee, línea por linea, el fichero de entrada y procesa las fechas en la misma.
     * También, linea por linea, escribe las lineas procesadas a ArchivoFinal.csv
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
     * TODO:     Dar formato al promedio (incluir un decimal de precisión)
     * TODO:     Prevenir que suma se desborde. Hint: No usar suma, y sólo manipular (cuidadosamente) promedio.
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