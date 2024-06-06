package naves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import naves.entity.Nave;

public interface NaveRepository extends JpaRepository<Nave, Long> {

	List<Nave> findByNombreContaining(String nombre);
}
