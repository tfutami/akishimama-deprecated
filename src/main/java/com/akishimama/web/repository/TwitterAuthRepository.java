package com.akishimama.web.repository;

import com.akishimama.web.domain.TwitterAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterAuthRepository extends JpaRepository<TwitterAuth, String> {
}
