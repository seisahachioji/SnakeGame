import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener {

	final String title = "Snakegame";
	static int width = 960;
	static int height = 540;
	static GamePanel gamePanel;
	static Snake snake;
	boolean space = false;

	int input = 0;

	public GameFrame() {
		init();
		setTitle(title);
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.WHITE);
		getContentPane().add(gamePanel);
		addKeyListener(this);
		while (true)
			loop();
	}

	public void gameover() {
        gamePanel.setScore(snake.score);
		gamePanel.gameOver = true;
		repaint();
		space = true;
		while (space) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		snake.init();
		restart();
	}

	public void restart(){
		snake = new Snake();
		gamePanel.gameOver = false;
	}

	public void loop() {
		if (snake.move(input))
			gameover();
		repaint();
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void init() {
		snake = new Snake();
		gamePanel = new GamePanel();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_RIGHT && keycode == KeyEvent.VK_LEFT) {
			input = 3;
		} else if (keycode == KeyEvent.VK_RIGHT) {
			input = 1;
		} else if (keycode == KeyEvent.VK_LEFT) {
			input = 2;
		}
		if (keycode == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_RIGHT && keycode == KeyEvent.VK_LEFT) {
			input = 3;
		} else if (keycode == KeyEvent.VK_RIGHT) {
			if (input == 0) {
				input = 1;
			} else if (input == 2) {
				input = 3;
			}
		} else if (keycode == KeyEvent.VK_LEFT) {
			if (input == 0) {
				input = 2;
			} else if (input == 1) {
				input = 3;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		//		if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_LEFT)
		//			input = 0;

		if (keycode == KeyEvent.VK_RIGHT) {
			if (input == 1) {
				input = 0;
			} else if (input == 3) {
				input = 2;
			}
		} else if (keycode == KeyEvent.VK_LEFT) {
			if (input == 2) {
				input = 0;
			} else if (input == 3) {
				input = 1;
			}
		}

	}



	public static void main(String[] args) {
		GameFrame gameframe = new GameFrame();
	}
}
