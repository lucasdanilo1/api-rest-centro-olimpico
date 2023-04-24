package cop.api.Model.Aluno;

public enum Etnia {

    BRANCO("Branco"),
    PRETO("Preto"),
    PARDO("Pardo"),
    AMARELO("Amarelo");

    private final String descricao;

    Etnia(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
