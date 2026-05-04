package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.model.AgendamentoModel;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IAService {

    private Booster model;
    private final String MODEL_PATH = "src/main/resources/modelo_clinica.model";

    @PostConstruct
    public void init() {
        try {
            File f = new File(MODEL_PATH);
            if (f.exists()) {
                this.model = XGBoost.loadModel(MODEL_PATH);
                System.out.println("IA: Modelo carregado com sucesso do disco.");
            } else {
                System.out.println("IA: Arquivo não encontrado. Gerando modelo inicial automático...");
                gerarModeloInicial();
            }
        } catch (Exception e) {
            System.err.println("IA: Erro ao carregar modelo. Tentando recriar...");
            gerarModeloInicial();
        }
    }

    private float[] prepararDados(AgendamentoModel a) {
        
        float tipoNum = (a.getTipo_Procedimento() != null && 
                         a.getTipo_Procedimento().equalsIgnoreCase("Estético")) ? 1.0f : 0.0f;

        float hora = (a.getDataHora() != null) ? (float) a.getDataHora().getHour() : 12.0f;

        float valor = (a.getValor_Procedimento() != null) ? a.getValor_Procedimento().floatValue() : 0.0f;

        float totalAgendamentos = (a.getPaciente() != null) ? a.getPaciente().getTotalAgendamentos().floatValue() : 0.0f;
        float totalFaltas = (a.getPaciente() != null) ? a.getPaciente().getHistorico_NoShow().floatValue() : 0.0f;

        return new float[] { tipoNum, hora, valor, totalAgendamentos, totalFaltas };
    }

    public double preverRisco(AgendamentoModel agendamento) {
        try {
            if (this.model == null) return 0.5; 

            float[] entrada = prepararDados(agendamento);
   
            DMatrix matriz = new DMatrix(entrada, 1, 5, 0.0f);
            
            float[][] resultado = this.model.predict(matriz);
            return resultado[0][0]; 
        } catch (XGBoostError e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void treinarModeloManual(List<AgendamentoModel> historico) {
        try {
            int linhas = historico.size();
            int colunas = 5;
            float[] dadosX = new float[linhas * colunas];
            float[] labelsY = new float[linhas];

            for (int i = 0; i < linhas; i++) {
                AgendamentoModel a = historico.get(i);
                float[] features = prepararDados(a);
               
                System.arraycopy(features, 0, dadosX, i * colunas, colunas);
            
                labelsY[i] = (a.getStatus() != null && a.getStatus().equalsIgnoreCase("FALTOU")) ? 1.0f : 0.0f;
            }

            DMatrix trainMat = new DMatrix(dadosX, linhas, colunas, 0.0f);
            trainMat.setLabel(labelsY);

            this.model = treinarMotor(trainMat);
            this.model.saveModel(MODEL_PATH);
            System.out.println("IA: Modelo re-treinado com " + linhas + " exemplos reais.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarModeloInicial() {
        try {
           
            float[] dadosFake = {
                1.0f, 18.0f, 300.0f, 5.0f, 0.0f,
                0.0f, 09.0f, 50.0f, 2.0f, 2.0f,  
                1.0f, 19.0f, 600.0f, 1.0f, 1.0f,
                0.0f, 10.0f, 100.0f, 10.0f, 0.0f 
            };
            float[] labelsFake = { 0.0f, 1.0f, 1.0f, 0.0f };

            DMatrix trainMat = new DMatrix(dadosFake, 4, 5, 0.0f);
            trainMat.setLabel(labelsFake);

            this.model = treinarMotor(trainMat);
            
            File file = new File(MODEL_PATH);
            file.getParentFile().mkdirs(); 
            this.model.saveModel(MODEL_PATH);
            
            System.out.println("IA: Modelo inicial criado e salvo em: " + MODEL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Booster treinarMotor(DMatrix matriz) throws XGBoostError {
        Map<String, Object> params = new HashMap<>();
        params.put("objective", "binary:logistic");
        params.put("eval_metric", "logloss");
        params.put("eta", 0.1); 
        params.put("max_depth", 4);
        
        return XGBoost.train(matriz, params, 50, new HashMap<>(), null, null);
    }
}