package batalhanaval;

/**
 * Classe de exceção
 * @author Diogo Cardoso e Wellington José 
 * @version 1.0
 */
public class BatalhaException extends Exception {
    
    public String getMessage (){
        return "Digite um valor entre 1 e 10";
    }   
}
