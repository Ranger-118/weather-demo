package indi.henry.weatherdemo.exception;

public class AppException extends Exception {

    /**
     * Application owned exception
     * 
     * @author Henry Hu
     */
    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }
    
}
