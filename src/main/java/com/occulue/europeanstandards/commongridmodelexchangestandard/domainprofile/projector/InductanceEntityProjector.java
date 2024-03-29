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
 * Projector for Inductance as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by InductanceAggregate
 *
 * @author your_name_here
 */
@Component("inductance-entity-projector")
public class InductanceEntityProjector {

  // core constructor
  public InductanceEntityProjector(InductanceRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a Inductance
   *
   * @param	entity Inductance
   */
  public Inductance create(Inductance entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a Inductance
   *
   * @param	entity Inductance
   */
  public Inductance update(Inductance entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a Inductance
   *
   * @param	id		UUID
   */
  public Inductance delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    Inductance entity = repository.findById(id).get();

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
   * @return	Inductance
   */
  public Inductance assignValue(UUID parentId, FloatProxy assignment) {
    LOGGER.info("assigning Value as " + assignment.toString());

    Inductance parentEntity = repository.findById(parentId).get();
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
   * @return	Inductance
   */
  public Inductance unAssignValue(UUID parentId) {
    Inductance parentEntity = repository.findById(parentId).get();

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
   * Method to retrieve the Inductance via an FindInductanceQuery
   *
   * @return query FindInductanceQuery
   */
  @SuppressWarnings("unused")
  public Inductance find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a Inductance - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all Inductances
   *
   * @param query FindAllInductanceQuery
   * @return List<Inductance>
   */
  @SuppressWarnings("unused")
  public List<Inductance> findAll(FindAllInductanceQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all Inductance - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final InductanceRepository repository;

  @Autowired
  @Qualifier(value = "floatProxy-entity-projector")
  FloatProxyEntityProjector FloatProxyProjector;

  @Autowired
  @Qualifier(value = "dCGround-entity-projector")
  DCGroundEntityProjector DCGroundProjector;

  private static final Logger LOGGER = Logger.getLogger(InductanceEntityProjector.class.getName());
}
