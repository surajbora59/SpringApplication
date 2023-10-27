package com.spring.study.Repository;

import java.util.List;

import com.spring.study.Entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Colors, Integer> {

    @Query(value = "SELECT c.id, c.colortype FROM colors c WHERE c.colortype = :colorType", nativeQuery = true)
    List<Object> findByColorType(String colorType);
}
