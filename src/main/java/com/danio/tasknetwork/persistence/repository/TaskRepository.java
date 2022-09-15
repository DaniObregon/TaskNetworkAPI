package com.danio.tasknetwork.persistence.repository;

import com.danio.tasknetwork.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
