package batalhanaval;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por criar o Submarino
 * @author Diogo Gomes e Wellington José 
 * @version 1.0
 */
public class Submarino implements INavios {

    final String nomeSub = "Submarino";
    int tamanhoSub = 1;
    int linhaSub = 0;
    int colunaSub = 0;
    String direcaoSub;

    private int acertosSub = 0;

    public Submarino() {
    }

    public Submarino(int linha, int coluna) {//Construtor
        this.linhaSub = linha;
        this.colunaSub = coluna;
    }

    Scanner entSub;

    @Override
    public void consultaUsuario() {
        boolean status = false;
        for (;;) {
            try {
                System.out.println(nomeSub);
                entSub = new Scanner(System.in);
                System.out.println("Linha:");
                linhaSub = entSub.nextInt() - 1;
                System.out.println("Coluna:");
                colunaSub = entSub.nextInt() - 1;

                if ((linhaSub > 10) || (colunaSub > 10)||(linhaSub < 0) || (colunaSub < 0)) {
                    System.out.println("ERRO!!!\nDigite um valor numérico entre 1 e 10");
                    continue;
                } else {
                    status = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO!!!\nDigite um valor numérico entre 1 e 10");
            }
            if (status) {
                break;
            }
        }
    }

    @Override
    public int acertos(int tiroLinha, int tiroColuna) {
        if ((tiroLinha == linhaSub) && (tiroColuna == colunaSub)) {
            acertosSub++;
        }
        return acertosSub;
    }

    @Override
    public void naufragio() {
        if (acertosSub == tamanhoSub) {
            System.out.println("Seu navio náufragou!!!");
        }
    }

    @Override
    public void bussola() {
    }

    @Override
    public void setDirecao() {
    }
}
