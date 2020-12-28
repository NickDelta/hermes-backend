package org.hua.hermes.repository;

import org.hua.hermes.data.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

/**
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos
 */
public interface OrganizationApplicationRepository extends JpaRepository<Application,String>
{
    @Query("SELECT a FROM Application a WHERE a.organization = ?#{@keycloakTokenHelper.group.parent.id}")
    Page<Application> findAll(Pageable pageable);

    @PostAuthorize("returnObject.orElseThrow(@resourceNotFoundExceptionSupplier).organization == @keycloakTokenHelper.group.parent.id")
    Optional<Application> findById(String id);

    @Query("SELECT COUNT(ap.id) FROM Application ap WHERE ap.organization = ?#{@keycloakTokenHelper.group.parent.id}")
    long count();
}
