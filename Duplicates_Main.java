/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab.pkg1.ayc;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/* Materia: Algoritmo y Complejidad
 * Curso: 3265
 * Nombre: Esteban Quintero Gonzalez
 * Codigo Estudiantil: 200158282
 * Workshop 3
 * 07-09-2022
 * Realizar un algoritmo que cuente la cantidad de duplicados que hay en un texto
 */
public class Lab1AyC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        create();	// creates a file
        write();	// writes data to the file
        read();		// reads data in the file
        store();	// stores data in an array
        return;
    }

    // implementations:
    private static void create() // creates a file
    {
        try {
            // defines the filename
            String fname = ("data.txt");
            // creates a new File object
            File f = new File(fname);

            String msg = "creating file `" + fname + "' ... ";
            System.out.println();
            System.out.printf("%s", msg);
            // creates the new file
            f.createNewFile();
            System.out.println("done");

        } catch (IOException err) {
            // complains if there is an Input/Output Error
            err.printStackTrace();
        }

        return;
    }

    private static void write() // writes data to a file
    {
        try {
            // defines the filename
            String filename = ("data.txt");
            // creates new PrintWriter object for writing file
            PrintWriter out = new PrintWriter(filename);
            Random rand = new Random();
            int numel = 256; // establece el numero de valores que tendra el archivo
            String msg = "writing %d numbers ... ";
            System.out.printf(msg, numel);
            // writes the integers in the range [0, nume1)
            for (int i = 0; i != numel; ++i) {
                out.printf("%d\n", rand.nextInt(numel)); // imprime numero aleatorios
            }

            System.out.println("done");

            System.out.printf("closing file ... ");
            out.close();	// closes the output stream
            System.out.println("done");
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    private static void read() // reads the file contents and prints them to the console
    {
        // defines the filename
        String filename = ("data.txt");
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            System.out.printf("\nnumbers in file:\n");

            int x;
            int count = 0;
            // reads integers in file until EOF
            while (in.hasNextInt()) {
                // reads  number and prints it
                x = in.nextInt();
                System.out.printf("%4d\n", x);
                ++count;
            }

            String msg = ("%d numbers have been read\n");
            System.out.printf(msg, count);

            in.close();	// closes the input stream

        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    private static void store() // stores the file contents into an array and prints the array
    {
        String filename = "data.txt";
        File f = new File(filename);

        try {
            Scanner in = new Scanner(f);

            // allocates list for storing the numbers in file
            int size = lines(filename);
            int[] list = new int[size];
            int[] inputs = new int[size]; // guarda las comparaciones que tiene un input
            long[] time = new long[size]; // guarda el tiempo demorado por cada input
            int[][] contn = new int[size][2]; // matriz con el numero de datos iguales
            int count = 0;
            int comparisons = 0; // contador de comparaciones
            // reads numbers into array
            while (in.hasNextInt()) {
                list[count] = in.nextInt();
                ++count;
            }
            for (int i = 0; i != size; ++i) {
                contn[i][0] = i; // llenar la matriz de los numeros que puede contener el texto
            }
            for (int i = 0; i != size; ++i) {
                time[i] = System.nanoTime();
                BuscarRep(list, contn, i, 0, comparisons, inputs);
                time[i] = System.nanoTime() - time[i]; // tiempo demorado en realizar el # de comparaciones
            }
            for (int i = 0; i != size; ++i) {
                    System.out.println(i+1 + " " + inputs[i] + " " + time[i]); // imprime el input, su numero de comparaciones y el tiempo tardado
            }
            in.close();	// closes the input stream

        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    private static int lines(String filename) // counts number of lines (or records) in a file
    {

        int count = 0;
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            // reads integers in file until EOF for counting
            while (in.hasNextInt()) {
                in.nextInt();
                ++count;
            }

            in.close();	// closes the input stream
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return count;
    }

    public static void BuscarRep(int[] vec, int[][] matriz, int i, int j, int count, int[] input) {
        count = count + 1; // cada que vuelve a la funcion recursiva cuenta 1 comparación mas
        if (vec[i] == matriz[j][0]) {
            matriz[j][1]++; // le suma 1 al dato buscado en la matriz
            input[i] = count; // guarda el numero de repeticiones para el input actual
            return;
        } else {
            BuscarRep(vec, matriz, i, j + 1, count, input); // vuelve a llamar la funcion moviendose en la matriz
        }
    }
}
