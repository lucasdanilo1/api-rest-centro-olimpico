package cop.api.Model.Turma;

public enum StatusTurma {

        TURMA_ABERTA("Turma Aberta"),
        TURMA_FECHADA("Turma Fechada");

        private String descricao;

        StatusTurma(String descricao) {
                this.descricao = descricao;
        }

        public String getDescricao() {
                return descricao;
        }
}