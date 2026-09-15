package edu.eci.dosw.tdd.skyrescue.center;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;

public class RescueCenterTest {

    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("d1", "Matrice300", 15);

        // Act
        boolean result = center.addDrone(drone);

        // Assert
        assertTrue(result);
    }

    @Test 
    void shouldNotRegisterNullDrone(){
        // Arrange
        RescueCenter center = new RescueCenter();
        
        // Act
        boolean result = center.addDrone(null);

        // Assert
        assertFalse(result);
    }

    @Test 
    void shouldNotRegisterDroneWithBlankId(){
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("", "Matrice2000", 100);

        // Act
        boolean result = center.addDrone(drone);

        // Assert
        assertFalse(result);
    }

    @Test 
    void shouldNotRegisterTwoDronesWithTheSameId(){
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone firstDrone = new Drone("d1", "Matrice200", 500);
        Drone secondDrone = new Drone("d1", "Matrice123", 109);

        // Act
        boolean firstResult = center.addDrone(firstDrone);
        boolean secondResult = center.addDrone(secondDrone);

        // Assert
        assertTrue(firstResult);
        assertFalse(secondResult);
    }

}
