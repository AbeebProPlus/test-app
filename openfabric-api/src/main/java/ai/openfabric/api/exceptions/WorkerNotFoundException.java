package ai.openfabric.api.exceptions;

public class WorkerNotFoundException extends RuntimeException{
    public WorkerNotFoundException(String message){
        super(message);
    }
}
