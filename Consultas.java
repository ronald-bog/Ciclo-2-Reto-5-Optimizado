import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {

    public static void crearTablas() {
        try {
            String[] sqls = {
                    "CREATE TABLE proveedor (prov_id int PRIMARY KEY,prov_nombre char(20),prov_direccion char(45),prov_telefono char(20))",
                    "CREATE TABLE clientes (alias char(20) PRIMARY KEY,nombre char(20),apellidos char(20),email varchar(45),celular char(20) ,contraseña int ,f_nacimiento date)",
                    "CREATE TABLE fabricantes (  fabricante char(20) PRIMARY KEY )",
                    "CREATE TABLE bicicletas (  id int PRIMARY KEY,  fabricante char(20) ,  precio int,  año int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante) )",
                    "CREATE TABLE motocicletas (  id int PRIMARY KEY,  fabricante char(20) ,  precio int,  autonomia int,  id_prov  int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante),  FOREIGN KEY (id_prov) REFERENCES proveedor (prov_id)  )",
                    "CREATE TABLE compras (  id int PRIMARY KEY,  alias char(20),  fabricante char(20) ,  fecha_hora timestamp,  FOREIGN KEY (alias) REFERENCES clientes (alias),  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante)  )"
            };
            for (String i : sqls) {
                PreparedStatement sentencia = Principal.conectar().prepareStatement(i);
                sentencia.executeUpdate();
            }
            System.out.println("TABLAS CREADAS CORRECTAMENTE\n------------------------------------");
        } catch (Exception e) {
            System.out.println("Ya existen tablas\n------------------------------------\nHasta Pronto...");
        }
    }

    public static void modificaYear() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("UPDATE bicicletas SET año=? WHERE fabricante=?");
        Entidades.colInt("Por favor ingrese Año y fabricante de la bicicleta a modificar.\nAño: ", 1, sentencia);
        Entidades.colStr("Fabricante: ", 2, sentencia);
        if (sentencia.executeUpdate() > 0) {
            System.out.println("! Año Cambiado Correctamente !\n------------------------------------");
        }
    }

    public static void modificaTelCliente() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("UPDATE clientes SET celular=? WHERE alias=?");
        Principal.sc.nextLine();
        Entidades.colStr("Por favor ingrese Alias del Cliente y el nuenvo número de celular.\nCelular: ", 1, sentencia);
        Entidades.colStr("Alias: ", 2, sentencia);
        if (sentencia.executeUpdate() > 0) {
            System.out.println("! Número de Celular Cambiado Correctamente !\n------------------------------------");
        }
    }

    public static void borraCompra() throws SQLException {
        PreparedStatement sentencia = Principal.conectar().prepareStatement("DELETE FROM compras WHERE alias=? AND fabricante=?");
        Principal.sc.nextLine();
        Entidades.colStr("Por favor ingrese Alias del Cliente y Fabricante de la intención de compra a eliminar.\nAlias: ", 1, sentencia);
        Entidades.colStr("Fabricante: ", 2, sentencia);
        if (sentencia.executeUpdate() > 0) {
            System.out.println("! Intención de compra ELIMINADA Correctamente !\n------------------------------------");
        }
    }

    public static void consulta1() throws SQLException {
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("SELECT fabricante FROM fabricantes ORDER BY fabricante");
        while (consulta.next()) {
            System.out.println(consulta.getString(1));
        }
        System.out.println("------------------------------------");
    }

    public static void consulta2() throws SQLException {
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("SELECT fabricante,precio,año FROM bicicletas WHERE año >= 2019 ORDER BY fabricante");
        while (consulta.next()) {
            System.out.println(consulta.getString(1) + " " + consulta.getInt(2) + " " + consulta.getString(3));
        }
        System.out.println("------------------------------------");
    }

    public static void consulta3() throws SQLException {
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("select fabricante from motocicletas where id_prov=101");
        while (consulta.next()) {
            System.out.println(consulta.getString(1));
        }
        System.out.println("------------------------------------");
    }

    public static void consulta4() throws SQLException {
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("select fabricante from compras where alias=\"lucky\" order by fabricante");
        while (consulta.next()) {
            System.out.println(consulta.getString(1));
        }
        System.out.println("------------------------------------");
    }

    public static void consulta5() throws SQLException {
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("select c.alias, c.nombre, c.apellidos from clientes c,compras p where  p.alias=c.alias and p.fabricante=\"Yeti\" order by nombre");
        while (consulta.next()) {
            System.out.println(consulta.getString(1) + " " + consulta.getString(2) + " " + consulta.getString(3));
        }
        System.out.println("------------------------------------");
    }

    public static void consulta6() throws SQLException {
        System.out.print("Por favor ingrese Año: ");
        int year = Principal.sc.nextInt();
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery("select count(fabricante) from bicicletas where año >=" + year);
        while (consulta.next()) {
            System.out.println(consulta.getInt(1));
        }
        System.out.println("------------------------------------");
    }
}
