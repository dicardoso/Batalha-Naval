package batalhanaval;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por criar o Porta-aviões
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class PortaAvioes implements INavios {

    final String nome = "Porta Aviões";
    int tamanhoPort = 5;
    int linhaPort = 0;
    int colunaPort = 0;
    String direcaoPort;

    private int acertosPort = 0;

    public PortaAvioes() {
    }

    public PortaAvioes(int linha, int coluna) {//Construtor
        this.linhaPort = linha;
        this.colunaPort = coluna;
    }

    Scanner entPort;

    @Override
    public void consultaUsuario() {
        boolean status = false;
        for (;;) {
            try {
                System.out.println(nome);
                entPort = new Scanner(System.in);
                System.out.println("Linha:");
                linhaPort = entPort.nextInt() - 1;
                System.out.println("Coluna:");
                colunaPort = entPort.nextInt() - 1;

                if ((linhaPort > 10) || (colunaPort > 10) || (linhaPort < 0) || (colunaPort < 0)) {
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
            direcaoPort = entPort.next();
            if ((direcaoPort.equals("N")) && (linhaPort - tamanhoPort) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaPort = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoPort.equals("S")) && (linhaPort + tamanhoPort) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                linhaPort = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoPort.equals("L")) && (colunaPort + tamanhoPort) > 10) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaPort = -2;
                consultaUsuario();
                continue;
            } else if ((direcaoPort.equals("O")) && (colunaPort - tamanhoPort) < 0) {
                System.out.println("Impossível colocar o Submarino nessa direção");
                colunaPort = -2;
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
        switch (direcaoPort) {
            case "N":
                linhaPort--;
                break;
            case "S":
                linhaPort++;
                break;
            case "O":
                colunaPort--;
                break;
            case "L":
                colunaPort++;
                break;
        }
    }

    @Override
    public int acertos(int tiroLinha, int tiroColuna) {
        if ((tiroLinha == linhaPort) && (tiroColuna == colunaPort)) {
            acertosPort++;
        }
        return acertosPort;
    }

    @Override
    public void naufragio() {
        if (acertosPort == tamanhoPort) {
            System.out.println("Seu navio náufragou!!!");
        }
    }
}