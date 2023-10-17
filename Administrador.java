import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Administrador {

    private  static String nombre;
    private  static String codigo;
    private  static String rol;
    private  static String tipoDoc;
    private  static String numCedula;
    private static HashMap<String, String> nombresLaboral = new HashMap<>();
    private static HashMap<String, String> tiposDocs = new HashMap<>();
    private static HashMap<String, String> roles = new HashMap<>();
    private static HashMap<String, String> numCedulas = new HashMap<>();
    private static HashMap<String, ArrayList<String>> mensajes = new HashMap<>();



    public void ejecutar(String rol, int codigoAdministrador) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOpciones para Administradores:");
            System.out.println("1. Consultar datos empleado");
            System.out.println("2. Consultar todos los empleados");
            System.out.println("3. Modificar datos de empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Revisar mensajes");
            System.out.println("6. Enviar mensaje a coordinador");
            System.out.println("7. Salir");;
            System.out.print("Ingrese el numero de la opcion deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    System.out.println("Ingrese el codigo del empleado:");
                    String codigoEmpleado = scanner.next();
                    scanner.nextLine();
                    String datosEmpleado = verDatos(codigoEmpleado);
                    System.out.println(datosEmpleado);
                    break;
                case 2:
                    verEmpleados();
                    break;
                case 3:
                    modificarDatos();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    reviMens(String.valueOf(codigoAdministrador));
                    break;
                case 6:
                    System.out.print("Ingrese el codigo del Coordinador: ");
                    String codigoDestinatario = scanner.next();
                    String enviar = mensCoor(codigoDestinatario);
                    System.out.println(enviar);
                    break;
                case 7:
                    salir();
                    return; // Salir del método
                default:
                    System.out.println("Opcion invalida. Seleccione nuevamente");
                    break;
            }
        }
    }




    public String verDatos(String codigo) {

        if (nombresLaboral.containsKey(codigo)) {
             nombre = nombresLaboral.get(codigo);
             tipoDoc = tiposDocs.get(codigo);
             numCedula = numCedulas.get(codigo);
             rol = roles.get(codigo);


            return nombre+ tipoDoc+numCedula+rol;
        } else {
            System.out.println("...");
            return "NO existe ningun usuario con el código " + codigo;
        }

    }
    public void verEmpleados(){
        Set<String> codigos = nombresLaboral.keySet();
        System.out.println();
        System.out.println("Numero de empleados registrados: " + codigos.size());
        for (String codigo : codigos) {
            nombre = nombresLaboral.get(codigo);
            rol = roles.get(codigo);
            System.out.println();
            System.out.println("- Nombre: " + nombre + ". Codigo: " + codigo + ". Rol: " + rol);
        }
    }
    public String modificarDatos(){
        Scanner scanner= new Scanner(System.in);

        System.out.print("Ingrese el codigo del empleado: ");
        String codigoE = scanner.next();
        scanner.nextLine();
        codigoE.equals(codigo);
        if (nombresLaboral.containsKey(codigo)) {
            System.out.println("Seleccione qué aspecto desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Tipo de Documento");
            System.out.println("3. Número de Documento");
            System.out.println("4. Rol");
            int opcionModificar = scanner.nextInt();
            scanner.nextLine();

            switch (opcionModificar) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre del empleado: ");
                    nombre = scanner.nextLine();
                    nombresLaboral.put(codigo, nombre);
                    System.out.println("Nombre modificado con éxito.");
                    break;
                case 2:
                    int tipoDocInt;
                    while (true){
                        System.out.println("Seleccione el nuevo tipo de documento: ");
                        System.out.print("1. Cedula de Ciudadania(CC), 2. Cédula de Extranjería(CE), 3. Pasaporte: ");
                        tipoDocInt = Integer.parseInt(scanner.nextLine());
                        if (tipoDocInt >=1 && tipoDocInt <= 3) {
                            break;
                        } else {
                            System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                        }
                    }
                    String tipoDoc;
                    switch (tipoDocInt) {
                        case 1 :
                           tipoDoc= "Cedula de Ciudadania(CC)";
                           break;
                        case 2 :
                            tipoDoc="Cédula de Extranjería(CE)";
                            break;
                        case 3 :
                            tipoDoc= "Pasaporte";
                            break;
                        default :
                            tipoDoc="desconocido";
                            break;
                    }
                    tiposDocs.put(codigo, tipoDoc);
                    System.out.println("Tipo de documento modificado con éxito.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo numero de documento: ");
                    numCedula = scanner.nextLine();
                    numCedulas.put(codigo, numCedula);
                    System.out.println("Numero de documento modificado con éxito..");
                    break;
                case 4:
                    int rolInt;
                    while (true) {
                        System.out.println("Seleccione su Rol: ");
                        System.out.print("1. Administrador, 2. Coordinador, 3. Empleado: ");
                        rolInt = Integer.parseInt(scanner.nextLine());
                        if (rolInt >= 1 && rolInt <= 3) {
                            break;
                        } else {
                            System.out.println("Opcion Invalida. Seleccione 1, 2 o 3");
                        }
                    }

                    String rol;
                    switch (rolInt) {
                        case 1:
                            rol = "Administrador";
                            break;
                        case 2:
                            rol = "Coordinador";
                            break;
                        case 3:
                            rol = "Empleado";
                            break;
                        default:
                            rol = "desconocido";
                            break;
                    }
                    roles.put(codigo, rol);
                    System.out.println("Rol modificado con éxito.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } else {
            System.out.println("No existe un empleado con el código " + codigo);
        }
        return "Modificacion exitosa";

    }
    public void eliminarEmpleado() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el codigo del empleado: ");
        String codigoE = scanner.next();
        scanner.nextLine();
        codigoE.equals(codigo);

        if (nombresLaboral.containsKey(codigo)) {
            System.out.print("¿Está seguro que desea eliminar al empleado? (si/no): ");
            String respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.equals("si")) {
                nombresLaboral.remove(codigo);
                tiposDocs.remove(codigo);
                numCedulas.remove(codigo);
                roles.remove(codigo);
                System.out.println("...");
                System.out.println("Se ha eliminado el empleado exitosamente");
            } else if (respuesta.equals("no")) {
                System.out.println("No se ha eliminado al usuario");
            } else {
                System.out.println("Respuesta invalida. Ingrese 'si' o 'no'");
            }
        } else {
            System.out.println("...");
            System.out.println("NO existe ningun usuario con el código " + codigo);
        }
    }


    public  String reviMens(String codigo) {
        if (mensajes.containsKey(codigo)) {
            ArrayList<String> listaMensajes = mensajes.get(codigo);
            StringBuilder result = new StringBuilder("Tiene los siguientes mensajes:\n");
            for (String mensaje : listaMensajes) {
                result.append("- ").append(mensaje).append("\n");
            }
            return result.toString();
        } else {
            return "No tiene mensajes";
        }
    }

    public String mensCoor(String codigoDestinatario) {
        Scanner scanner = new Scanner(System.in);

        if (nombresLaboral.containsKey(codigoDestinatario)) {
            String nombre = nombresLaboral.get(codigo);
            scanner.nextLine();
            System.out.print("Ingrese el mensaje: ");
            String mensaje = scanner.nextLine();
            String mensajeCompleto = "De: " + nombre + ". Mensaje: " + mensaje;
            if (mensajes.containsKey(codigoDestinatario)) {
                mensajes.get(codigoDestinatario).add(mensajeCompleto);
            } else {
                ArrayList<String> listaMensajes = new ArrayList<>();
                listaMensajes.add(mensajeCompleto);
                mensajes.put(codigoDestinatario, listaMensajes);
            }
            return "Se envió el mensaje.";
        } else {
            System.out.println("...");
            return "NO existe ningun usuario con el código " + codigoDestinatario;
        }
    }

    public static void salir() {
        // No hay necesidad de devolver nada en este caso
    }
}
