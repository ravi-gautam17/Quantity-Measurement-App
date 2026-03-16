package com.apps.quantitymeasurement.integrationTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.services.IQuantityMeasurementService;
import com.apps.quantitymeasurement.services.QuantityMeasurementServiceImpl;

public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;
    private IQuantityMeasurementRepository repository;

    @BeforeClass
    public static void setUpTestEnvironment() {
        System.setProperty("app.env", "TESTING");
    }

    @Before
    public void setUp() {

        repository = QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);

        repository.getAllMeasurements().clear(); // clean cache
    }

    @After
    public void tearDown() {
        repository.getAllMeasurements().clear();
    }

    @Test
    public void testEndToEndLengthComparison() {

        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LengthUnit");

        boolean result = controller.performComparison(q1, q2);

        Assert.assertTrue(result);
    }

    @Test
    public void testEndToEndTemperatureConversion() {

        QuantityDTO thisDto = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO thatDto = new QuantityDTO(0.0, "FAHRENHEIT", "TemperatureUnit");

        QuantityDTO resultDTO = controller.performConversion(thisDto, thatDto);

        Assert.assertNotNull(resultDTO);
        Assert.assertEquals(32.0, resultDTO.getValue(), 0.01);
    }
}