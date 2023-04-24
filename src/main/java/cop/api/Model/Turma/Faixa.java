package cop.api.Model.Turma;

public enum Faixa {

    FAIXA_7_17(7, 17, "7 a 17"),
    FAIXA_9_12(6, 12, "6 a 12"),
    FAIXA_12_18(12, 18, "12 a 18"),
    FAIXA_18MAIS(18, "18+");

    private int idadeMinima;
    private int idadeMaxima;
    private String descricao;


    Faixa(int idadeMinima, int idadeMaxima, String faixaEtaria) {
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = idadeMaxima;
        this.descricao = faixaEtaria;
    }

    Faixa(int idadeMinima, String faixaEtaria){
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = Integer.MAX_VALUE;
        this.descricao = faixaEtaria;
    }

    public boolean permiteIdade(int idade){
        return idade >= idadeMinima && idade <= idadeMaxima;
    }

    public String getDescricao() {
        return descricao;
    }
}
