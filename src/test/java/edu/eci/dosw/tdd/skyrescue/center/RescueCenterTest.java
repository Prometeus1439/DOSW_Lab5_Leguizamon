package edu.eci.dosw.tdd.skyrescue.center;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

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

    private RescueCenter center;
    private Drone drone;
    private RescueOperator operator;

    @BeforeEach
    void setUp(){
        center = new RescueCenter();
        drone = new Drone("d1", "Matrice300", 50);
        operator = new RescueOperator("o1","Mike");
    }

    @Test
    void shouldCreateActiveMissionWhenOperatorAndDroneAreValid() {
        // Arrange
            center.addDrone(drone);
            center.addOperator(operator);

        // Act
            Mission createdMission = center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);

        // Assert
            assertEquals(MissionStatus.ACTIVE, createdMission.getStatus());
            assertFalse(drone.isAvailable());
    }

    @Test 
    void shouldThrowExceptionWhenDroneDoesNotExist(){
        // Arrange
            center.addOperator(operator);

        // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->{
                center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);
        });
    }

    @Test 
    void shouldThrowExceptionWhenDroneIsAssigned(){
        // Arrange
            center.addDrone(drone);
            center.addOperator(operator);
            drone.setAvailable(false);

        // Act & Assert
            assertThrows(IllegalStateException.class, () ->{
                center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);
        });
            
    }

    @Test 
    void shouldThrowExceptionWhenDistanceIsFurtherThanDroneAutonomy(){
        // Arrange
            center.addDrone(drone);
            center.addOperator(operator);


        // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->{
                center.assignMission(operator.getId(), drone.getId(), "Zona A", 60);
        });
            
    }

    
    @Test 
    void shouldThrowExceptionWhenOperatorDoesNotExist(){
        // Arrange
            center.addDrone(drone);

        // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->{
                center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);
        });
    }

    @Test
    void shouldNotCreateMissionWhenOperatorIsAssigned() {
        // Arrange
            center.addDrone(drone);
            center.addOperator(operator);
            Drone secondDrone = new Drone("d2", "Matrice3008", 80);
            center.addDrone(secondDrone);
            center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);

        // Act & Assert
            assertThrows(IllegalStateException.class, () ->{
                    center.assignMission(operator.getId(), secondDrone.getId(), "Zona A", 40);
            });
    }

    @Test
    void shouldCloseAnActiveMission() {
        // Arrange
            center.addDrone(drone);
            center.addOperator(operator);
            Mission createdMission = center.assignMission(operator.getId(), drone.getId(), "Zona A", 40);

        // Act
            Mission missionCompleted = center.completeMission(createdMission.getId());

        // Assert
            assertEquals(MissionStatus.COMPLETED, missionCompleted.getStatus());
            assertNotNull(missionCompleted.getEndDate());
            assertTrue(drone.isAvailable());
    }

}
