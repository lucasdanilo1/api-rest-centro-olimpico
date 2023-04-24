package cop.api.Model.Aluno;

public enum Status {

    MATRICULADO("Matriculado"),
    INSCRITO("Inscrito"),
    SELECIONADO("Selecionado"),
    INATIVADO("Inativado");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
