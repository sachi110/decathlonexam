package com.example.decathlon.repro;

import com.example.decathlon.model.Developer;
import com.example.decathlon.model.Developers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevListRepor extends JpaRepository<Developer,Long> {


    @Query(value="SELECT  * FROM DEVELOPER t where t.team_id = :team_id" , nativeQuery = true)
    List<Developer>  findByteam_id(@Param("team_id") Long team_id);




}