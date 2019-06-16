package batalhanaval;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe principal do jogo
 * @author Diogo Gomes Cardoso e Wellington José 
 * @version 1.0
 */
public class BatalhaNaval extends Tabuleiro {
    /*
    Adimitindo-se os seguintes valores:    
     -2 > Água
     -1 > Navio
     1 > Navio Atingido
     2 > Água Atingida
     */
static Clip clip;
    public static void tocarAudio(String local) {
        try {
            /*InputStream arq = new FileInputStream(local);
             AudioStream som = new AudioStream(arq);
             AudioPlayer.player.start(som);*/
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(local));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f); // Controla o volume (dB)
            clip.start();
            
        } catch (IOException e) {
            System.out.println("Erro na execução do áudio de abertura");  }
        catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(BatalhaNaval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        int lTiro = 0, cTiro = 0;
        int rodadas = 0;
        String vencedor = null;
        boolean status = false;

        tocarAudio("C:\\Users\\Diogo Cardoso\\Documents\\NetBeansProjects\\BatalhaNaval\\musicas\\Prepared For Adventure.wav");
        Tabuleiro t1 = new Tabuleiro("Jogador 1");
        Tabuleiro t2 = new Tabuleiro("Jogador 2");

        Scanner ent = new Scanner(System.in);
        for (;;) {
            try {
                System.out.println("### BATALHA NAVAL ###\nPressione qualquer tecla e depois ENTER");
                ent.next();
                status = true;
            } catch (InputMismatchException e1) {
                System.out.println("ERRO!!!\nDigite um valor numérico entre 1 e 10");
            }
            if (status) {
                break;
            }
        }
        t1.iniciaTabuleiro();
        t1.exibeTabuleiro();
        t1.inciaNavios();

        t2.iniciaTabuleiro();
        t2.exibeTabuleiro();
        t2.inciaNavios();
        do {
            System.out.println("Jogador 1 é sua vez\nPressione qualquer tecla e depois ENTER");
            ent.next();
            t1.exibeTabuleiro();
            //for (;;) {
            //  try {
            status = false;
            System.out.println(t1.nome + ", escolha um posição para atirar");
            System.out.println("Linha");
            lTiro = ent.nextInt();
            System.out.println("Coluna");
            cTiro = ent.nextInt();
            //} catch (InputMismatchException e1) {
            //System.out.println("ERRO!!!\nDigite um valor numérico entre 1 e 10");
            //}
            //if (status) {
            //  break;
            //}
            //}
            t2.darTiro(lTiro, cTiro);//Lembrar de subtrair 1 na leitura dos dados
            t2.naufragios();
            if (t2.pos == t2.casas) {
                vencedor = "Jogador 1";
                break;
            }

            System.out.println("Jogador 2 é sua vez\nPressione qualquer tecla e depois ENTER");
            ent.next();
            t2.exibeTabuleiro();
            /*for (;;) {
             try {*/
            status = false;
            System.out.println(t2.nome + ", escolha um posição para atirar");
            System.out.println("Linha");
            lTiro = ent.nextInt();
            System.out.println("Coluna");
            /*} catch (InputMismatchException e1) {
             System.out.println("ERRO!!!\nDigite um valor numérico entre 1 e 10");
             }
             if (status) {
             break;
             }
             }*/
            cTiro = ent.nextInt();
            t1.darTiro(lTiro, cTiro);//Lembrar de subtrair 1 na leitura dos dados
            t1.naufragios();
            if (t1.pos == t1.casas) {
                vencedor = "Jogador 2";
                break;
            }
            rodadas++;
        } while ((t1.pos != t1.casas) || (t2.pos != t2.casas));
        tocarAudio("C:\\Users\\Diogo Cardoso\\Documents\\NetBeansProjects\\BatalhaNaval\\musicas\\tada.wav");
        System.out.println("\nFim de jogo\n" + vencedor + " é o vencedor da partida");
        System.out.println("Rodadas jogadas: " + rodadas);
    }
}
