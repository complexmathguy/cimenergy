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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity RegulatingControl.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/RegulatingControl")
public class RegulatingControlQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a RegulatingControl using a UUID
   *
   * @param UUID regulatingControlId
   * @return RegulatingControl
   */
  @GetMapping("/load")
  public RegulatingControl load(@RequestParam(required = true) UUID regulatingControlId) {
    RegulatingControl entity = null;

    try {
      entity =
          RegulatingControlBusinessDelegate.getRegulatingControlInstance()
              .getRegulatingControl(new RegulatingControlFetchOneSummary(regulatingControlId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load RegulatingControl using Id " + regulatingControlId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all RegulatingControl business objects
   *
   * @return Set<RegulatingControl>
   */
  @GetMapping("/")
  public List<RegulatingControl> loadAll() {
    List<RegulatingControl> regulatingControlList = null;

    try {
      // load the RegulatingControl
      regulatingControlList =
          RegulatingControlBusinessDelegate.getRegulatingControlInstance()
              .getAllRegulatingControl();

      if (regulatingControlList != null)
        LOGGER.log(Level.INFO, "successfully loaded all RegulatingControls");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all RegulatingControls ", exc);
      return null;
    }

    return regulatingControlList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected RegulatingControl regulatingControl = null;
  private static final Logger LOGGER =
      Logger.getLogger(RegulatingControlQueryRestController.class.getName());
}
