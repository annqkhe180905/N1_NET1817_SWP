package online.ondemandtutor.be.repository;

import online.ondemandtutor.be.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
    Location findbyId(Long id);

}
