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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.core.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.repository.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for ReportingGroup as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by ReportingGroupAggregate
 *
 * @author your_name_here
 */
@Component("reportingGroup-entity-projector")
public class ReportingGroupEntityProjector {

  // core constructor
  public ReportingGroupEntityProjector(ReportingGroupRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a ReportingGroup
   *
   * @param	entity ReportingGroup
   */
  public ReportingGroup create(ReportingGroup entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a ReportingGroup
   *
   * @param	entity ReportingGroup
   */
  public ReportingGroup update(ReportingGroup entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a ReportingGroup
   *
   * @param	id		UUID
   */
  public ReportingGroup delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    ReportingGroup entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the ReportingGroup via an FindReportingGroupQuery
   *
   * @return query FindReportingGroupQuery
   */
  @SuppressWarnings("unused")
  public ReportingGroup find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a ReportingGroup - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all ReportingGroups
   *
   * @param query FindAllReportingGroupQuery
   * @return List<ReportingGroup>
   */
  @SuppressWarnings("unused")
  public List<ReportingGroup> findAll(FindAllReportingGroupQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all ReportingGroup - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final ReportingGroupRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(ReportingGroupEntityProjector.class.getName());
}
