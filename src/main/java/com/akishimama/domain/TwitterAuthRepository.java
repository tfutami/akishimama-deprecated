package com.akishimama.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterAuthRepository extends JpaRepository<TwitterAuth, String> {
}
