package com.akishimama.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sat8bit on 2016/12/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
