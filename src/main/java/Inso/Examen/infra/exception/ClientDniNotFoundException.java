package Inso.Examen.infra.exception;

public class ClientDniNotFoundException extends RuntimeException {
    public ClientDniNotFoundException(String dni) {
        super("Client with DNI " + dni + " not found");
    }
}
