import java.util.*;

public class LocationGraph {
    protected ArrayList<Vertex> vertices;

    public LocationGraph() {
        this.vertices = new ArrayList<>();
    }

    public boolean addLocation(String location){
        if(containsName(location) == true){
            Vertex toAdd = new Vertex(location);
            this.vertices.add(toAdd);
            return true;
        }
        return false;
    }



    public boolean addDistance(String locationA, String locationB, Double distance){
        Vertex start;
        Vertex end;


        int index = this.getIndexOfVertex(locationA);
        if (index >= 0){
            start = this.vertices.get(index);
        } else{
            this.addLocation(locationA);
            start = this.vertices.get(getIndexOfVertex(locationA));
        }

        index = this.getIndexOfVertex(locationB);
        if(index >= 0){
            end = this.vertices.get(index);
        } else{
            this.addLocation(locationB);
            end = this.vertices.get(getIndexOfVertex(locationB));
        }

        Edge toAdd = new Edge(end,distance);
        if(start.containsEdge(toAdd)){
            start.edges.add(toAdd);
            return true;
        }
        return false;
    }



    protected boolean containsName(String name){
        return this.getIndexOfVertex(name) >= 0;
    }

    protected int getIndexOfVertex(String name){
        int index = 0;
        for (Vertex toTest: this.vertices){
            if (toTest.locationName.compareTo(name) == 0){
                return index;
            }
            index++;
        }
        return -1;
    }
}
