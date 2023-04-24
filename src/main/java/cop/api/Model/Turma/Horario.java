package cop.api.Model.Turma;

public enum Horario {

    HORA_18_19("18:00 - 19:00"),
    HORA_19_20("19:00 - 20:00"),
    HORA_20_21("20:00 - 21:00"),
    HORA_21_22("21:00 - 22:00");

    private String descricao;

    Horario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
