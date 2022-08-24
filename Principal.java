import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        try {
            System.out.print(
                    "************************\n" +
                            "***    CRUD  MySQL   ***\n" +
                            "************************\n" +
                            "1. Crear Tablas.\n" +
                            "2. Ingresar datos Tabla.\n" +
                            "3. Modificar Año de Bicicleta.\n" +
                            "4. Modificar Telefono de Cliente.\n" +
                            "5. Borrar intención de compra.\n" +
                            "6. Consultas.\n" +
                            "Otro número para SALIR\n>> "
            );
            switch (sc.nextByte()) {
                case 1:
                    Consultas.crearTablas();
                    break;
                case 2:
                    System.out.print("1.Proveedor | 2.Clientes | 3.Fabricantes | 4.Bicicletas | 5.Motocicletas | 6.Compras\n>> ");
                    byte tabla = sc.nextByte();
                    if (tabla == 1) {
                        Entidades.insertProveedor();
                    } else if (tabla == 2) {
                        Entidades.insertClientes();
                    } else if (tabla == 3) {
                        Entidades.insertFabricantes();
                    } else if (tabla == 4) {
                        Entidades.insertBicicletas();
                    } else if (tabla == 5) {
                        Entidades.insertMotocicletas();
                    } else if (tabla == 6) {
                        Entidades.insertCompras();
                    } else {
                        System.out.print("Por favor digite una opcion entre 1 a 6");
                    }
                    break;
                case 3:
                    Consultas.modificaYear();
                    break;
                case 4:
                    Consultas.modificaTelCliente();
                    break;
                case 5:
                    Consultas.borraCompra();
                    break;
                case 6:
                    System.out.print("1. Listado de Fabricantes.\n" +
                            "2. Mostrar información fabricantes Bicicletas estrenadas desde 2019.\n" +
                            "3. Mostrar fabricantes motocicletas con motor Auteco.\n" +
                            "4. Mostrar fabricante intencion de Compra del cliente lucky.\n" +
                            "5. Mostrar Clientes que quieren comprar bicicleta Yeti.\n" +
                            "6. Cantidad bicicletas fabricadas desde \"x\" Año.\n" +
                            ">> ");
                    byte consulta = sc.nextByte();
                    if (consulta == 1) {
                        Consultas.consulta1();
                    } else if (consulta == 2) {
                        Consultas.consulta2();
                    } else if (consulta == 3) {
                        Consultas.consulta3();
                    } else if (consulta == 4) {
                        Consultas.consulta4();
                    } else if (consulta == 5) {
                        Consultas.consulta5();
                    } else if (consulta == 6) {
                        Consultas.consulta6();
                    } else {
                        System.out.print("Por favor digite una opcion entre 1 a 6");
                    }
                    break;
                default:
                    System.out.println("Hasta Pronto...");
            }
        } catch (Exception e) {
            System.out.println("Hasta Pronto...");
        }
    }

    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reto5", "root", "");
            if (con != null) {
                System.out.println("*** Conectado a la base de datos ***\n------------------------------------");
            }
        } catch (SQLException error) {
            System.out.println("Error: " + error.getErrorCode() + " " + error.getMessage());
        }
        return con;
    }
}
