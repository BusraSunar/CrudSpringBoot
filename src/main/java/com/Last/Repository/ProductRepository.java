package com.Last.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Last.Model.User;

@Repository
public interface ProductRepository extends JpaRepository<User, Long> {

}
