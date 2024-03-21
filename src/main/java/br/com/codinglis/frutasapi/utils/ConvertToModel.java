package br.com.codinglis.frutasapi.utils;

import br.com.codinglis.frutasapi.dtos.FrutaRequest;
import br.com.codinglis.frutasapi.dtos.FrutaResponse;
import br.com.codinglis.frutasapi.models.FrutaModel;
import org.springframework.stereotype.Component;

@Component
public class ConvertToModel {

    public FrutaModel convert(FrutaRequest req) {
        return new FrutaModel(req.nome(), req.preco());
    }
}
