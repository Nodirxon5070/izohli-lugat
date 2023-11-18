package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.DayWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayWordRepository extends JpaRepository<DayWord,Integer> {
    Optional<DayWord> findByDayWordIdAndDeletedAtIsNull(Integer dayWordId);
}
