package com.akishimama.web.repository;

import com.akishimama.web.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ri104 on 17/01/02.
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{
}
