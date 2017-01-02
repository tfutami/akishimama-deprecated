package com.akishimama.web.repository;

import com.akishimama.web.domain.WebSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSessionRepository extends JpaRepository<WebSession, String> {
}
