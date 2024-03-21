package br.com.codinglis.frutasapi.repositories;

import br.com.codinglis.frutasapi.models.FrutaModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FrutaRepository extends JpaRepository<FrutaModel, Long> {
}
