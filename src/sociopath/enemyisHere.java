
package sociopath;
public class enemyisHere<T> extends Graph {

    Vertex<T> head;
    int size;

    public enemyisHere() {
        head = null;
        size = 0;
    }

    //get number of vertices
    public int getSize() {
        return this.size;
    }

    //enemy vertex
    public boolean addEnemyVertex(VertexInfo<T> v) {
        if (hasEnemyVertex(v) == false) {
            Vertex<T> temp = head;
            Vertex<T> newVertex = new Vertex<>(v, null);
            if (head == null) {
                head = newVertex;
            } else {
                Vertex<T> previous = head;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    //check if has enemyVertex
    public boolean hasEnemyVertex(VertexInfo<T> v) {
        if (head == null) {
            return false;
        }
        Vertex<T> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.equals(v)) {
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }

    //check if has enemyEdge
    public boolean hasEnemyEdge(VertexInfo<T> source, VertexInfo<T> destination) {
        if (head == null) {
            return false;
        }
        if (!hasEnemyVertex(source) || !hasEnemyVertex(destination)) {
            return false;
        }
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.equals(source)) {
                Edge<T> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.equals(destination)) {
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    //add EnemyEdge
    public void addEnemyEdge(VertexInfo<T> source, VertexInfo<T> destination) {
        addUndirectedEnemyEdge(source,destination);
        addUndirectedEnemyEdge(destination,source);
    }
 public boolean addUndirectedEnemyEdge(VertexInfo<T> source, VertexInfo<T> destination) {
        if (head == null) {
            return false;
        }
        if (!hasEnemyVertex(source) || !hasEnemyVertex(destination)) {
            return false;
        }
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.equals(source)) {
                Vertex<T> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.equals(destination)) {
                        Edge<T> currentEdge = sourceVertex.firstEdge;
                        int r = 0;
                        Edge<T> newEdge = new Edge<>(destinationVertex, r, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

}

class frenemy<T> {

    Vertex<T> head;
    int size;

    public frenemy() {
        head = null;
        size = 0;
    }

    //frenemyVertex
    public boolean addFrenemyVertex(VertexInfo<T> v) {
        if (hasFrenemyVertex(v) == false) {
            Vertex<T> temp = head;
            Vertex<T> newVertex = new Vertex<>(v, null);
            if (head == null) {
                head = newVertex;
            } else {
                Vertex<T> previous = head;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    //check if has frenemyvertex
    public boolean hasFrenemyVertex(VertexInfo<T> v) {
        if (head == null) {
            return false;
        }
        Vertex<T> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.equals(v)) {
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }

    //check if has frenemyedge
    public boolean hasFrenemyEdge(VertexInfo<T> source, VertexInfo<T> destination) {
        if (head == null) {
            return false;
        }
        if (!hasFrenemyVertex(source) || !hasFrenemyVertex(destination)) {
            return false;
        }
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.equals(source)) {
                Edge<T> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.equals(destination)) {
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    //add frenemyEdge
    public boolean addFrenemyEdge(VertexInfo<T> source, VertexInfo<T> destination) {
        if (head == null) {
            return false;
        }
        if (!hasFrenemyVertex(source) || !hasFrenemyVertex(destination)) {
            return false;
        }
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.equals(source)) {
                Vertex<T> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.equals(destination)) {
                        Edge<T> currentEdge = sourceVertex.firstEdge;
                        int r = 0;
                        Edge<T> newEdge = new Edge<>(destinationVertex, r, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }
}

