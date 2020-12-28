package org.hua.hermes.repository;

import org.hua.hermes.data.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

/**
 * @author Vivian Gourgioti
 */
public interface CitizenApplicationRepository extends JpaRepository<Application,String> {

    @PostAuthorize("returnObject.orElseThrow(@resourceNotFoundExceptionSupplier).createdBy == authentication.name")
    Optional<Application> findById(String id);

    @Query("SELECT a FROM Application a WHERE a.createdBy = ?#{authentication.name}")
    Page<Application> findAll(Pageable pageable);

    @Query("SELECT ap FROM Application ap WHERE ap.createdBy = ?#{authentication.name}")
    long count();
}
