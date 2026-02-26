package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rutmiit.models.entities.Rocket;
import java.util.List;
import java.util.Optional;

@Repository
public interface RocketRepository extends JpaRepository<Rocket, Long> {
    // Если понадобится искать по имени
    Optional<Rocket> findByName(String name);

    List<Rocket> findAllByNameContainingIgnoreCase(String name);
}