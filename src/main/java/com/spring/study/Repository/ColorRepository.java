package com.spring.study.Repository;

import java.util.List;

import com.spring.study.Entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<Colors,Integer>,JpaRepository<Colors, Integer> {
    List<Colors> findByColorTypeIgnoreCase(String colorType);
}
