public class Aluno {
    private String nome;
    private String ra; // Registro Acadêmico
    private double[] notas; // Array de notas
    private boolean ead; // Indica se o aluno está em uma disciplina EAD
    private double presenca; // Percentual de presença (para disciplinas presenciais)

    // Construtor que inicializa o aluno
    public Aluno(String nome, String ra, double[] notas, boolean ead, double presenca) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.ead = ead;
        this.presenca = presenca;
    }

    // Método para calcular a média final
    public double calcularMediaFinal() {
        int qtdNotas = notas.length;
        
        if (qtdNotas == 2) {
            // Média aritmética para até 2 notas
            return (notas[0] + notas[1]) / 2.0;
        } else if (qtdNotas == 3) {
            // Média ponderada para 3 notas (peso: N1=1, N2=2, N3=4)
            return (notas[0] + notas[1] * 2 + notas[2] * 4) / 7.0;
        } else if (qtdNotas == 4) {
            // Cálculo da média final com 4 notas (ac1, ac2, ag, af)
            return (notas[0] * 0.15) + (notas[1] * 0.30) + (notas[2] * 0.10) + (notas[3] * 0.45);
        } else {
            throw new IllegalArgumentException("Número de notas inválido.");
        }
    }

    // Método para verificar se o aluno está aprovado
    public boolean verificarAprovacao() {
        double mediaFinal = calcularMediaFinal();

        if (ead) {
            // Aprovação apenas pela nota em disciplinas EAD
            return mediaFinal >= 5.0;
        } else {
            // Aprovação pela nota e presença em disciplinas presenciais
            return mediaFinal >= 5.0 && presenca >= 75.0;
        }
    }

    // Método para imprimir as informações do aluno
    public void imprimirSituacao() {
        double mediaFinal = calcularMediaFinal();
        boolean aprovado = verificarAprovacao();
        
        System.out.println("Nome: " + nome);
        System.out.println("RA: " + ra);
        System.out.printf("Nota Final: %.2f\n", mediaFinal);
        System.out.println("Situação: " + (aprovado ? "Aprovado" : "Reprovado"));
    }

    // Exemplo de uso
    public static void main(String[] args) {
        // Exemplo com aluno presencial com 4 notas
        double[] notasAluno1 = {7.0, 8.0, 6.0, 9.0};
        Aluno aluno1 = new Aluno("João Silva", "123456", notasAluno1, false, 80.0);
        aluno1.imprimirSituacao();

        // Exemplo com aluno EAD com 3 notas
        double[] notasAluno2 = {4.0, 5.0, 4.0};
        Aluno aluno2 = new Aluno("Maria Oliveira", "654321", notasAluno2, true, 0.0);
        aluno2.imprimirSituacao();
    }
}
