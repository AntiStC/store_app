package exception;

public class EntityNotCreateException extends Exception{
    public EntityNotCreateException(String message){
        super("Entity not create!");
    }
}
