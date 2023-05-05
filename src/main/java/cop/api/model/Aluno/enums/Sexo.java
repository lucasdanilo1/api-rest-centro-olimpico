package cop.api.model.Aluno.enums;

public enum Sexo {

    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
