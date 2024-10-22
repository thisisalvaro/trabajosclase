import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private static final String NOMBRE_FICHERO = "libros.dat";

    // Constructor que inicializa la lista y carga los libros desde el archivo
    public Biblioteca() {
        libros = new ArrayList<>();
        cargarLibros();
    }

    // Método para cargar libros desde el archivo, leyendo uno a uno
    public void cargarLibros() {
        libros.clear();  // Limpiar la lista antes de cargar
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
        	
            while (true) {  //esto 
                try {
                    Libro libro = (Libro) ois.readObject();
                    libros.add(libro);  // Añadir libro a la lista
                } catch (IOException e) {
                    break;  // Cuando llegamos al final del archivo, salimos del bucle
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado...");
        } catch (IOException | ClassNotFoundException e) {
           System.out.println(e.getMessage());
        }
    }

    // Método para guardar un libro individualmente en el archivo sin sobrescribir
    public void guardarLibro(Libro libro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO, true))) { //el true es para que habilite el modo append es decir que no se sobreescriba
            oos.writeObject(libro);  // Guardar libro individual
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un nuevo libro
    public void agregarNuevoLibro(Libro libro) {
        for (Libro l : libros) {
            if (l.getId() == libro.getId()) {
                System.out.println("El libro con el ID " + libro.getId() + " ya existe y no se puede agregar.");
                return;
            }
        }
        
        libros.add(libro);  // Añadir el libro a la lista en memoria
        guardarLibro(libro);  // Guardar el libro en el archivo
        System.out.println("Libro agregado correctamente.");
    }

    // Método para consultar un libro por su ID
    public Libro consultarLibro(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id && !libro.isEliminado()) {
                return libro;
            }
        }
        System.out.println("El libro con el ID " + id + " no fue encontrado.");
        return null;
    }
}

