package senai.projeto.manutencaoequipamentos.repositories;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.manutencaoequipamentos.entities.Equipamento;

@Repository
public interface EquipamentoRep extends JpaRepository<Equipamento, Long> {
}
