package batalhanaval;
import java.util.Scanner;
/**
 * Classe responsável por criar e iniciar os navios
 * @author Diogo Gomes Cardoso
 * @version 1.0
 */
public class Navios1 {
    int navio[][] = new int[10][10];
    Scanner entr = new Scanner(System.in);
    private int l=0,c=0;
    
    public int iniciaNavio(int i){
                        
        for(i=0;i < 3;i++){//O usuário posiciona os navios
            System.out.println("Digite as coordenadas do navio "+(i+1));
            System.out.print("Linha: ");
            l = entr.nextInt();
            System.out.print("Coluna: ");
            c = entr.nextInt();
            navio[l][c] = 1;
            if ((l>4)||(c>4)){
                System.out.println("Você digitou uma coordenada inexistente!!!\n"
                        + "Digite novamente");
                l = 0;
                c = 0;
                continue;
            }
        }
        return 0;
    }

}
