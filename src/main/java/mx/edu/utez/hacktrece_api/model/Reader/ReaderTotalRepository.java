package mx.edu.utez.hacktrece_api.model.Reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReaderTotalRepository extends JpaRepository<ReaderTotal, UUID> {

}
