package br.com.codinglis.frutasapi.services;

import br.com.codinglis.frutasapi.models.FrutaModel;
import br.com.codinglis.frutasapi.repositories.FrutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrutaService {

    @Autowired
    private FrutaRepository frutaRepository;

    public FrutaModel criarFruta (FrutaModel fruta){
        try{
           return frutaRepository.save(fruta);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public FrutaModel buscarFrutaPeloId (Long frutaId){
        var oFruta = frutaRepository.findById(frutaId);
        if (oFruta.isPresent()) return oFruta.get();
        else throw new RuntimeException("Fruta de id " + frutaId + "não foi encontrada");
    }

    public List<FrutaModel> buscarFrutas (){
        try{
            return frutaRepository.findAll();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public void deletarFruta (Long frutaId){
        try{
            frutaRepository.deleteById(frutaId);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    public FrutaModel editarFruta (Long frutaId, FrutaModel fruta){
        buscarFrutaPeloId(frutaId);
        fruta.setId(frutaId);
        return criarFruta(fruta);
    }
}
