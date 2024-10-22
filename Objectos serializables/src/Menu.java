import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
   
       try( BufferedReader br= new BufferedReader(new InputStreamReader(System.in));) {
		
    	 boolean flag= true;
        
        while (flag) {
            System.out.println("=== Menú de Biblioteca ===");
            System.out.println("1. Agregar nuevo libro");
            System.out.println("2. Consultar libro por ID");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            
            String opcionS = br.readLine();
            int opcion= Integer.parseInt(opcionS);

            switch (opcion) {
                case 1:
                    // Agregar nuevo libro
                    System.out.print("Ingrese el ID del libro: ");
                    String idS=br.readLine();         		
                    int id = Integer.parseInt(idS);
                    
                    System.out.println("Ingrese el titulo del libro");
                    String titulo= br.readLine();
                    
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = br.readLine();
                    
                    System.out.print("Ingrese el precio del libro: ");
                    String precioS= br.readLine();
                    double precio = Double.parseDouble(precioS);
                    
                    Libro nuevoLibro = new Libro(id,titulo, autor, precio);
                    biblioteca.agregarNuevoLibro(nuevoLibro);
                    break;
                    
                case 2:
                    // Consultar libro por ID
                    System.out.print("Ingrese el ID del libro a consultar: ");
                    String consultaIdS= br.readLine();
                    int consultaId = Integer.parseInt(consultaIdS);
                    Libro libro = biblioteca.consultarLibro(consultaId);
                    if (libro != null) {
                        System.out.println(libro); //al parecer si no pongo el toString se llama solo al toString
                    }
                    break;
                    
                case 3:
                    // Salir
                    System.out.println("Saliendo del programa...");      
                    flag=false;
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        
	   	} catch (IOException e) {
			System.out.println(e.getMessage());
		}
     
    }
}

