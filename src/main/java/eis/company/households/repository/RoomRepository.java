package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Optional<Room> findById(Integer id);

}
