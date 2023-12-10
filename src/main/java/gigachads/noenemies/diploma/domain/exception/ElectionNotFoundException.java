package gigachads.noenemies.diploma.domain.exception;

public class ElectionNotFoundException extends RuntimeException{
    public ElectionNotFoundException(String msg){
        super(msg);
    }
}
