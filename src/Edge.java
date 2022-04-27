

public class Edge implements Comparable<Edge>{
    //the edge points to this edge
    Vertex vertex;
    Double distance;

    public Edge(Vertex vertex, Double distance){
        this.vertex = vertex;
        this.distance = distance;
    }

    public Double getWeight(){
        return distance;
    }

    public void setName(Double distance){
        this.distance = distance;
    }

    public Vertex getVertex(){
        return vertex;
    }

    public void setEdge(Vertex vertex){
        this.vertex = vertex;
    }


    @Override
    public int compareTo(Edge o) {
        if (this.vertex.equals(o.vertex)){
            if(this.distance.equals(o.distance)){
                return 1;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("-- %.2f  -> %s  ", this.getWeight(),this.vertex.getName()));

        return str.toString();
    }
}
