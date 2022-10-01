package com.danio.tasknetwork.persistence.repository;

import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Segun el nombre que le dimos al metodo, Spring Data JPA
     * sabe que se tiene que conectar a la DB y buscar tarea por estado
     * @param taskStatus
     * @return lista de tareas por estado
     */
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    /**
     * Consulta query nativa SQL
     * Se debe anotar en el servicio como @Transactional
     * lo que significa que la operacion se hara completa o no se hara!
     */
    @Modifying
    @Query(value = "UPDATE TASK SET finished=TRUE WHERE id=:id", nativeQuery = true)
    public void maskTaskAsFinished(@Param("id") Long id);
}
