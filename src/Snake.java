import java.awt.*;

public class Snake {
    Food food = new Food();
    int angle = 0;
    int anglespeed = 8;
    int speed = 6;
    int size = 20;
    int score = 0;
    double[][] x = {{200, 195, 190, 185}, {180, 175, 170, 165}, {160, 155, 150, 145}};
    double[][] y = {{200, 200, 200, 200}, {200, 200, 200, 200}, {200, 200, 200, 200}};
    int marginx;
    int marginy;

    public Snake() {
        init();
    }

    public void init() {
        String osName = System.getProperty("os.name");
        if(osName.equals("Windows 10")){
            marginx = 17;
            marginy = 43;
        }else{
            marginx = 20;
            marginy = 48;

        }
        angle = 0;
        food.init();
    }

    public boolean move(int input) {

        for (int i = x.length - 1; i >= 0; i--) {
            x[i][3] = x[i][2];
            x[i][2] = x[i][1];
            x[i][1] = x[i][0];
            if (i != 0)
                x[i][0] = x[i - 1][3];
            y[i][3] = y[i][2];
            y[i][2] = y[i][1];
            y[i][1] = y[i][0];
            if (i != 0)
                y[i][0] = y[i - 1][3];
        }

        if (input == 1) {
            angle += anglespeed;
        } else if (input == 2) {
            angle -= anglespeed;
        }
        x[0][0] += Math.cos(angle * Math.PI / 180) * speed;
        y[0][0] += Math.sin(angle * Math.PI / 180) * speed;

        if (getDistance(x[0][0], y[0][0], food.x, food.y) < size / 2 + food.size / 2) {
            eat();
        }
        return checkGameover();
    }

    public void eat() {
        score++;
        int point[];
        boolean set = true;
        while (set) {
            set = false;
            point = food.init();
            for (int i = 0; i < x.length; i++) {
                if (getDistance(x[i][0], y[i][0], point[0], point[1]) < (size + food.size) * 2) {
                    set = true;
                }
            }
        }

        double[][] bufX = x.clone();
        double[][] bufY = y.clone();
        x = new double[x.length + 1][4];
        y = new double[y.length + 1][4];

        for (int i = 0; bufX.length > i; i++) {
            x[i] = bufX[i];
            y[i] = bufY[i];
        }

        x[x.length - 1] = x[x.length - 2].clone();
        y[y.length - 1] = y[y.length - 2].clone();
    }

    public void paint(Graphics g) {
        food.paint(g);
        g.setColor(Color.GREEN);
        for (int i = 0; i < x.length; i++) {
            g.fillOval((int) x[i][0] - size / 2, (int) y[i][0] - size / 2, size, size);
        }
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString( String.valueOf(score),10,20);
    }

    public boolean checkGameover() {
        for (int i = 0; i < x.length - 1; i++) {
            if (getDistance(x[0][0], y[0][0], x[i + 1][0], y[i + 1][0]) < size) {
                return true;
            }
        }
        if (x[0][0] < size / 2 || x[0][0] > GameFrame.width - size / 2 - marginx ||
                y[0][0] < size / 2 || y[0][0] > GameFrame.height - size / 2 - marginy)
            return true;
        return false;
    }

    protected int getDistance(double x, double y, double x2, double y2) {
        double distance = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));

        return (int) distance;
    }

}
