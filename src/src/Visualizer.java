package src;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.List;

public class Visualizer{

    public Visualizer(Solver solver) {
        JFrame frame = new MyFrame(solver);
        frame.setVisible(true);
    }

    public class MyFrame extends JFrame {

        Solver solver;
        int[] X;
        int[] Y;
        List<List<Integer>> routes;

        public MyFrame(Solver solver) {
            this.solver = solver;
            this.X = solver.getX();
            this.Y = solver.getY();
            this.routes = solver.constructRoute();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Routes Visualization");
            setSize(1000,1000);
            setLocation(new Point(0,0));
            setVisible(true);
            setBackground(Color.black);
        }

        public void paint(Graphics g) {
            for (int k = 0; k < routes.size(); k++) {
                paintRoute(g, routes.get(k),
                        new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)),
                        8);
            }
        }

        /**
         * Plot one route by connecting nodes in List<Integer> route, with random color
         * @param g graphics
         * @param route list of nodes on one route
         * @param color random color
         * @param scale enlarge/shrink the distance between nodes
         */
        private void paintRoute(Graphics g, List<Integer> route, Color color, int scale) {
            g.setColor(color);
            for (int i = 0; i < route.size(); i++) {
                int node = route.get(i);
                g.fillRect(X[node]*scale - 1, Y[node]*scale - 1, 2, 2);
                if (i != route.size() - 1) {
                    g.drawLine(X[node]*scale, Y[node]*scale, X[route.get(i + 1)]*scale, Y[route.get(i + 1)]*scale);
                }
            }
        }
    }
}