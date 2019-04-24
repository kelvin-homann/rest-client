package resources;

public class UnknownResourceException extends RuntimeException {

    public UnknownResourceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public UnknownResourceException(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public UnknownResourceException(String s, Throwable throwable) {
        super(s, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public UnknownResourceException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
