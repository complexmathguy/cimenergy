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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.repository.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Projector for VoltageLimit as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by VoltageLimitAggregate
 *
 * @author your_name_here
 */
@Component("voltageLimit-entity-projector")
public class VoltageLimitEntityProjector {

  // core constructor
  public VoltageLimitEntityProjector(VoltageLimitRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a VoltageLimit
   *
   * @param	entity VoltageLimit
   */
  public VoltageLimit create(VoltageLimit entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a VoltageLimit
   *
   * @param	entity VoltageLimit
   */
  public VoltageLimit update(VoltageLimit entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a VoltageLimit
   *
   * @param	id		UUID
   */
  public VoltageLimit delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    VoltageLimit entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Assign a Value
   *
   * @param	parentId	UUID
   * @param	assignment 	Voltage
   * @return	VoltageLimit
   */
  public VoltageLimit assignValue(UUID parentId, Voltage assignment) {
    LOGGER.info("assigning Value as " + assignment.toString());

    VoltageLimit parentEntity = repository.findById(parentId).get();
    assignment = VoltageProjector.find(assignment.getVoltageId());

    // ------------------------------------------
    // assign the Value to the parent entity
    // ------------------------------------------
    parentEntity.setValue(assignment);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Unassign the Value
   *
   * @param	parentId		UUID
   * @return	VoltageLimit
   */
  public VoltageLimit unAssignValue(UUID parentId) {
    VoltageLimit parentEntity = repository.findById(parentId).get();

    LOGGER.info("unAssigning Value on " + parentEntity.toString());

    // ------------------------------------------
    // null out the Value on the parent entithy
    // ------------------------------------------
    parentEntity.setValue(null);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the VoltageLimit via an FindVoltageLimitQuery
   *
   * @return query FindVoltageLimitQuery
   */
  @SuppressWarnings("unused")
  public VoltageLimit find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a VoltageLimit - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all VoltageLimits
   *
   * @param query FindAllVoltageLimitQuery
   * @return List<VoltageLimit>
   */
  @SuppressWarnings("unused")
  public List<VoltageLimit> findAll(FindAllVoltageLimitQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all VoltageLimit - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final VoltageLimitRepository repository;

  @Autowired
  @Qualifier(value = "voltage-entity-projector")
  VoltageEntityProjector VoltageProjector;

  private static final Logger LOGGER =
      Logger.getLogger(VoltageLimitEntityProjector.class.getName());
}
