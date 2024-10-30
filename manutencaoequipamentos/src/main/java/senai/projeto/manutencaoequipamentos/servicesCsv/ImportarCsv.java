package senai.projeto.manutencaoequipamentos.servicesCsv;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import senai.projeto.manutencaoequipamentos.entities.Comentario;
import senai.projeto.manutencaoequipamentos.entities.Equipamento;
import senai.projeto.manutencaoequipamentos.entities.Perfil;
import senai.projeto.manutencaoequipamentos.entities.Usuario;
import senai.projeto.manutencaoequipamentos.repositories.ComentarioRep;
import senai.projeto.manutencaoequipamentos.repositories.EquipamentoRep;
import senai.projeto.manutencaoequipamentos.repositories.PerfilRep;
import senai.projeto.manutencaoequipamentos.repositories.UsuarioRep;

import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class ImportarCsv {

    private final ComentarioRep comentarioRep;
    private final EquipamentoRep equipamentoRep;
    private final PerfilRep perfilRep;
    private final UsuarioRep usuarioRep;

    public ImportarCsv(ComentarioRep comentarioRep,
                       EquipamentoRep equipamentoRep,
                       PerfilRep perfilRep,
                       UsuarioRep usuarioRep) {
        this.comentarioRep = comentarioRep;
        this.equipamentoRep = equipamentoRep;
        this.perfilRep = perfilRep;
        this.usuarioRep = usuarioRep;
    }

    public void perfilCSV(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long id = Long.parseLong(nextLine[0]);
                String perfil = nextLine[1];

                var novoPerfil = new Perfil(
                        id,
                        perfil
                );

                perfilRep.save(novoPerfil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void usuarioCSV(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long id = Long.parseLong(nextLine[0]);
                Integer senha = Integer.parseInt(nextLine[1]);
                var perfil = perfilRep.findById(Long.parseLong(nextLine[2])).get();

                var novoUsuario = new Usuario(
                        id,
                        senha,
                        perfil
                );

                usuarioRep.save(novoUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void equipamentoCSV(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long id = Long.parseLong(nextLine[0]);
                String equipamento = nextLine[1];
                String imagem = nextLine[2];
                String descricao = nextLine[3];
                Boolean ativo = convertIntToBoolean(nextLine[4]);

                LocalDateTime localDateTime = LocalDateTime
                        .parse(nextLine[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                Instant data = localDateTime.atZone(ZoneId.systemDefault()).toInstant();


                var novoEquipamento = new Equipamento(
                        id,
                        equipamento,
                        imagem,
                        descricao,
                        ativo,
                        data
                );

                equipamentoRep.save(novoEquipamento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comentarioCSV(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long id = Long.parseLong(nextLine[0]);
                String comentario = nextLine[1];
                var equipamento = equipamentoRep.findById(Long.parseLong(nextLine[2])).get();
                var perfil = perfilRep.findById(Long.parseLong(nextLine[3])).get();

                LocalDateTime localDateTime = LocalDateTime
                        .parse(nextLine[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                Instant data = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

                var novoComentario = new Comentario(
                        id,
                        comentario,
                        equipamento,
                        perfil,
                        data
                );

                comentarioRep.save(novoComentario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean convertIntToBoolean(String ativo) {
        Integer bit = Integer.parseInt(ativo);

        if (bit != 1) {
            return false;
        }

        return true;
    }
}
