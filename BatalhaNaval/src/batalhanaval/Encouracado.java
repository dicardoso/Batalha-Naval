package batalhanaval;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por criar o Encouraçado
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class Encouracado implements INavios {

    final String nome = "Encouraçado";
    int tamanhoEnc = 4;
    int linhaEnc = 0;
    int colunaEnc = 0;
    String direcaoEnc;

    private int acertosEnc = 0;

    public Encouracado() {
    }

    public Encouracado(int linha, int coluna) {//Construtor
        this.linhaEnc = linha;
        this.colunaEnc = coluna;
    }

    Scanner entEnc;

    @Override
    public void consultaUsuario() {
        boolean status = false;
        for (;;) {
            try {
                System.out.println(nome);
                entEnc = new Scanner(System.in);
                System.out.println("Linha:");
                linhaEnc = entEnc.nextInt() - 1;
                System.out.println("Coluna:");
                colunaEnc = entEnc.nextInt() - 1;

                if ((linhaEnc > 10) || (colunaEnc > 10) || (linhaEnc < 0) || (colunaEnc < 0)) {
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
            direcaoEnc = entEnc.next();
            if ((direcaoEnc.equals("N")) && (linhaEnc - tamanhoEnc) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaEnc = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoEnc.equals("S")) && (linhaEnc + tamanhoEnc) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaEnc = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoEnc.equals("L")) && (colunaEnc + tamanhoEnc) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaEnc = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoEnc.equals("O")) && (colunaEnc - tamanhoEnc) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaEnc = -2;
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
        switch (direcaoEnc) {
            case "N":
                linhaEnc--;
                break;
            case "S":
                linhaEnc++;
                break;
            case "O":
                colunaEnc--;
                break;
            case "L":
                colunaEnc++;
                break;
        }
    }

    @Override
    public int acertos(int tiroLinha, int tiroColuna) {
        if ((tiroLinha == linhaEnc) && (tiroColuna == colunaEnc)) {
            acertosEnc++;
        }
        return acertosEnc;
    }

    @Override
    public void naufragio() {
        if (acertosEnc == tamanhoEnc) {
            System.out.println("Seu navio náufragou!!!");
        }
    }
}
