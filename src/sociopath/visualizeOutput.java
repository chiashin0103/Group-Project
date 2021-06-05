package sociopath;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class visualizeOutput extends JPanel {

    private int shapeHeight;
    private int shapeWidth;

    ArrayList<vertex> vertex;
    ArrayList<edges> edge;

    public visualizeOutput() {
        vertex = new ArrayList<vertex>();
        edge = new ArrayList<edges>();
        shapeWidth = 45;
        shapeHeight = 45;
    }
    
    public void paint(Graphics graph) {
        FontMetrics fontmetrics = graph.getFontMetrics();
        int ovalHeight = Math.max(shapeHeight, fontmetrics.getHeight());
        int ovalWidth = Math.max(shapeWidth, fontmetrics.getLeading() + shapeWidth / 2);

        graph.setColor(Color.black);
        for (edges ed : edge) {
            int e1 = vertex.get(ed.q).u;
            int e2 = vertex.get(ed.q).v;
            int e3 = vertex.get(ed.w).u;
            int e4 = vertex.get(ed.w).v;
            graph.drawLine(e1, e2, e3, e4);
        }

        for (vertex vertex : vertex) {
            int x = vertex.u - ovalWidth / 2;
            int y = vertex.v - ovalHeight / 2;
            int wording = vertex.u - fontmetrics.stringWidth(vertex.vertexId) / 2;
            int wording2 = vertex.v + fontmetrics.getHeight() / 2;
            graph.setColor(Color.cyan);
            graph.fillOval(x, y, ovalWidth, ovalHeight);

            graph.setColor(Color.black);
            graph.drawOval(x, y, ovalWidth, ovalHeight);

            graph.drawString(vertex.vertexId, wording, wording2);
        }

    }

    public void addVertex(String vertexId, int u, int v) {
        vertex.add(new vertex(vertexId, u, v));
        this.repaint();
    }

    public void addEdge(int u, int v) {
        edge.add(new edges(u, v));
         edge.add(new edges(v, u));
        this.repaint();
    }

    class vertex {

        String vertexId;
        int u, v;

        public vertex() {
        }

        public vertex(String vertexId, int u, int v) {
            this.vertexId = vertexId;
            this.u = u;
            this.v = v;
        }

    }

    class edges {

        int q, w;

        public edges() {
        }

        public edges(int q, int w) {
            this.q = q;
            this.w = w;
        }

    }

}
