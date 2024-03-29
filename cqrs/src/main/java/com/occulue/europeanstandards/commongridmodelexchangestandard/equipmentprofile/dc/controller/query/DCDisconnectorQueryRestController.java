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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity DCDisconnector.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DCDisconnector")
public class DCDisconnectorQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a DCDisconnector using a UUID
   *
   * @param UUID dCDisconnectorId
   * @return DCDisconnector
   */
  @GetMapping("/load")
  public DCDisconnector load(@RequestParam(required = true) UUID dCDisconnectorId) {
    DCDisconnector entity = null;

    try {
      entity =
          DCDisconnectorBusinessDelegate.getDCDisconnectorInstance()
              .getDCDisconnector(new DCDisconnectorFetchOneSummary(dCDisconnectorId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load DCDisconnector using Id " + dCDisconnectorId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all DCDisconnector business objects
   *
   * @return Set<DCDisconnector>
   */
  @GetMapping("/")
  public List<DCDisconnector> loadAll() {
    List<DCDisconnector> dCDisconnectorList = null;

    try {
      // load the DCDisconnector
      dCDisconnectorList =
          DCDisconnectorBusinessDelegate.getDCDisconnectorInstance().getAllDCDisconnector();

      if (dCDisconnectorList != null)
        LOGGER.log(Level.INFO, "successfully loaded all DCDisconnectors");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all DCDisconnectors ", exc);
      return null;
    }

    return dCDisconnectorList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected DCDisconnector dCDisconnector = null;
  private static final Logger LOGGER =
      Logger.getLogger(DCDisconnectorQueryRestController.class.getName());
}
