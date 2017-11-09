import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	boolean gameOver= false;
	int score=0;
	int hiscore =0;

	public GamePanel() {
		setBackground(Color.WHITE);

	}

	public void setScore(int score){
		this.score = score;
		if(score>hiscore)hiscore=score;
	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GameFrame.snake.paint(g);
		if(gameOver){
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("GameOver",GameFrame.width/2-280,200);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("Space to Restart",GameFrame.width/2-230,300);
			g.setColor(Color.RED);
			g.drawString("Score " + String.valueOf(score),GameFrame.width/2-130,110);
		}

	}
}