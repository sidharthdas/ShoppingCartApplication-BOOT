package com.sca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sca.model.User;
import com.sca.model.User;
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
