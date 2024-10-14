package controller;

import model.CandidatoModel;
import object.CandidatoObject;

import java.util.List;

public class CandidatoController {
    static CandidatoModel candidatoModel = new CandidatoModel();

    public String validarCandidato(CandidatoObject candidatoObject) {
        return candidatoModel.validaSalario(candidatoObject);
    }

    public String inserirCandidato(CandidatoObject candidatoObject) {
        return candidatoModel.inserirCandidato(candidatoObject);
    }

    public String alterarCandidato(CandidatoObject candidatoObject) {
        return candidatoModel.alterarCandidato(candidatoObject);
    }

    public List<CandidatoObject> buscarCandidatos(double salarioBase) {
        return candidatoModel.buscarCandidatosComSalarioMenorOuIgual(salarioBase);
    }
}
