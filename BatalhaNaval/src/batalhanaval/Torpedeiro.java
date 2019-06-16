package batalhanaval;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por criar o Torpedeiro
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class Torpedeiro implements INavios {

    final String nome = "Torpedeiro";
    int tamanhoTorp = 2;
    int linhaTorp = 0;
    int colunaTorp = 0;
    String direcaoTorp;

    private int acertosTorp = 0;

    public Torpedeiro() {
    }

    public Torpedeiro(int linha, int coluna) {//Construtor
        this.linhaTorp = linha;
        this.colunaTorp = coluna;
    }

    Scanner entTorp;

    @Override
    public void consultaUsuario() {
        boolean status = false;
        for (;;) {
            try {
                System.out.println(nome);
                entTorp = new Scanner(System.in);
                System.out.println("Linha:");
                linhaTorp = entTorp.nextInt() - 1;
                System.out.println("Coluna:");
                colunaTorp = entTorp.nextInt() - 1;

                if ((linhaTorp > 10) || (colunaTorp > 10) || (linhaTorp < 0) || (colunaTorp < 0)) {
                    System.out.println("\nERRO!!!\nDigite um valor numérico entre 1 e 10\n");
                    continue;
                } else {
                    status = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERRO!!!\nDigite um valor numérico entre 1 e 10\n");
            }
            if (status) {
                break;
            }
        }
    }

    @Override
    public void setDirecao() {
        while (true) {
            System.out.println("\nEscolha a direção do submarino\n"
                    + "N-Norte   S-Sul   O-Oeste   L-Leste");
            direcaoTorp = entTorp.next();           
            if ((direcaoTorp.equals("N")) || (direcaoTorp.equals("S")) || (direcaoTorp.equals("O")) || (direcaoTorp.equals("L"))) {
                if ((direcaoTorp.equals("N")) && (linhaTorp - tamanhoTorp) < 0) {
                    System.out.println("\nImpossível colocar o Submarino nessa direção\n");
                    linhaTorp = -2;
                    consultaUsuario();
                    continue;
                } else if ((direcaoTorp.equals("S")) && (linhaTorp + tamanhoTorp) > 10) {
                    System.out.println("\nImpossível colocar o Submarino nessa direção\n");
                    linhaTorp = -2;
                    consultaUsuario();
                    continue;
                } else if ((direcaoTorp.equals("L")) && (colunaTorp + tamanhoTorp) > 10) {
                    System.out.println("\nImpossível colocar o Submarino nessa direção\n");
                    colunaTorp = -2;
                    consultaUsuario();
                    continue;
                } else if ((direcaoTorp.equals("O")) && (colunaTorp - tamanhoTorp) < 0) {
                    System.out.println("\nImpossível colocar o Submarino nessa direção\n");
                    colunaTorp = -2;
                    consultaUsuario();
                    continue;
                } else {
                    break;
                }
            }//if
            else{
                System.out.println("\nDigite uma das opções válidas\n");
                continue;
            }
        }//while
    }//método

    @Override
    public void bussola() {
        switch (direcaoTorp) {
            case "N":
                linhaTorp--;
                break;
            case "S":
                linhaTorp++;
                break;
            case "O":
                colunaTorp--;
                break;
            case "L":
                colunaTorp++;
                break;
        }
    }

    @Override
    public int acertos(int tiroLinha, int tiroColuna) {
        if ((tiroLinha == linhaTorp) && (tiroColuna == colunaTorp)) {
            acertosTorp++;
        }
        return acertosTorp;
    }

    @Override
    public void naufragio() {
        if (acertosTorp == tamanhoTorp) {
            System.out.println("\nSeu navio náufragou!!!\n");
        }
    }
}
