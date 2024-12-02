package Inso.Examen.infra.exception;

public class ClientRucNotFoundException extends RuntimeException {
    public ClientRucNotFoundException(String ruc) {
        super("Client with RUC " + ruc + " not found");
    }
}