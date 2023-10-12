import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Calificaciones{
    static String[][] datosAlumnos;
    static File archivo;
    static int numAlumnos;

    public static void main(String[] args){
        archivo = new File("D:\\Documentos HDD\\Septimo Semestre\\Asesorias\\calificaciones.txt");
        leerNumAlumnos();
        datosAlumnos = new String[numAlumnos][6];
        leerCalificaciones();
        escribirCalificacionesConPromedioTxt();
    }

    static void leerCalificaciones(){
        FileReader fr;
        BufferedReader br;
        int contador = 0;
        try{
            fr  = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = br.readLine();

            while(linea != null){
                // Si es la primera iteracion no va a leer la linea porque es la que contiene
                // el numero de alumnos
                if(contador==0){
                    linea = br.readLine();
                    contador++;
                    continue;
                }
                // Dividimos la linea en palabras
                // La primera palabra es su ID y las demas son sus calificaciones
                String[] palabras = linea.split(" ");

                linea = br.readLine();

                // Calculamos el promedio del alumno de esa linea
                double promedio = calcularPromedio(palabras);
                //Este es su ID del alumno
                datosAlumnos[contador - 1][0] = palabras[0]; 
                //Estas son sus calificaciones
                datosAlumnos[contador- 1][1] = palabras[1]; 
                datosAlumnos[contador- 1][2] = palabras[2]; 
                datosAlumnos[contador- 1][3] = palabras[3]; 
                datosAlumnos[contador- 1][4] = palabras[4]; 
                // Este es el promedio
                datosAlumnos[contador- 1][5] = Double.toString(promedio); 
                
                contador++;
            }

            leerArray();
            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lee el numero que hay en el txt
    static void leerNumAlumnos(){
        if(!archivo.exists()){
            throw new RuntimeException("El archivo no existe");
        }
        FileReader fr;
        BufferedReader br;
        try {
            fr  = new FileReader(archivo);
            br = new BufferedReader(fr);
            numAlumnos = Integer.parseInt(br.readLine());
            fr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Calcula el promedio de las calificaciones pasadas (No toma en cuenta el primer indice ya que es el
    //  ID del alumno)
    static double calcularPromedio(String[] palabras){
        double promedio = (Double.parseDouble(palabras[1]) + Double.parseDouble(palabras[2]) + Double.parseDouble(palabras[3]) + Double.parseDouble(palabras[4]))/4;
        return promedio;
    }

    // Escribe las calificaiones del alumno con el promedio en un archivo binario
    static void escribirCalificacionesConPromedioBinario(){
        FileOutputStream fos;
        BufferedOutputStream bos;

        try {
            fos = new FileOutputStream(archivo);
            bos = new BufferedOutputStream(fos);
            for (int i = 0; i < datosAlumnos.length; i++) {
                for (int j = 0; j < datosAlumnos[0].length; j++) {
                    int bytes = Integer.parseInt(datosAlumnos[i][j]);
                    bos.write(bytes);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Escribe las calificaiones del alumno con el promedio en un archivo txt
    static void escribirCalificacionesConPromedioTxt(){
        FileWriter fw;
        File archivoPromedios = new File("D:\\Documentos HDD\\Septimo Semestre\\Asesorias\\calificaciones_con_promedio.txt");
        try {
            fw = new FileWriter(archivoPromedios);
            for (int i = 0; i < datosAlumnos.length; i++) {
                String linea = datosAlumnos[i][0] + " " + datosAlumnos[i][1] + " " + datosAlumnos[i][2] + " " + datosAlumnos[i][3] + " " + datosAlumnos[i][4] + " " + datosAlumnos[i][5];
                fw.write(linea);
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Imprime el arreglo de datosAlumno en consola
    static void leerArray(){
        for(int i=0; i<datosAlumnos.length; i++){
            for(int j=0; j<datosAlumnos[i].length; j++){
                System.out.println(datosAlumnos[i][j]);
            }
        }
    }
}