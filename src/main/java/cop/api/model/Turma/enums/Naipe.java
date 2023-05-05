package cop.api.model.Turma.enums;

public enum Naipe {

    FEMININO("Feminino"),
    MASCULINO("Masculino"),
    MISTO("Misto (Masc e Fem)");

    private String descricao;

    Naipe(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}