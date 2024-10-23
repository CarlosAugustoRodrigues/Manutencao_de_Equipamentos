package senai.projeto.manutencaoequipamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.manutencaoequipamentos.entities.Perfil;

@Repository
public interface PerfilRep extends JpaRepository<Perfil, Long> {
}
