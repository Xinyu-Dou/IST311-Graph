import java.util.ArrayList;

public class Vertex {
    String locationName;
    ArrayList<Edge> edges;

    public Vertex(String name){
        this.locationName = name;
        this.edges = new ArrayList<>();
    }

    public String getName(){
        return locationName;
    }

    public void setName(String name){
        this.locationName = name;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public void setEdge(ArrayList<Edge> edges){
        this.edges = edges;
    }

    public boolean containsEdge(Edge toFind){
        for(int i = 0; i < this.edges.size(); i++){
            Edge edge = this.edges.get(i);
            if(edge.compareTo(toFind) == 1){
                return true;
            };

        }
        return false;
    }
}
