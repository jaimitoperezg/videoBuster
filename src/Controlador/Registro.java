package Controlador;

import Modelo.Categoria;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sql.Conexion;

public class Registro {
    private static final String CAT_INSERT = "INSERT INTO categoria (id, descripcion) VALUES(CAT_ID.NEXTVAL, ?)";
    private static final String CAT_UPDATE = "UPDATE categoria SET descripcion=? WHERE id=?";
    private static final String CAT_DELETE = "DELETE FROM categoria WHERE id=?";
    private static final String PELI_INSERT = "INSERT INTO pelicula (codigo, nombre, precio, id_categoria, formato4k) VALUES(PELI_ID.NEXTVAL, ?, ?, ?, ?)";
    private static final String PELI_DELETE = "DELETE FROM pelicula WHERE codigo=?";
    private static final String DELETE_PRECIOM = "DELETE FROM pelicula WHERE precio > ?";
    private static final String PELI_UPDATE = "UPDATE pelicula SET nombre=?,precio=?,id_categoria=?,formato4k=? WHERE codigo=?";
    private static final String PELI_UPDATEP = "UPDATE pelicula SET nombre = concat('P', nombre)";
    private static final String SEL_CAT = "SELECT * FROM categoria";
    private static final String SEL_CATID = "SELECT id FROM categoria WHERE descripcion = ?";
    private static final String SEL_CATDESC = "SELECT descripcion FROM categoria WHERE id = ?";
    private static final String BUSCAR_PELI = "SELECT * FROM pelicula WHERE codigo = ?";
    private static final String BUSCAR_PELI_TXT = "SELECT * FROM pelicula WHERE nombre = ?";
    private static final String LIST_PELI = "SELECT * FROM pelicula";
    private static final String LIST_CAT = "SELECT * FROM categoria";
    private static final String LIST_POR_CAT = "SELECT * FROM pelicula WHERE id_categoria = ?";
    
    
    public static boolean ingresarCat(Categoria cat) {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement insCat = conexion.prepareStatement(CAT_INSERT);
            insCat.setString(1, cat.getDescripcion());
            insCat.execute();
            insCat.close();
            conexion.close();
            return true;
        } catch(Exception e){
            System.out.println("Error SQL al agregar el registro " + e.getMessage());
            return false;
        }
    }
    
    public static boolean actualizarCat(Categoria cat)
    {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement updCat = conexion.prepareStatement(CAT_UPDATE);
            updCat.setString(1, cat.getDescripcion());
            updCat.setInt(2, cat.getId());
            updCat.execute();
            updCat.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al agregar el registro " + e.getMessage());
            return false;
        }
    }
    
    public boolean borrarCat(int id) {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement borrarCat = conexion.prepareStatement(CAT_DELETE);
            borrarCat.setInt(1, id);
            borrarCat.execute();
            borrarCat.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
            return false;
        }
    }
    
    public static boolean ingresarPeli(Pelicula peli) {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement insPel = conexion.prepareStatement(PELI_INSERT);
            insPel.setString(1, peli.getNombre());
            insPel.setInt(2, peli.getPrecio());
            insPel.setInt(3, peli.getId_categoria());
            insPel.setString(4, peli.getFormato4k());
            insPel.execute();
            insPel.close();
            conexion.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public boolean borrarPeli(int codigo) {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement borrarPeli = conexion.prepareStatement(PELI_DELETE);
            borrarPeli.setInt(1, codigo);
            borrarPeli.execute();
            borrarPeli.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
            return false;
        }
    }
    
    public boolean borrarXprecioM(int precio) {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement borrarPrecio = conexion.prepareStatement(DELETE_PRECIOM);
            borrarPrecio.setInt(1, precio);
            borrarPrecio.execute();
            borrarPrecio.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
            return false;
        }
    }
    
    public static boolean actualizarPeli(Pelicula peli)
    {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement updPeli = conexion.prepareStatement(PELI_UPDATE);
            updPeli.setString(1, peli.getNombre());
            updPeli.setInt(2, peli.getPrecio());
            updPeli.setInt(3, peli.getId_categoria());
            updPeli.setString(4, peli.getFormato4k());
            updPeli.setInt(5, peli.getCodigo());
            updPeli.execute();
            updPeli.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al agregar el registro " + e.getMessage());
            return false;
        }
    }
    
    public static boolean actualizarP()
    {
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement updPeli = conexion.prepareStatement(PELI_UPDATEP);
            updPeli.execute();
            updPeli.close();
            conexion.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al agregar el registro " + e.getMessage());
            return false;
        }
    }
    
    public static ArrayList<String> selectCat(){
        ArrayList<String> lista = new ArrayList<String>();
        Connection conexion = Conexion.getConection();
        try {
            PreparedStatement getCat = conexion.prepareStatement(SEL_CAT);
            ResultSet rs = getCat.executeQuery();
            while (rs.next()){
                lista.add(rs.getString("descripcion"));
            }
        } catch(Exception e) {
            System.out.println("No se pudo obtener la lista desde la BD");
        }
        return lista;
    }
    
    
    //obtiene el ID de categoria a partir del texto de la descripción
    public static int getCatId(String desc) {
        Connection conexion = Conexion.getConection();
        try{
            PreparedStatement getCat = conexion.prepareStatement(SEL_CATID);
            getCat.setString(1, desc);
            ResultSet rs = getCat.executeQuery();
            if(rs.next()) {
                int resId = rs.getInt("id");
                return resId;
            }
            
        } catch(Exception e) {
            System.out.println("No hay resultados");
        }
        return 0;
    }
    
    //obtiene la descripción de la categoría a partir de su número de ID
    public static String getCatDesc(int id) {
        Connection conexion = Conexion.getConection();
        try{
            PreparedStatement getDesc = conexion.prepareStatement(SEL_CATDESC);
            getDesc.setInt(1, id);
            ResultSet rs = getDesc.executeQuery();
            if(rs.next()) {
                String resDesc = rs.getString("descripcion");
                return resDesc;
            }
            
        } catch(Exception e) {
            System.out.println("No hay resultados");
        }
        return null;
    }
    
     public static Pelicula buscarPeli(int bCodigo) {
        Pelicula peli = null;
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement buscaPel = conexion.prepareStatement(BUSCAR_PELI);
            buscaPel.setInt(1, bCodigo);
            ResultSet rs = buscaPel.executeQuery();
            if(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int idCategoria = rs.getInt("id_categoria");
                String f4k = rs.getString("formato4k");

                peli = new Pelicula(codigo, nombre, precio, idCategoria, f4k);
                return peli;
            }
            buscaPel.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al buscar el codigo del registro " + bCodigo + e.getMessage());
            return peli;
        }
        return null;
    }
     
    public static Pelicula buscarPeliTxt(String bNombre) {
        Pelicula peli = null;
        try{
            Connection conexion = Conexion.getConection();
            PreparedStatement buscaPel = conexion.prepareStatement(BUSCAR_PELI_TXT);
            buscaPel.setString(1, bNombre);
            ResultSet rs = buscaPel.executeQuery();
            if(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int idCategoria = rs.getInt("id_categoria");
                String f4k = rs.getString("formato4k");

                peli = new Pelicula(codigo, nombre, precio, idCategoria, f4k);
                return peli;
            }
            buscaPel.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al buscar en los nombres el registro " + bNombre + e.getMessage());
            return peli;
        }
        return null;
    }
     
    public ArrayList<Pelicula> listarPeli() {
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
        Connection conexion = Conexion.getConection();
        try{
            
            PreparedStatement listPel = conexion.prepareStatement(LIST_PELI);
            ResultSet rs = listPel.executeQuery();
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int idCategoria = rs.getInt("id_categoria");
                String f4k = rs.getString("formato4k");

                Pelicula peli = new Pelicula(codigo, nombre, precio, idCategoria, f4k);
                peliculas.add(peli);
            }
            listPel.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
        }
        return peliculas;
    }
    
    public ArrayList<Pelicula> listarRomance() {
        ArrayList<Pelicula> romances = new ArrayList<Pelicula>();
        Connection conexion = Conexion.getConection();
        try{
            
            PreparedStatement listRom = conexion.prepareStatement(LIST_POR_CAT);
            listRom.setInt(1, getCatId("ROMANCE"));
            ResultSet rs = listRom.executeQuery();
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int idCategoria = rs.getInt("id_categoria");
                String f4k = rs.getString("formato4k");

                Pelicula peli = new Pelicula(codigo, nombre, precio, idCategoria, f4k);
                romances.add(peli);
            }
            listRom.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
        }
        return romances;
    }
    
     public ArrayList<Pelicula> listarPorCat(String catDesc) {
        ArrayList<Pelicula> listasCat = new ArrayList<Pelicula>();
        Connection conexion = Conexion.getConection();
        try{
            
            PreparedStatement listPCat = conexion.prepareStatement(LIST_POR_CAT);
            listPCat.setInt(1, getCatId(catDesc.toUpperCase()));
            ResultSet rs = listPCat.executeQuery();
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int idCategoria = rs.getInt("id_categoria");
                String f4k = rs.getString("formato4k");

                Pelicula peli = new Pelicula(codigo, nombre, precio, idCategoria, f4k);
                listasCat.add(peli);
            }
            listPCat.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al listar por la categoría seleccionada " + e.getMessage());
        }
        return listasCat;
    }
    
    public ArrayList<Categoria> listarCat() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        Connection conexion = Conexion.getConection();
        try{
            
            PreparedStatement listCat = conexion.prepareStatement(LIST_CAT);
            ResultSet rs = listCat.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");

                Categoria cat = new Categoria(id, descripcion);
                categorias.add(cat);
            }
            listCat.close();
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error SQL al borrar el registro " + e.getMessage());
        }
        return categorias;
    }
}