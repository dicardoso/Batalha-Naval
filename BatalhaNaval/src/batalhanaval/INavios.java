package batalhanaval;
/**
 * Interface base para todos os navios
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public interface INavios{
    
    public abstract void consultaUsuario() throws BatalhaException;
    
    public abstract void setDirecao();
    
    public abstract void bussola();
        
    public abstract int acertos (int tiroLinha, int tiroColuna);
    
    public abstract void naufragio ();     
    
}
