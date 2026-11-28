package n.m.com.br.Yurei_run.repository;

import n.m.com.br.Yurei_run.model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long id);
}
