package object;

public class CandidatoObject {
    private double salarioBase = 2000.00;
    private String nomeCandidato = "";
    private String emailCandidato = "";
    private double salarioCandidato = 0.0;

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getEmailCandidato() {
        return emailCandidato;
    }

    public void setEmailCandidato(String emailCandidato) {
        this.emailCandidato = emailCandidato;
    }

    public double getSalarioCandidato() {
        return salarioCandidato;
    }

    public void setSalarioCandidato(double salarioCandidato) {
        this.salarioCandidato = salarioCandidato;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}
