package cop.api.model.Aluno.enums;

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
