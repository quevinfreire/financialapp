package com.quevinfreire.financialapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quevinfreire.financialapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
