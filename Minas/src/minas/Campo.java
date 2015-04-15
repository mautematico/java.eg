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
package minas;
import java.util.Scanner;

/**
 *
 * @author Mauricio Navarro Miranda <mauricio@navarromiranda.mx>
 */
public class Campo {
    /**
     * Notemos que K (minas adyacentes) es a lo más 8,
     * de manera que nos basta con 8 + 2 valores distintos
     * (uno extra para no-adyacentes a minas, y uno más para las minas)
     * 
     * Minas guardadas como '*'+'0'
     * No-adyacentes a minas guardados como 0
     * Adyacentes a minas guardados como K
     */
    private char[][] cuadros;
    
    /**
     * 
     * @param N la cantidad de filas del Campo
     * @param M la cantidad de columnas del campo
     * 
     */
    public  void leerCuadrosDeEntrada(int N, int M){
        if (N == 0 && M == 0)
            return;
        cuadros = new char[N][M];
        Scanner entrada = new Scanner(System.in);
        
        for (int i=0; i<N; i++){
             String linea = entrada.nextLine();
            for (int j=0; j<M; j++){
                if (linea.charAt(j) == '*'){
                    actualizarAdyacentes(i,j);
                }
            }
                
        }
    }
    /**
     * 
     * @param y entre 0 y M
     * @param x entre 0 y N
     * 
     */
    private void actualizarAdyacentes(int y, int x){
        for (int i=y-1; i<=y+1; i++){
            for(int j=x-1; j<=x+1; j++){
                if( (0 <= i && i< cuadros.length) && (0<=j && j < cuadros[0].length) ){
                    if(cuadros[i][j] != (char)('*'-'0'))
                        cuadros[i][j]++;
                }
            }
        }
        cuadros[y][x] = (char) ('*'-'0');
    }

    @Override
    public String toString() {
        if ( cuadros == null )
            return "";
        String string = new String();   
        for (char[] cuadro : cuadros) {
            for (int j = 0; j< cuadros[0].length; j++) {
                string += (char) (cuadro[j] + '0');
            }
            string+='\n';
        }
        return string;
    }
}
