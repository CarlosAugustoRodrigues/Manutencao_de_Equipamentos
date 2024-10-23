package senai.projeto.manutencaoequipamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.manutencaoequipamentos.entities.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long> {
}
