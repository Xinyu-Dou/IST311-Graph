import com.sun.jdi.DoubleValue;

import java.util.*;

public class LocationGraph {
    protected ArrayList<Vertex> vertices;

    public LocationGraph() {
        this.vertices = new ArrayList<>();
    }

    public boolean addLocation(String location){
        if(!containsName(location)){
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
            index = this.getIndexOfVertex(locationA);
            start = this.vertices.get(index);
        }

        index = this.getIndexOfVertex(locationB);
        if(index >= 0){
            end = this.vertices.get(index);
        } else{
            this.addLocation(locationB);
            end = this.vertices.get(getIndexOfVertex(locationB));
        }

        Edge toAddStart = new Edge(end,distance);
        Edge toAddEnd = new Edge(start,distance);
        if(!start.containsEdge(toAddStart) && !end.containsEdge(toAddEnd)){
            start.edges.add(toAddStart);
            end.edges.add(toAddEnd);
            return true;
        }
        return false;
    }

    public Double findDistanceBreadthFirst(String locationA, String locationB){
        ArrayList<Vertex> visited = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        Queue<Double> distance = new LinkedList<>();

        if(Objects.equals(locationA, locationB)){
            return -1.0;
        }

        if(!containsName(locationA) || !containsName(locationB)){
            return -1.0;
        }

        int index = this.getIndexOfVertex(locationA);

        queue.add(this.vertices.get(index));
        visited.add(this.vertices.get(index));
        distance.add(0.0);

        while(!queue.isEmpty()){
            Vertex current = queue.remove();
            Double curr_dis = distance.remove();

            if(current.locationName.equals(locationB)){
                return curr_dis;
            }

            for(Edge edge: current.edges){
                if(!visited.contains(edge.vertex)){
                    queue.add(edge.vertex);
                    visited.add(edge.vertex);
                    distance.add(curr_dis + edge.distance);
                }
            }

        }
        return -1.0;
    }

    public Double findDistanceDepthFirst(String locationA, String locationB){
        ArrayList<Vertex> visited = new ArrayList<>();
        Stack<Vertex> stack = new Stack();
        Stack<Double> distance = new Stack();

        if(Objects.equals(locationA, locationB)){
            return -1.0;
        }

        if(!containsName(locationA) || !containsName(locationB)){
            return -1.0;
        }

        int index = this.getIndexOfVertex(locationA);

        stack.push(this.vertices.get(index));
        visited.add(this.vertices.get(index));
        distance.add(0.0);

        while(!stack.isEmpty()){
            Vertex current = stack.pop();
            Double curr_dis = distance.pop();

            if(current.locationName.equals(locationB)){
                return curr_dis;
            }

            for(Edge edge: current.edges){
                if(!visited.contains(edge.vertex)){
                    stack.push(edge.vertex);
                    visited.add(edge.vertex);
                    Double new_dis = 0.0;
                    new_dis = curr_dis + edge.distance;
                    distance.add(new_dis);
                }
            }

        }
        return -1.0;
    }

    public Boolean detectCycle(){
        ArrayList<Vertex> visited = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        Queue<Vertex> parent = new LinkedList<>();
        for (Vertex toCycle: this.vertices){
            visited.add(toCycle);
            queue.add(toCycle);
            parent.add(toCycle);
            while(!queue.isEmpty()) {
                Vertex current = queue.remove();
                Vertex f_current = parent.remove();
                for(Edge edge: current.edges){
                    if(edge.vertex.getName().equals(f_current.getName())){
                        continue;
                    }
                    if(!visited.contains(edge.vertex)){
                        queue.add(edge.vertex);
                        visited.add(edge.vertex);
                        parent.add(current);
                    }else{
                        return true;
                    }
                }
            }
            //delete all the things in arraylist and queue
            queue.clear();
            visited.clear();
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

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\t");
        for(int x = 0; x < this.vertices.size(); x++){
            Vertex a = this.vertices.get(x);
            str.append(a.getName());
            str.append("\t");
        }
        str.append("\n");
        for(int x = 0; x < this.vertices.size(); x++){
            Vertex a = this.vertices.get(x);
            str.append(a.getName());
            str.append("\t");
            for(int y = 0; y < this.vertices.size(); y++){
                Vertex b = this.vertices.get(y);
                str.append(String.format(" %.2f",findDistanceBreadthFirst(a.getName(),b.getName())));
                str.append("\t");
            }
            str.append('\n');
        }
        return str.toString();
    }



}
