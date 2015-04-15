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
public class Minas {
    public static void main(String b[]){
        Scanner entrada = new Scanner(System.in);
        Campo campo;
        int i = 0;
        int N,M;
        
        do{
            N = entrada.nextInt();
            M = entrada.nextInt();
            
            
            campo = new Campo();
            campo.leerCuadrosDeEntrada(N,M);
            System.out.println("Campo minado #" + ++i +":");
            System.out.println(campo);            
        }while(N!= 0 || M!=0);
    }    
}
