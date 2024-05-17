package com.example.tictactoe;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameLogic {

    /***1st element --> row , 2nd element --> col m 3rd element --> line type***/
    private int[] winType = {-1, -1, -1};
    private int[][] gameBoard;
    private int player = 1;
    private String[] playersNames = {"Player 1", "Player 2"};
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private int boardFill;
    private GameDisplay game;
    private String playerName;
    private Context context;

    /***Constructor***/
    public GameLogic(Context context) {
        this.context = context;
        this.game = new GameDisplay();
        this.gameBoard = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                this.gameBoard[r][c] = 0;
            }
        }
    }

    public void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                this.gameBoard[r][c] = 0;
            }
        }

        player = 1;
        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        playerTurn.setText((playersNames[0] + " " + context.getString(R.string.turn)));
    }


    public boolean winnerCheck() {
        boolean isWinner = false;

        //Horizontal check (winType == 1)
        for (int r = 0; r < 3; r++) {
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0) {
                winType = new int[]{r, 0, 1};
                isWinner = true;
            }
        }

        //VerticalCheck (winType == 2)
        for (int c = 0; c < 3; c++) {
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] && gameBoard[0][c] != 0) {
                winType = new int[]{0, c, 2};
                isWinner = true;
            }
        }

        //negative diagonal check (winType == 3)
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0) {
            winType = new int[]{0, 2, 3};
            isWinner = true;
        }

        //positive diagonal check(win Type == 4)
        if (gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] != 0) {
            winType = new int[]{2, 2, 4};
            isWinner = true;
        }

        boardFill = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (gameBoard[r][c] != 0) {
                    boardFill += 1;
                }
            }
        }
        if (isWinner) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((playersNames[player - 1] + " "+context.getString(R.string.won)));
            playerName = playersNames[player - 1];
            return true;
        } else if (boardFill == 9) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((playersNames[player - 1] + " " +context.getString(R.string.tied_game)));
            return true;
        } else {
            return false;
        }
    }


    public boolean updateGameBoard(int row , int col){
        if (this.gameBoard[row-1][col-1] == 0){
            this.gameBoard[row-1][col-1] = this.player;
            if(player == 1 ){
                playerTurn.setText((playersNames[1] + " " + context.getString(R.string.turn)));
            }else{
                playerTurn.setText((playersNames[0] + " " + context.getString(R.string.turn)));
            }
            return true;
        } else {
            return false;
        }
    }

    /***Getters***/
    public int[][] getGameBoard() { return gameBoard; }
    public int getPlayer() { return player; }
    public String getPlayerName() { return playerName; }
    public Button getPlayAgainBtn() { return playAgainBtn; }
    public Button getHomeBtn() { return homeBtn; }
    public TextView getPlayerTurn() { return playerTurn; }
    public int[] getWinType() { return winType; }
    public int getBoardFill() {return boardFill;}


    /***Setters***/
    public void setPlayer(int player) { this.player = player; }
    public void setPlayAgainBtn(Button playAgainBtn) { this.playAgainBtn = playAgainBtn; }
    public void setHomeBtn(Button homeBtn) { this.homeBtn = homeBtn; }
    public void setPlayerTurn(TextView playerTurn) { this.playerTurn = playerTurn; }
    public void setPlayerNames(String[] players) { this.playersNames = players; }
    public void setBoardFill(int boardFill) {this.boardFill = boardFill;}
}
