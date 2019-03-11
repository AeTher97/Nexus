package org.urzednicza.nexus.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.urzednicza.nexus.models.SlaveDatabaseEntity;

import java.util.List;
import java.util.Optional;

public interface SlaveRepository extends JpaRepository<SlaveDatabaseEntity, Long> {
    Optional<SlaveDatabaseEntity> findById(Long id);
    Optional<SlaveDatabaseEntity> findByAddress(String address);
    List<SlaveDatabaseEntity> findAll();

}
