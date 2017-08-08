package fr.noeldupuis.demo.repositories;

import fr.noeldupuis.demo.fileManager.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<CoursEntity, Long>{

    CoursEntity findFirstById(long id);
}
