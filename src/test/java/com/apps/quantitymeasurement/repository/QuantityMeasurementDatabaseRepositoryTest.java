package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

public class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;
    private QuantityMeasurementEntity testEntity;

    @BeforeClass
    public static void setUpDatabase() {
        // Set test database
        System.setProperty("app.env", "TESTING");
    }

    @Before
    public void setUp() {
        repository = QuantityMeasurementDatabaseRepository.getInstance();
        repository.deleteAll(); // Clean database before each test
        createTestEntity();
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    // Test saving an entity
    @Test
    public void testSaveEntity() {

        repository.save(testEntity);

        int count = repository.getTotalCount();

        assertEquals(1, count);
    }

    // Test retrieving all measurements
    @Test
    public void testGetAllMeasurements() {

        repository.save(testEntity);
        repository.save(createTestEntityCopy(20));

        List<QuantityMeasurementEntity> measurements = repository.getAllMeasurements();

        assertEquals(2, measurements.size());
    }

    // Test retrieving measurements by operation
    @Test
    public void testGetMeasurementsByOperation() {

        repository.save(testEntity);

        QuantityMeasurementEntity entity2 = createTestEntityCopy(15);
        entity2.operation = "SUBTRACT";

        repository.save(entity2);

        List<QuantityMeasurementEntity> results = repository.getMeasurementsByOperation("ADD");

        assertEquals(1, results.size());
        assertEquals("ADD", results.get(0).operation);
    }

    // Test retrieving measurements by measurement type
    @Test
    public void testGetMeasurementsByType() {

        repository.save(testEntity);

        QuantityMeasurementEntity entity2 = createTestEntityCopy(30);
        entity2.thisMeasurementType = "WEIGHT";

        repository.save(entity2);

        List<QuantityMeasurementEntity> results = repository.getMeasurementByType("LENGTH");

        assertEquals(1, results.size());
    }

    // Test total count
    @Test
    public void testGetTotalCount() {

        repository.save(testEntity);
        repository.save(createTestEntityCopy(25));
        repository.save(createTestEntityCopy(35));

        int count = repository.getTotalCount();

        assertEquals(3, count);
    }

    // Test deleteAll
    @Test
    public void testDeleteAll() {

        repository.save(testEntity);
        repository.save(createTestEntityCopy(20));

        repository.deleteAll();

        int count = repository.getTotalCount();

        assertEquals(0, count);
    }

    // Helper method to create a test entity with predefined values
    private void createTestEntity() {

        testEntity = new QuantityMeasurementEntity();

        testEntity.thisValue = 10;
        testEntity.thisUnit = "FEET";
        testEntity.thisMeasurementType = "LENGTH";

        testEntity.thatValue = 12;
        testEntity.thatUnit = "INCHES";
        testEntity.thatMeasurementType = "LENGTH";

        testEntity.operation = "ADD";

        testEntity.resultValue = 11;
        testEntity.resultUnit = "FEET";
        testEntity.resultMeasurementType = "LENGTH";
    }

    // Create copy with different value
    private QuantityMeasurementEntity createTestEntityCopy(double value) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.thisValue = value;
        entity.thisUnit = "FEET";
        entity.thisMeasurementType = "LENGTH";

        entity.thatValue = 12;
        entity.thatUnit = "INCH";
        entity.thatMeasurementType = "LENGTH";

        entity.operation = "ADD";

        entity.resultValue = value + 1;
        entity.resultUnit = "FEET";
        entity.resultMeasurementType = "LENGTH";

        return entity;
    }
}