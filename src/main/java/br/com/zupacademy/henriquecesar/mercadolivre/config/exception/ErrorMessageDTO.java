package br.com.zupacademy.henriquecesar.mercadolivre.config.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessageDTO {

    private List<String> errosGerais = new ArrayList<String>();
    private List<ErrorFieldMessageDTO> errosDeCampo = new ArrayList<ErrorFieldMessageDTO>();

    public void addError(String error) {
        this.errosGerais.add(error);
    }

    public void addFieldError(String campo, String mensagem) {
        this.errosDeCampo.add(new ErrorFieldMessageDTO(campo, mensagem));
    }

    public List<String> getErrosGerais() {
        return errosGerais;
    }

    public List<ErrorFieldMessageDTO> getErrosDeCampo() {
        return errosDeCampo;
    }
}
