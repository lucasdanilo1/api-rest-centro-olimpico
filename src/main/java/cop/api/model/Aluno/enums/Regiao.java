package cop.api.model.Aluno.enums;

public enum Regiao {

    AGUAS_CLARAS("Águas Claras"),
    BRAZLANDIA("Brazlândia"),
    CANDANGOLANDIA("Candangolândia"),
    CEILANDIA("Ceilândia"),
    CIDADE_ESTRUTURAL("Cidade Estrutural"),
    CRUZEIRO("Cruzeiro"),
    FERCAL("Fercal"),
    GAMA("Gama"),
    GUARA("Guará"),
    ITAPOA("Itapoã"),
    JARDIM_BOTANICO("Jardim Botânico"),
    LAGO_NORTE("Lago Norte"),
    LAGO_SUL("Lago Sul"),
    NUCLEO_BANDERIANTE("Núcleo Bandeirante"),
    OCTOGONAL("Octogonal"),
    PARANOA("Paranoá"),
    PARK_WAY("Park Way"),
    PLANALTINA("Planaltina"),
    RECANTO_DAS_EMAS("Recanto das Emas"),
    RIACHO_FUNDO("Riacho Fundo"),
    RIACHO_FUNDO_II("Riacho Fundo II"),
    SAMAMBAIA("Samambaia"),
    SANTA_MARIA("Santa Maria"),
    SAO_SEBASTIAO("São Sebastião"),
    SCIA("SCIA"),
    SOBRADINHO("Sobradinho"),
    SOBRADINHO_II("Sobradinho II"),
    SUDOESTE_OCTOGONAL("Sudoeste/Octogonal"),
    TAGUATINGA("Taguatinga"),
    VARJAO("Varjão"),
    VICENTE_PIRES("Vicente Pires"),
    BRASILIA_PILOTO("Brasília (Plano Piloto)"),
    OUTRAS_AREAS("Brasília (Outras áreas)");

    private final String descricao;

    Regiao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
