package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
    Optional<Type> findByTypeIdAndDeletedAtIsNull(Integer typeId);
}
