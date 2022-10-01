package com.danio.tasknetwork.persistence.repository;

import com.danio.tasknetwork.persistence.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
