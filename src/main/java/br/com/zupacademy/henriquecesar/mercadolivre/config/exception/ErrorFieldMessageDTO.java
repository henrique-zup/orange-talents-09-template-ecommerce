package br.com.zupacademy.henriquecesar.mercadolivre.config.exception;

public class ErrorFieldMessageDTO {

    private String field;
    private String message;

    public ErrorFieldMessageDTO(String campo, String mensagem) {
        this.field = campo;
        this.message = mensagem;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
