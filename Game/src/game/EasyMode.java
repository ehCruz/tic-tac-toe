/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class EasyMode {

    List<Integer> aleatorio;

    public List<Integer> verificaAleatorio() {
        boolean cond = true;
        while (cond) {
            aleatorio = new ArrayList<>();
            aleatorio = geraAleatorio();
            if (Jogo.matizPosicoes[aleatorio.get(0)][aleatorio.get(1)] != 1
                    && Jogo.matizPosicoes[aleatorio.get(0)][aleatorio.get(1)] != 2) {
                Jogo.matizPosicoes[aleatorio.get(0)][aleatorio.get(1)] = 2;
                cond = false;
            }
        }
        return aleatorio;
    }

    private List<Integer> geraAleatorio() {
        List<Integer> rand = new ArrayList<>();
        int randLinha = (int) (Math.random() * 3);
        int randColuna = (int) (Math.random() * 3);

        rand.add(randLinha);
        rand.add(randColuna);
        return rand;
    }
}
