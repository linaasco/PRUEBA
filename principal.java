
import java.util.Scanner;
import java.util.HashMap;
public class principal {
    private  static String nombre;
    private  static String codigo;
    private  static String rol;
    private  static String tipoDoc;
    private  static String numCedula;


    private static HashMap<String, String> nombresLaboral = new HashMap<>();
    private static HashMap<String, String> roles = new HashMap<>();
    private static HashMap<String, String> tiposDocs = new HashMap<>();
    private static HashMap<String, String> numCedulas = new HashMap<>();

    private static HashMap<String, String> codigosEmpleados = new HashMap<>();
    private static HashMap<String, String> codigosAdministradores = new HashMap<>();
    private static HashMap<String, String> codigosCoordinadores = new HashMap<>();

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sistema de Registro de Horarios V 0.7");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse en el Sistema");
            System.out.println("3. Salir");
            System.out.print("Ingrese el numero de la opcion deseada : ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    registrarUsuario(scanner);
                    break;
                case 3:
                    System.out.print("¿Está seguro de que desea salir? (si/no): ");
                    String respuesta = scanner.nextLine().toLowerCase();

                    if (respuesta.equals("si")) {
                        System.out.println("Hasta Pronto!!");
                        return;
                    } else if (respuesta.equals("no")) {
                        break;
                    } else {
                        System.out.println("Respuesta invalida. Ingrese 'si' o 'no'");
                    }
                    break;
                default:
                    System.out.println("Opcion invalida. Seleccione nuevamente");
                    break;
            }
        }
    }

    public static void iniciarSesion(Scanner scanner) {

        System.out.print("Ingrese su código de usuario: ");
        codigo = scanner.next();
        if (nombresLaboral.containsKey(codigo)) {
            nombre = nombresLaboral.get(codigo);
            tipoDoc = tiposDocs.get(codigo);
            numCedula = numCedulas.get(codigo);
            rol = roles.get(codigo);
            System.out.println("...");
            System.out.println("\nBienvenido al Sistema");
            System.out.println("Nombre: " + nombre);
            System.out.println("Tipo de Documento: " + tipoDoc);
            System.out.println("Numero de Documento: " + numCedula);
            System.out.println("Rol : " + rol);
            gestionRoles(rol);

        } else {
            System.out.println("...");
            System.out.println("NO existe ningun usuario con el código " + codigo);
        }
    }
    public static void registrarUsuario(Scanner scanner) {
        System.out.println("\nDiligencie los siguientes datos:");
        System.out.print("\nCodigo de empleado: ");
        codigo = scanner.nextLine();
        scanner.nextLine();
        if (!nombresLaboral.containsKey(codigo)) {
            System.out.print("Nombre Completo: ");
            nombre = scanner.nextLine();

            int tipoDocInt;
            while (true) {
                System.out.println("Seleccione su tipo de Documento: ");
                System.out.print("1. Cedula de Ciudadania(CC), 2. Cédula de Extranjería(CE), 3. Pasaporte: ");
                tipoDocInt = Integer.parseInt(scanner.nextLine());
                if (tipoDocInt >= 1 && tipoDocInt <= 3) {
                    break;
                } else {
                    System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                }
            }
            System.out.print("Numero de Identificacion: ");
            numCedula = scanner.nextLine();
            int rolInt;
            do {
                System.out.println("Seleccione su Rol: ");
                System.out.print("1. Administrador, 2.  Coordinador, 3. Empleado: ");
                rolInt = Integer.parseInt(scanner.nextLine());
                if (rolInt >= 1 && rolInt <= 3) {
                    break;
                } else {
                    System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                }
            } while (true);
            String rol;
            switch (rolInt) {
                case 1:
                    rol = "Administrador";
                    codigosAdministradores.put(codigo,rol);

                    break;
                case 2:
                    rol = "Coordinador";

                    codigosCoordinadores.put(codigo,rol);

                    break;
                case 3:
                    rol = "Empleado";

                    codigosEmpleados.put(codigo,rol);


                    break;
                default:
                    rol = "desconocido";
                    break;
            }

            nombresLaboral.put(codigo, nombre);
            tiposDocs.put(codigo, tipoDoc);
            numCedulas.put(codigo, numCedula);
            roles.put(codigo, rol);
            System.out.println("...");
            System.out.println("Usuario creado exitosamente.");
        } else {
            System.out.println("EL codigo " + codigo + " ya esta en uso. Por favor ingrese un codigo valido.");
        }
    }
    public static void gestionRoles(String rol) {

        switch (rol) {

            case "Empleado":
                if (codigosEmpleados.containsKey(codigo)) {
                    empleado empl = new empleado();
                    empl.ejecutar("Empleado", Integer.parseInt(codigo));
                } else {
                    System.out.println("Código de empleado no encontrado.");
                }
                break;
            case "Administrador":
                if (codigosAdministradores.containsKey(codigo)){
                    Administrador admin = new Administrador();
                    admin.ejecutar("Administrador", Integer.parseInt(codigo));
                }
                else {
                    System.out.println("Código de empleado no encontrado.");
                }
                break;
            case "Coordinador":
                if (codigosCoordinadores.containsKey(codigo)){
                    Coordinador coor = new Coordinador();
                    coor.ejecutar("Coordinador", Integer.parseInt(codigo));
                }
                else {
                    System.out.println("Código de empleado no encontrado.");
                }
                break;


        }
    }
}
