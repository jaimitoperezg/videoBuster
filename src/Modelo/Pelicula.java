package Modelo;

public class Pelicula {
    private int codigo;
    private String nombre;
    private int precio;
    private int id_categoria;
    private String formato4k;

    
//Constructor sin parametros
    public Pelicula() {
        //this.codigo = 0;
        this.nombre = "";   
        this.precio = 0;
        this.id_categoria = 0;
        this.formato4k = "";
    }    

//Constructor sólo con codigo y categoría
    public Pelicula(int codigo, int id_categoria) {
        this.nombre = "";
        this.precio = 0;
        this.id_categoria = id_categoria;
        this.formato4k = "";
        this.codigo = codigo;
    }
    
    //Constructor sin código
    public Pelicula(String nombre, int precio, int id_categoria, String formato4k) {
        this.nombre = nombre;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.formato4k = formato4k;
    }
    
    //Constructor con todos los atributos
    public Pelicula(int codigo, String nombre, int precio, int id_categoria, String formato4k) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.formato4k = formato4k;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getFormato4k() {
        return formato4k;
    }

    public void setFormato4k(String formato4k) {
        this.formato4k = formato4k;
    }
}
