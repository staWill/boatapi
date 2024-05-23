package ch.boatapi.repository.boat;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BoatRepository extends CrudRepository<Boat, UUID> {
}
