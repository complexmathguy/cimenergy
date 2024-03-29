/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.controller.query;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller query CQRS processing for entity Measurement.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Measurement")
public class MeasurementQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a Measurement using a UUID
   *
   * @param UUID measurementId
   * @return Measurement
   */
  @GetMapping("/load")
  public Measurement load(@RequestParam(required = true) UUID measurementId) {
    Measurement entity = null;

    try {
      entity =
          MeasurementBusinessDelegate.getMeasurementInstance()
              .getMeasurement(new MeasurementFetchOneSummary(measurementId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load Measurement using Id " + measurementId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all Measurement business objects
   *
   * @return Set<Measurement>
   */
  @GetMapping("/")
  public List<Measurement> loadAll() {
    List<Measurement> measurementList = null;

    try {
      // load the Measurement
      measurementList = MeasurementBusinessDelegate.getMeasurementInstance().getAllMeasurement();

      if (measurementList != null) LOGGER.log(Level.INFO, "successfully loaded all Measurements");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all Measurements ", exc);
      return null;
    }

    return measurementList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Measurement measurement = null;
  private static final Logger LOGGER =
      Logger.getLogger(MeasurementQueryRestController.class.getName());
}
