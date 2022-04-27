import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class LocationGraphTest {

    @Test
    void addLocation() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("Wuhan");
        graph.addLocation("New York");
        graph.addLocation("State College");
        graph.addLocation("Portland");
        //adding an existing location, return false
        assertFalse(graph.addLocation("Wuhan"));
        //adding a new location, return true
        assertTrue(graph.addLocation("Miami"));

    }

    @Test
    void addDistance() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("Wuhan");
        graph.addLocation("New York");
        graph.addLocation("State College");
        graph.addLocation("Portland");
        graph.addDistance("Wuhan", "Portland", 1230.2);
        graph.addDistance("Portland", "State College", 230.2);
        //adding a new edge, return true
        assertTrue(graph.addDistance("State College", "New York", 530.2));
        //adding an existing edge, return false
        assertFalse(graph.addDistance("Wuhan", "Portland", 1230.2));
    }

    @Test
    void findDistanceBreadthFirst() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("Wuhan");
        graph.addLocation("New York");
        graph.addLocation("State College");
        graph.addLocation("Portland");
        graph.addDistance("Wuhan", "Portland", 10.0);
        graph.addDistance("Wuhan", "New York", 20.0);
        graph.addDistance("Portland", "State College", 40.0);
        Double result = 50.0;
        Double result2 = -1.0;
        assertEquals(result, graph.findDistanceBreadthFirst("Wuhan","State College"));
        assertEquals(result2, graph.findDistanceBreadthFirst("New York","State College"));
        assertEquals(result2, graph.findDistanceBreadthFirst("Miami","State College"));
    }


    @Test
    void findDistanceDepthFirst() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("Wuhan");
        graph.addLocation("New York");
        graph.addLocation("State College");
        graph.addLocation("Portland");
        graph.addDistance("Wuhan", "Portland", 10.0);
        graph.addDistance("Wuhan", "New York", 20.0);
        graph.addDistance("Portland", "State College", 40.0);
        System.out.println(graph.findDistanceDepthFirst("Wuhan","State College"));
    }

    @Test
    void detectCycle() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("State College");
        graph.addDistance("Wuhan", "Portland", 10.0);
        graph.addDistance("Wuhan", "New York", 20.0);
        graph.addDistance("Atlanta", "Miami", 40.0);
        graph.addDistance("Miami", "Atlanta", 40.0);
        System.out.println(graph.detectCycle());
        System.out.println(graph.toString());
    }
}