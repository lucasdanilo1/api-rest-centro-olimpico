package cop.api.model.Turma.enums;

public enum Dias {

    SEGUNDA("Segunda"),
    SEGUNDA_A_SEXTA("Segunda a Sexta"),
    SEGUNDA_QUARTA_SEXTA("Segunda, Quarta e Sexta"),
    TERCA_QUINTA("Terca e Quinta"),
    TERCA_A_SEXTA("Terça a Sexta"),
    QUARTA_SEXTA("Quarta e Sexta"),
    SABADO("Sábado");


    public String descricao;

    Dias(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
