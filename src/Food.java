import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {

	int size = 30;
	int x;
	int y;
	Random rnd = new Random();

	public int[] init() {
		x = rnd.nextInt(850)+40;
		y = rnd.nextInt(440)+40;
		int re[] = { x, y };
		return re;
	}

	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}

	public Food() {

	}


}
