package br.com.RollTickets.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import br.com.RollTickets.api.entity.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findByEmail(String email);


}
