package Inso.Examen.util;

public class DocumentValidator {

    // Método para validar el formato del DNI (8 dígitos numéricos)
    public static boolean validateDni(String dni) {
        if (dni == null || dni.length() != 8) {
            return false;
        }
        return dni.matches("\\d{8}");
    }

    // Método para validar el formato del RUC (11 dígitos numéricos)
    public static boolean validateRuc(String ruc) {
        if (ruc == null || ruc.length() != 11) {
            return false;
        }
        return ruc.matches("\\d{11}");
    }
}
