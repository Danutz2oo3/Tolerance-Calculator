package com.Danutz.tolerancecalculator.borehole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoreHoleRepository extends JpaRepository<BoreHoleEntity, Long> {
    @Query("SELECT bh FROM BoreHoleEntity bh WHERE bh.userEntity.id = :id")
    List<BoreHoleEntity> findALLBoreHoleEntityByName(Long id);
}
