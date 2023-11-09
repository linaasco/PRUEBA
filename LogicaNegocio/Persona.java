package LogicaNegocio;

import java.util.ArrayList;
import java.util.HashMap;

public class Persona {
    private String codigo;
    private String nombre;
    private String rol;
    private String tipoDoc;
    private String numCedula;
    private String horario;
    private String mensaje;
    private String codigoDestinatario;
    private  HashMap<String, String> nombresLaboral = new HashMap<>();
    private static HashMap<String, ArrayList<String>> mensajes = new HashMap<>();

    public Persona(String codigo, String nombre, String rol, String tipoDoc, String numCedula, String horario, String mensaje, String codigoDestinatario) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.rol=rol;
        this.tipoDoc=tipoDoc;
        this.numCedula=numCedula;
        this.horario=horario;
        this.mensaje=mensaje;
        this.codigoDestinatario=codigoDestinatario;

    }


}
