package com.ZenPack.repository;

import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZenPackRepository extends JpaRepository<ZenPack,Integer>, JpaSpecificationExecutor<ZenPack> {

    void deleteByZenPackId(Long zenPackId);

    Optional<ZenPack> findByZenPackId(Long zenPackId);
}