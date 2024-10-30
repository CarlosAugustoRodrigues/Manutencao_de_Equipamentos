package senai.projeto.manutencaoequipamentos.runtime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import senai.projeto.manutencaoequipamentos.servicesCsv.ImportarCsv;

@Component
public class CarregarDadosCsv implements CommandLineRunner {

    private final ImportarCsv importarCsv;

    public CarregarDadosCsv(ImportarCsv importarCsv) {
        this.importarCsv = importarCsv;
    }

    @Value("${path.perfis}")
    private String perfis;

    @Value("${path.usuarios}")
    private String usuarios;

    @Value("${path.equipamentos}")
    private String equipamentos;

    @Value("${path.comentarios}")
    private String comentarios;

    @Override
    public void run(String... args) throws Exception {
        importarCsv.perfilCSV(perfis);
        importarCsv.usuarioCSV(usuarios);
        importarCsv.equipamentoCSV(equipamentos);
        importarCsv.comentarioCSV(comentarios);
    }
}
