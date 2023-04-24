package cop.api.Model.Aluno;

public enum Naturalidade {

    ACREANO("Acreano"),
    ALAGOANO("Alagoano"),
    AMAZONENSE("Amazonense"),
    BAIANO("Baiano"),
    CEARENSE("Cearense"),
    DISTRITO_FEDERAL("Candango"),
    ESPIRITO_SANTENSE("Espírito Santense"),
    GOIANO("Goiano"),
    MARANHENSE("Maranhense"),
    MATO_GROSSENSE("Mato-Grossense"),
    MINEIRO("Mineiro"),
    PAULISTA("Paulista"),
    PARANAENSE("Paranaense"),
    PERNAMBUCANO("Pernambucano"),
    PIAUIENSE("Piauiense"),
    GAUCHO("Gaúcho"),
    CARIOCA("Carioca"),
    CATARINENSE("Catarinense"),
    SUDESTE_MINEIRO("Sudeste Mineiro"),
    SERGIPANO("Sergipano"),
    TOCANTINENSE("Tocantinense");

    private final String descricao;

    Naturalidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
