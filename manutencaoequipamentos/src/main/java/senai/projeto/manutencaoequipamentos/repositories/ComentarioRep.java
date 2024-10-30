package senai.projeto.manutencaoequipamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.manutencaoequipamentos.entities.Comentario;

@Repository
public interface ComentarioRep extends JpaRepository<Comentario, Long> {
}
