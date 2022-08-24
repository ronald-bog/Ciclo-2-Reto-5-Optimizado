import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Entidades {

    public static void colInt(String label, int column, PreparedStatement sentencia) throws SQLException {
        System.out.print(label);
        int dato = Principal.sc.nextInt();
        sentencia.setInt(column, dato);
        Principal.sc.nextLine();
    }

    public static void colStr(String label, int column, PreparedStatement sentencia) throws SQLException {
        System.out.print(label);
        String dato = Principal.sc.nextLine();
        sentencia.setString(column, dato);
    }

    public static void into(PreparedStatement sentencia) throws SQLException {
        if (sentencia.executeUpdate() > 0) {
            System.out.println("! Inserción exitosa !\n------------------------------------");
        }
    }

    public static void insertProveedor() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO proveedor VALUES (?, ?, ?, ?)");
        colInt("ID: ", 1, sentencia);
        colStr("Nombre: ", 2, sentencia);
        colStr("Dirección: ", 3, sentencia);
        colStr("Telefono: ", 4, sentencia);
        into(sentencia);
    }

    public static void insertClientes() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO clientes VALUES (?, ?, ?, ?, ?, ?, ?)");
        Principal.sc.nextLine();
        colStr("Alias: ", 1, sentencia);
        colStr("Nombre: ", 2, sentencia);
        colStr("Apellido: ", 3, sentencia);
        colStr("e-mail: ", 4, sentencia);
        colStr("Celular: ", 5, sentencia);
        colInt("Contraseña: ", 6, sentencia);
        colStr("Fecha de Nacimiento: ", 7, sentencia);
        into(sentencia);
    }

    public static void insertFabricantes() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO fabricantes VALUES (?)");
        Principal.sc.nextLine();
        colStr("Nombre Fabricante: ", 1, sentencia);
        into(sentencia);
    }

    public static void insertBicicletas() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO bicicletas VALUES (?, ?, ?, ?)");
        colInt("ID: ", 1, sentencia);
        colStr("Fabricante: ", 2, sentencia);
        colInt("Precio: ", 3, sentencia);
        colInt("Año: ", 4, sentencia);
        into(sentencia);
    }

    public static void insertMotocicletas() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO motocicletas VALUES (?, ?, ?, ?, ?)");
        colInt("ID: ", 1, sentencia);
        colStr("Fabricante: ", 2, sentencia);
        colInt("Precio: ", 3, sentencia);
        colInt("Autonomia: ", 4, sentencia);
        colInt("Id. Proveedor: ", 5, sentencia);
        into(sentencia);
    }

    public static void insertCompras() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("INSERT INTO compras VALUES (?, ?, ?, ?)");
        colInt("ID: ", 1, sentencia);
        colStr("Alias Comprador: ", 2, sentencia);
        colStr("Fabricante: ", 3, sentencia);
        colStr("Fecha y Hora: ", 4, sentencia);
        into(sentencia);
    }
}
