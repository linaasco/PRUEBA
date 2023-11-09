package LogicaNegocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class empleado extends Persona {

    private static HashMap<String, String> nombresLaboral = new HashMap<>();

    private static HashMap<String, String> horarios = new HashMap<>();
    private static HashMap<String, ArrayList<String>> mensajes = new HashMap<>();

    public empleado(String codigo, String nombre, String rol, String tipoDoc, String numCedula, String horario, String mensaje, String codigoDestinatario) {
        super(codigo, nombre, rol, tipoDoc, numCedula, horario, mensaje, codigoDestinatario);
    }

    public void ejecutar(String rol, int codigoEmpleado) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nOpciones para Empleados:");
                System.out.println("1. Ver horario");
                System.out.println("2. Revisar mensajes");
                System.out.println("3. Enviar mensaje a coordinador");
                System.out.println("4. Salir");
                System.out.print("Ingrese el numero de la opcion deseada: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        verHorario(String.valueOf(codigoEmpleado));
                        break;
                    case 2:
                        reviMens(String.valueOf(codigoEmpleado));
                        break;
                    case 3:
                        System.out.print("Ingrese el código del LogicaNegocio.Coordinador: ");
                        String codigoD = scanner.next();
                        scanner.nextLine();
                        mensCoor(codigoD);
                        break;
                    case 4:
                        salir();
                        return; // Salir del método
                    default:
                        System.out.println("Opcion invalida. Seleccione nuevamente");
                        break;
                }
            }
        }


    public static String verHorario(String codigo) {
        if (!horarios.isEmpty() && horarios.containsKey(codigo)) {
            return "Su horario es " + horarios.get(codigo);
        } else {
            return "Usted no tiene parametrizado ningun horario";
        }
    }

    public static String reviMens(String codigo) {
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

    public static void mensCoor(String codigoDestinatario) {
        Scanner scanner = new Scanner(System.in);
        if (nombresLaboral.containsKey(codigoDestinatario)) {
            String nombre = nombresLaboral.get(codigoDestinatario);
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
            System.out.println("Se envió el mensaje.");
        } else {
            System.out.println("...");
            System.out.println("NO existe ningun usuario con el código " + codigoDestinatario);
        }
    }

    public static void salir() {
        // No hay necesidad de devolver nada en este caso
    }
}


