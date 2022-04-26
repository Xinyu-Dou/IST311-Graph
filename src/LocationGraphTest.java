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
        assertFalse(graph.addLocation("Wuhan"));

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
        assertTrue(graph.addDistance("State College", "New York", 530.2));

    }

    @Test
    void containsName() {
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
        System.out.println(graph.findDistanceBreadthFirst("Wuhan","State College"));

    }



}