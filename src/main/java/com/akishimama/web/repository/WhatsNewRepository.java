package com.akishimama.web.repository;

import com.akishimama.web.domain.WhatsNew;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhatsNewRepository extends JpaRepository<WhatsNew, Integer> {

    List<WhatsNew> findTop10ByOrderByPostedAtDesc();
}
