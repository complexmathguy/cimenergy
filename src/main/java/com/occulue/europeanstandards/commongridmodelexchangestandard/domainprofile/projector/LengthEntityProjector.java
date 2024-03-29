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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.projector;

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
 * Projector for Length as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by LengthAggregate
 *
 * @author your_name_here
 */
@Component("length-entity-projector")
public class LengthEntityProjector {

  // core constructor
  public LengthEntityProjector(LengthRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a Length
   *
   * @param	entity Length
   */
  public Length create(Length entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a Length
   *
   * @param	entity Length
   */
  public Length update(Length entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a Length
   *
   * @param	id		UUID
   */
  public Length delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    Length entity = repository.findById(id).get();

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
   * @param	assignment 	FloatProxy
   * @return	Length
   */
  public Length assignValue(UUID parentId, FloatProxy assignment) {
    LOGGER.info("assigning Value as " + assignment.toString());

    Length parentEntity = repository.findById(parentId).get();
    assignment = FloatProxyProjector.find(assignment.getFloatProxyId());

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
   * @return	Length
   */
  public Length unAssignValue(UUID parentId) {
    Length parentEntity = repository.findById(parentId).get();

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
   * Method to retrieve the Length via an FindLengthQuery
   *
   * @return query FindLengthQuery
   */
  @SuppressWarnings("unused")
  public Length find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a Length - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all Lengths
   *
   * @param query FindAllLengthQuery
   * @return List<Length>
   */
  @SuppressWarnings("unused")
  public List<Length> findAll(FindAllLengthQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all Length - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final LengthRepository repository;

  @Autowired
  @Qualifier(value = "floatProxy-entity-projector")
  FloatProxyEntityProjector FloatProxyProjector;

  @Autowired
  @Qualifier(value = "dCLineSegment-entity-projector")
  DCLineSegmentEntityProjector DCLineSegmentProjector;

  @Autowired
  @Qualifier(value = "mutualCoupling-entity-projector")
  MutualCouplingEntityProjector MutualCouplingProjector;

  @Autowired
  @Qualifier(value = "govHydroFrancis-entity-projector")
  GovHydroFrancisEntityProjector GovHydroFrancisProjector;

  private static final Logger LOGGER = Logger.getLogger(LengthEntityProjector.class.getName());
}
