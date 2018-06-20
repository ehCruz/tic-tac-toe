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
 * Codigo adaptado do algoritmo Minimax, que verifica qual e a
 * melhor jogada para a maquina realizar, entao o jogo se torna IMPOSSIVEL de se
 * ganhar. Mais infos e uma otima explicaçao de como o algoritmo funciona:
 * https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
 */
public class Minimax {

    private boolean isMovesLeft(int[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int evaluate(int[][] tabuleiro) {
        for (int row = 0; row < 3; row++) {
            if (tabuleiro[row][0] == tabuleiro[row][1] && tabuleiro[row][1] == tabuleiro[row][2]) {
                if (tabuleiro[row][0] == 1) {
                    return +10;
                } else if (tabuleiro[row][0] == 0) {
                    return -10;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (tabuleiro[0][col] == tabuleiro[1][col] && tabuleiro[1][col] == tabuleiro[2][col]) {
                if (tabuleiro[0][col] == 1) {
                    return +10;
                } else if (tabuleiro[0][col] == 0) {
                    return -10;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            if (tabuleiro[0][0] == 1) {
                return +10;
            } else if (tabuleiro[0][0] == 0) {
                return -10;
            }
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            if (tabuleiro[0][2] == 1) {
                return +10;
            } else if (tabuleiro[0][2] == 0) {
                return -10;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private int minimax(int[][] tabuleiro, int depth, boolean isMax) {
        int score = evaluate(tabuleiro);
        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10) {
            return score;
        }

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10) {
            return score;
        }

        // If there are no more moves and no winner then
        // it is a tie
        if (isMovesLeft(tabuleiro) == false) {
            return 0;
        }

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (tabuleiro[i][j] == 0) {
                        // Make the move
                        tabuleiro[i][j] = 1;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = minimax(tabuleiro, depth + 1, !isMax);

                        // Undo the move
                        tabuleiro[i][j] = 0;
                    }
                }
            }
            return best;
        } // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (tabuleiro[i][j] == 0) {
                        // Make the move
                        tabuleiro[i][j] = 2;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = minimax(tabuleiro, depth + 1, !isMax);

                        // Undo the move
                        tabuleiro[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

    public List<Integer> findBestMove(int[][] tabuleiro) {
        List<Integer> melhorJogada = new ArrayList<>();
        int bestVal = -1000;
        int bestRow = -1;
        int bestCol = -1;

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if celll is empty
                if (tabuleiro[i][j] == 0) {
                    // Make the move
                    tabuleiro[i][j] = 1;
                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(tabuleiro, 0, false);

                    // Undo the move
                    tabuleiro[i][j] = 0;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.println(bestVal);
        System.out.println("Row: " + bestRow + " - Col: " + bestCol);
        Jogo.matizPosicoes[bestRow][bestCol] = 2;
        melhorJogada.add(bestRow);
        melhorJogada.add(bestCol);

        return melhorJogada;
    }
}
