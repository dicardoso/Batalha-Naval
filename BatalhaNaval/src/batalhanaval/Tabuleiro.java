package batalhanaval;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import java.util.InputMismatchException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe responsável por criar a matriz e iniciar o mapa 10x10
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class Tabuleiro {

    final int[][] tabuleiro = new int[10][10];
    int linha, coluna;
    String nome;
    int pos = 0;//Armazena o número de posições que VÃO sendo acertadas
    int nNavios = 0;//Especifica o número de navios no tabuleiro
    int casas = 0;//Armazena o número total de casas, ocupadas por navios, no tabuleiro

    Submarino sub;
    Torpedeiro torp;
    Fragata frag;
    Encouracado enc;
    PortaAvioes port;
    
    public Tabuleiro() {
    }

    public Tabuleiro(String nome) {
        this.nome = nome;
    }
    Scanner ent = new Scanner(System.in);

    public void inciaNavios() {
        sub = new Submarino();
        torp = new Torpedeiro();
        frag = new Fragata();

        {//SUBMARINO           
            sub.consultaUsuario();
            tabuleiro[sub.linhaSub][sub.colunaSub] = -1;
            casas += 1;            
        }

        {//TORPEDEIRO
            torp.consultaUsuario();
            torp.setDirecao();
            for (int i = 0; i < torp.tamanhoTorp; i++) {
                tabuleiro[torp.linhaTorp][torp.colunaTorp] = -1;
                torp.bussola();
            }
            casas += 2;
        }
        
        {//FRAGATA
            frag.consultaUsuario();
            frag.setDirecao();
            for (int i = 0; i < frag.tamanhoFrag; i++) {
                tabuleiro[frag.linhaFrag][frag.colunaFrag] = -1;
                frag.bussola();
            }
            casas += 3;
        }
        if (nNavios == 4){//ENCOURAÇADO
            enc = new Encouracado ();
            enc.consultaUsuario();
            enc.setDirecao();
            for (int i = 0; i < enc.tamanhoEnc; i++) {
                tabuleiro[enc.linhaEnc][enc.colunaEnc] = -1;
                enc.bussola();
            }
            casas += 4;
        }
        else if (nNavios == 5){//PROTA AVIÕES
            port = new PortaAvioes ();
            port.consultaUsuario();
            port.setDirecao();
            for (int i = 0; i < enc.tamanhoEnc; i++) {
                tabuleiro[enc.linhaEnc][enc.colunaEnc] = -1;
                port.bussola();
            }
            casas += 4;
        }
    }
    
    public void iniciaTabuleiro() {//Perguntar quantos navios quer usar
        for (linha = 0; linha < 10; linha++) {
            for (coluna = 0; coluna < 10; coluna++) {
                tabuleiro[linha][coluna] = -2;
            }
        }
    }

    public void exibeTabuleiro() {       
        System.out.println(nome);
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for (linha = 0; linha < 10; linha++) {
            System.out.print((linha + 1));
            for (coluna = 0; coluna < 10; coluna++) {
                if (tabuleiro[linha][coluna] == -2) {
                    System.out.print("\t" + "~");
                } else if (tabuleiro[linha][coluna] == -1) {
                    System.out.print("\t" + "N");
                } else if (tabuleiro[linha][coluna] == 1) {
                    System.out.print("\t" + "*");
                } else if (tabuleiro[linha][coluna] == 2) {
                    System.out.print("\t" + "A");
                }
            }
            System.out.print("\n");
        }
    }

    public void darTiro(int lin, int col) {
        lin--;
        col--;
        if ((tabuleiro[lin][col] == 2) || (tabuleiro[lin][col] == 1)) {
            System.out.println("Posição já escolhida!\nEscolha outra posição");
        } else if (tabuleiro[lin][col] == -2) {//Água
            tabuleiro[lin][col] = 2;
            tocarAudio("C:\\Users\\Diogo Cardoso\\Documents\\NetBeansProjects\\BatalhaNaval\\musicas\\wateresplash.wav");
            System.out.println("\nÁGUA");
        } else if (tabuleiro[lin][col] == -1) {//Navio
            tabuleiro[lin][col] = 1;
            tocarAudio("C:\\Users\\Diogo Cardoso\\Documents\\NetBeansProjects\\BatalhaNaval\\musicas\\bombacaindo.wav");
            System.out.println("\nFOGO");
            pos++;
        }
    }
    public void naufragios (){
        sub.naufragio();
        torp.naufragio();
        frag.naufragio();
        if (nNavios == 4){
            enc.naufragio();
        }
        if (nNavios == 4){
            port.naufragio();
        }
    }
    public static void tocarAudio(String local){
    try {
            InputStream arq = new FileInputStream(local);
            AudioPlayer.player.start(new AudioStream(arq));
           
        } catch (IOException e) {
            System.out.println("Erro na execução do áudio de abertura");
        }
}
}
