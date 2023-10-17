
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Coordinador {
        private static String nombre;
        private static String codigo;
        private static String horario;
        private static HashMap<String, String> nombresLaboral = new HashMap<>();
        private static HashMap<String, ArrayList<String>> mensajes = new HashMap<>();
        private static HashMap<String, String> horarios = new HashMap<>();

        public void ejecutar(String rol, int codigoCoordinador) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nOpciones para Coordinadores:");
                System.out.println("1. Ver Horario de Empleado");
                System.out.println("2. Registrar Horario");
                System.out.println("3. Modificar Horario");
                System.out.println("4. Eliminar  Horario");
                System.out.println("5. Revisar mensajes");
                System.out.println("6. Enviar mensaje a Administrador");
                System.out.println("7. Enviar mensaje a Empleado");
                System.out.println("8. Salir");
                System.out.print("Ingrese el numero de la opcion deseada: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();


                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el codigo del empleado:");
                        String codigoEmpleado = scanner.next();
                        scanner.nextLine();
                        String horarioEmpleado = verHorarioE(codigoEmpleado);
                        System.out.println(horarioEmpleado);
                        break;
                    case 2:
                        System.out.println("Ingrese el codigo del empleado:");
                        String codigoEmpleado2 = scanner.next();
                        scanner.nextLine();
                        String nuevohorario = registrarHorario(codigoEmpleado2);
                        System.out.println(nuevohorario);
                        break;
                    case 3:
                        System.out.println("Ingrese el codigo del empleado:");
                        String codigoEmpleado3 = scanner.next();
                        scanner.nextLine();
                        String cambioHorario = modificarHorario(codigoEmpleado3);
                        System.out.println(cambioHorario);

                        break;
                    case 4:
                        System.out.println("Ingrese el codigo del empleado:");
                        String codigoEmpleado4 = scanner.next();
                        scanner.nextLine();
                        String eliminar = eliminarHorario(codigoEmpleado4);
                        System.out.println(eliminar);
                        break;
                    case 5:
                        reviMens(String.valueOf(codigoCoordinador));
                        break;
                    case 6:
                        System.out.print("Ingrese el codigo del Administrador: ");
                        String codigoDestinatario = scanner.next();
                        String enviar = mensAdmin(codigoDestinatario);
                        System.out.println(enviar);
                        break;
                    case 7:
                        System.out.print("Ingrese el codigo del Empleado: ");
                        String codigoDestinatario1 = scanner.next();
                        String enviar1 = mensEmp(codigoDestinatario1);
                        System.out.println(enviar1);
                        break;
                    case 8:
                        salir();
                        return; // Salir del método
                    default:
                        System.out.println("Opcion invalida. Seleccione nuevamente");
                        break;
                }
            }
        }


        public String verHorarioE(String codigo) {


            if (nombresLaboral.containsKey(codigo)) {
                nombre = nombresLaboral.get(codigo);

                horario = horarios.get(codigo);
                return horario;
            } else {
                System.out.println("...");
                return "NO existe ningun horario con el código " + codigo;
            }
        }

        public String registrarHorario(String codigo) {
            Scanner scanner = new Scanner(System.in);

            if (nombresLaboral.containsKey(codigo)) {
                System.out.print("Ingrese el horario a asignar: ");
                horario = scanner.nextLine();
                horarios.put(codigo, horario);
                return "Se ha asignado el horario exitosamente";
            } else {
                return "No existe un empleado con el código " + codigo;
            }

        }

        public String modificarHorario(String codigo) {
            Scanner scanner = new Scanner(System.in);

            if (nombresLaboral.containsKey(codigo)) {
                nombre = nombresLaboral.get(codigo);
                if (horario != null) {
                    horario = horarios.get(codigo);
                    System.out.println("El horario asignado del empleado " + nombre + " es : " + horario);
                    System.out.print("Ingrese el nuevo horario: ");
                    horario = scanner.nextLine();
                    horarios.put(codigo, horario);
                    return "Se ha modificado el horario exitosamente";
                } else {
                    return "\nEl empleado no tiene parametrizado ningun horario";
                }
            } else {
                return "No existe un empleado con el código " + codigo;
            }
        }
    public String eliminarHorario(String codigo) {
        Scanner scanner = new Scanner(System.in);

        if (nombresLaboral.containsKey(codigo)) {
            nombre = nombresLaboral.get(codigo);
            if (horario != null) {
                horario = horarios.get(codigo);
                System.out.println("El empleado " + nombre + " tiene asignado el horario: "+ horario);
                System.out.print("¿Está seguro que desea eliminar el horario? (si/no): ");
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("si")) {
                    horarios.remove(codigo);
                    System.out.println("...");
                    return "Se ha eliminado el horario exitosamente";
                } else if (respuesta.equals("no")) {
                    return "No se ha eliminado al horario";
                } else {
                    return "Respuesta invalida. Ingrese 'si' o 'no'";
                }
            } else {
                return "El empleado no tiene asignado ningun horario";
            }
        } else {
            System.out.println("...");
            return "NO existe ningun usuario con el código " + codigo;
        }
    }

        public String reviMens(String codigo) {
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

        public String mensAdmin(String codigoDestinatario) {
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

    public String mensEmp(String codigoDestinatario) {
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

