package br.com.codinglis.frutasapi.utils;

import br.com.codinglis.frutasapi.dtos.FrutaResponse;
import br.com.codinglis.frutasapi.models.FrutaModel;
import org.springframework.stereotype.Component;

@Component
public class ConvertToResponse {

    public FrutaResponse convert(FrutaModel model) {
        return new FrutaResponse(model.getId(), model.getNome(), model.getPreco());
    }
}
