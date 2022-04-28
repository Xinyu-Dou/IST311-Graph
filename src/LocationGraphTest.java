import jdk.swing.interop.SwingInterOpUtils;
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
        System.out.print(graph);
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
        //return distance if there is a distance between two locations
        assertEquals(result, graph.findDistanceBreadthFirst("Wuhan","State College"));
        //return -1.0 if there is no distance between two locations
        assertEquals(result2, graph.findDistanceBreadthFirst("New York","State College"));
        //return -1.0 if either location does not exist
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
        Double result = 50.0;
        Double result2 = -1.0;
        //return distance if there is a distance between two locations
        assertEquals(result, graph.findDistanceDepthFirst("Wuhan","State College"));
        //return -1.0 if there is no distance between two locations
        assertEquals(result2, graph.findDistanceDepthFirst("New York","State College"));
        //return -1.0 if either location does not exist
        assertEquals(result2, graph.findDistanceDepthFirst("Miami","State College"));
    }

    @Test
    void detectCycle() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("State College");
        graph.addDistance("Wuhan", "Portland", 10.0);
        graph.addDistance("Wuhan", "New York", 20.0);
        graph.addDistance("Atlanta", "Miami", 40.0);
        graph.addDistance("Portland", "New York", 20.0);
        LocationGraph graph1 = new LocationGraph();
        graph1.addDistance("Wuhan", "Portland", 10.0);
        graph1.addDistance("Wuhan", "New York", 20.0);
        graph1.addDistance("Atlanta", "Miami", 40.0);
        //return true if detecting cycle
        assertTrue(graph.detectCycle());
        //return false if there is no cycle
        assertFalse(graph1.detectCycle());

    }

    @Test
    void testToString() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("State College");
        graph.addDistance("Wuhan", "Portland", 10.0);
        graph.addDistance("Wuhan", "New York", 20.0);
        graph.addDistance("Atlanta", "Miami", 40.0);
        graph.addDistance("Miami", "Atlanta", 40.0);
        assertEquals("\tState College\tWuhan\tPortland\tNew York\tAtlanta\tMiami\t\n" +
                "State College\t -1.00\t -1.00\t -1.00\t -1.00\t -1.00\t -1.00\t\n" +
                "Wuhan\t -1.00\t -1.00\t 10.00\t 20.00\t -1.00\t -1.00\t\n" +
                "Portland\t -1.00\t 10.00\t -1.00\t 30.00\t -1.00\t -1.00\t\n" +
                "New York\t -1.00\t 20.00\t 30.00\t -1.00\t -1.00\t -1.00\t\n" +
                "Atlanta\t -1.00\t -1.00\t -1.00\t -1.00\t -1.00\t 40.00\t\n" +
                "Miami\t -1.00\t -1.00\t -1.00\t -1.00\t 40.00\t -1.00\t\n", graph.toString());
    }
}