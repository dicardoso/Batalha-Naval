package batalhanaval;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por criar a Fragata
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class Fragata implements INavios {

    final String nome = "Fragata";
    int tamanhoFrag = 3;
    int linhaFrag = 0;
    int colunaFrag = 0;
    String direcaoFrag;

    private int acertosFrag = 0;

    public Fragata() {
    }

    public Fragata(int linha, int coluna) {//Construtor
        this.linhaFrag = linha;
        this.colunaFrag = coluna;
    }

    Scanner entFrag;

    @Override
    public void consultaUsuario() {
        boolean status = false;
        for (;;) {
            try {
                System.out.println(nome);
                entFrag = new Scanner(System.in);
                System.out.println("Linha:");
                linhaFrag = entFrag.nextInt() - 1;
                System.out.println("Coluna:");
                colunaFrag = entFrag.nextInt() - 1;

                if ((linhaFrag > 10) || (colunaFrag > 10) || (linhaFrag < 0) || (colunaFrag < 0)) {
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
    public void setDirecao() {
        while (true) {
            System.out.println("Escolha a direção do submarino\n"
                    + "N-Norte   S-Sul   O-Oeste   L-Leste");
            direcaoFrag = entFrag.next();
            if ((direcaoFrag.equals("N")) && (linhaFrag - tamanhoFrag) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaFrag = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoFrag.equals("S")) && (linhaFrag + tamanhoFrag) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaFrag = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoFrag.equals("L")) && (colunaFrag + tamanhoFrag) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaFrag = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoFrag.equals("O")) && (colunaFrag - tamanhoFrag) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaFrag = -2;
                consultaUsuario();
                continue;
            }
            else{
                break;
            }
        }
    }

    @Override
    public void bussola() {
        switch (direcaoFrag) {
            case "N":
                linhaFrag--;
                break;
            case "S":
                linhaFrag++;
                break;
            case "O":
                colunaFrag--;
                break;
            case "L":
                colunaFrag++;
                break;
        }
    }

    @Override
    public int acertos(int tiroLinha, int tiroColuna) {
        if ((tiroLinha == linhaFrag) && (tiroColuna == colunaFrag)) {
            acertosFrag++;
        }
        return acertosFrag;
    }

    @Override
    public void naufragio() {
        if (acertosFrag == tamanhoFrag) {
            System.out.println("Seu navio náufragou!!!");
        }
    }
}