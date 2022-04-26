import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    void containsName() {
    }
}