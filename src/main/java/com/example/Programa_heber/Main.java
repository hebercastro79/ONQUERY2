
package com.example.Programa_heber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import models.DadosNovosDb;
import models.DadosNovosAntDb;
import models.InformacoesEmpresasDb;

import org.apache.jena.ontology.Ontology;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import repositories.DadosNovosAntRepository;
import repositories.DadosNovosRepository;
import repositories.InformacoesEmpresasRepository;

import org.apache.commons.text.similarity.CosineDistance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

//import org.springframework.web.bind.annotation.RequestMapping;

// @SpringBootApplication
// @Controller
// public class Main {

//     private final ResourceLoader resourceLoader;

//     public Main(ResourceLoader resourceLoader) {
//         this.resourceLoader = resourceLoader;
//     }

//     public static void main(String[] args) {
//         SpringApplication.run(Main.class, args);
//     }

//     @GetMapping("/")
//     public ResponseEntity<String> index() throws IOException {
//     // Carrega o arquivo HTML
//     org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:static/index2.html");

//     // Lê o conteúdo do arquivo HTML como uma string
//     byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
//     String htmlContent = new String(bytes, StandardCharsets.UTF_8);

//     // Retorna o conteúdo do arquivo HTML como resposta
//     return ResponseEntity.ok().body(htmlContent);
// }
// }

@SpringBootApplication
@RestController
public class Main {

        private final ResourceLoader resourceLoader;
        // private static final Ontology ontology = new Ontology();

        public Main(ResourceLoader resourceLoader) {
                this.resourceLoader = resourceLoader;
        }

        public static void main(String[] args) {
                SpringApplication.run(Main.class, args);
        }

        @GetMapping("/")
        public ResponseEntity<String> index() throws IOException {

                // Carrega o arquivo HTML
                org.springframework.core.io.Resource resource = resourceLoader
                                .getResource("classpath:static/index2.html");

                // Lê o conteúdo do arquivo HTML como uma string
                byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
                String htmlContent = new String(bytes, StandardCharsets.UTF_8);

                // Retorna o conteúdo do arquivo HTML como resposta
                return ResponseEntity.ok().body(htmlContent);
        }

        @PostMapping("/processar_pergunta")
        public static RespostaReply processarPergunta(@RequestBody PerguntaRequest request) {

                // System.out.println("");
                // System.out.println("");
                // System.out.println("Mensagem de teste no console");
                // System.out.println(request.getPergunta());
                // System.out.println("");
                // System.out.println("");
                // System.out.println("");

                // MÉTODO QUE CHAMA AS QUERYS BASEADO EM UMA PERGUNTA
                String resposta = loadPerguntasInteresse(request.getPergunta());

                RespostaReply respostaReply = new RespostaReply();
                respostaReply.setResposta(resposta);

                // RETORNA A RESPOSTA
                return respostaReply;

        }

        private static String loadPerguntasInteresse(String pergunta) {

                StockMarketOntology ontology = new StockMarketOntology();
                Map<String, Double> precoFechamentoAnteriorMap = new HashMap<>();

                // Planilha DadosNovosAnt
                List<DadosNovosAntDb> dadosNovosAntList = new DadosNovosAntRepository().getAll();
                for (DadosNovosAntDb dadosNovosAntDb : dadosNovosAntList) {

                        String negocio = dadosNovosAntDb.getNegocio();

                        // Triplas
                        Resource empresa = ontology.createEmpresa(dadosNovosAntDb.getEmpresas());
                        Property temValorMobiliarioNegociadoProperty = ontology
                                        .getProperty("temValorMobiliarioNegociado");
                        Resource Valor = ontology.createValor(dadosNovosAntDb.getValorMobiliarioant());

                        Resource Valor1 = ontology.createValor(dadosNovosAntDb.getValorMobiliarioant());
                        Property representadoPorProperty = ontology.representadoPorProperty("representadoPor");
                        Resource Codigo = ontology.createCodNegociacao(dadosNovosAntDb.getTicker());

                        Resource Valor2 = ontology.createValor(dadosNovosAntDb.getValorMobiliarioant());
                        Property negociadoProperty = ontology.negociadoProperty("negociado");
                        Resource negociadoEm = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());

                        Resource Valor3 = ontology.createValor(dadosNovosAntDb.getValorMobiliarioant());
                        Property negociadoPregoesProperty = ontology.negociadoPregaoProperty("negociadoPregoes");
                        Resource negociadoEmDiferente = ontology
                                        .createNegociadoEmDiferentes(dadosNovosAntDb.getNegociadoEmant());

                        Resource negociadoEmDiferente2 = ontology
                                        .createNegociadoEmDiferentes(dadosNovosAntDb.getNegociadoEmant());
                        Property absolutaProperty = ontology.absolutaProperty("variacaoAbsoluta");
                        Resource variacaoAbs = ontology.createAbsoluta(dadosNovosAntDb.getPrecofechaant());

                        Resource negociadoEmDiferente3 = ontology
                                        .createNegociadoEmDiferentes(dadosNovosAntDb.getNegociadoEmant());
                        Property percentualProperty = ontology.percentualProperty("variacaoPercentual");
                        Resource variacaoPerc = ontology.createPercentual(dadosNovosAntDb.getPrecofechaant());

                        Resource negociadoEm2 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property duranteProperty = ontology.duranteProperty("negociadoDurante");
                        Resource pregao = ontology.createPregao(dadosNovosAntDb.getPregaoant());

                        Resource pregao2 = ontology.createPregao(dadosNovosAntDb.getPregaoant());
                        Property dataProperty = ontology.dataProperty("ocorreEmData");
                        Resource data = ontology.createData(dadosNovosAntDb.getData());

                        Resource negociadoEm3 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property precoFechaProperty = ontology.precofechaProperty("precoFechamento");
                        Resource precoFecha = ontology.createPrecoFecha(dadosNovosAntDb.getPrecofechaant());

                        Resource negociadoEm4 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property precoAbreProperty = ontology.precoAberturaProperty("precoAbertura");
                        Resource precoAbre = ontology.createPrecoAbre(dadosNovosAntDb.getPrecoabre());

                        Resource negociadoEm5 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property precoMaximoProperty = ontology.precoMaximoProperty("precoMaximo");
                        Resource precoMax = ontology.createPrecoMax(dadosNovosAntDb.getPrecoMax());

                        Resource negociadoEm6 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property precoMinimoProperty = ontology.precoMinimoProperty("precoMinimo");
                        Resource precoMin = ontology.createPrecoMin(dadosNovosAntDb.getPrecoMin());

                        Resource negociadoEm7 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property totalNegociadoProperty = ontology.totalNegociadoProperty("totalNegociado");
                        Resource totalNegociado = ontology.createTotal(dadosNovosAntDb.getTotal());

                        Resource negociadoEm8 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property volumeNegociadoProperty = ontology.volumeNegociadoProperty("volumeNegociado");
                        Resource volumeNegociado = ontology.createVolume(dadosNovosAntDb.getVolume());

                        Resource Codigo2 = ontology.createCodNegociacao(dadosNovosAntDb.getCodNegociacaoant());
                        Property temIdentificacaoProperty = ontology.temIdentificacaoProperty("temIdentificacao");
                        Resource valorMobiliario = ontology
                                        .createValor_Mobiliario(dadosNovosAntDb.getValor_Mobiliario());

                        Resource valorMobiliario2 = ontology.createCodNegociacao(dadosNovosAntDb.getValor_Mobiliario());
                        Property isAProperty = ontology.isAProperty("isA");
                        Resource acao = ontology.createAcao(dadosNovosAntDb.getAcaoant());

                        Resource acao2 = ontology.createAcao(dadosNovosAntDb.getAcaoant());
                        Property isA2Property = ontology.isAProperty("isA");
                        Resource preferencial = ontology.createPreferencial(dadosNovosAntDb.getTipo());

                        Resource acao3 = ontology.createAcao(dadosNovosAntDb.getAcaoant());
                        Property isA3Property = ontology.isAProperty("isA");
                        Resource ordinario = ontology.createOrdinaria(dadosNovosAntDb.getTipo());

                        Resource negociadoEm9 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property variacaoIntraAbsProperty = ontology
                                        .variacaoIntraAbsProperty("variacaoIntradiariaAbsoluta");
                        Resource variacaoIntraAbs = ontology
                                        .createvariacaoAbsoluta(dadosNovosAntDb.getVariacaoIntradiariaAbsoluta());

                        Resource negociadoEm10 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property variacaoIntraPerProperty = ontology
                                        .variacaoIntraPercProperty("variacaoIntradiariaPercentual");
                        Resource variacaoIntraPerc = ontology
                                        .createvariacaoPercentual(dadosNovosAntDb.getVariacaoIntradiariaPercentual());

                        Resource negociadoEm11 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property intervaloIntraAbsProperty = ontology
                                        .intervaloIntraAbsProperty("intervaloIntradiarioAbsoluto");
                        Resource intervaloIntraAbs = ontology
                                        .createintervaloAbsoluto(dadosNovosAntDb.getIntervaloIntradiarioAbsoluto());

                        Resource negociadoEm12 = ontology.createNegociadoEm(dadosNovosAntDb.getNegociadoEmant());
                        Property intervaloIntraPercProperty = ontology
                                        .intervaloIntraPercProperty("intervaloIntradiarioPercentual");
                        Resource intervaloIntraPerc = ontology
                                        .createintervaloPercentual(dadosNovosAntDb.getIntervaloIntradiarioPercentual());

                        Resource ticketEDataPregao = ontology.createTicketEDataPregao(dadosNovosAntDb.getTicker(),
                                        dadosNovosAntDb.getData());
                        Property precofechaProperty = ontology.getProperty("precoFechamento");
                        Literal precoFechamentoLiteral = ontology.createLiteral(dadosNovosAntDb.getPrecofechaant());
                        precoFechamentoAnteriorMap.put(dadosNovosAntDb.getTicker(),
                                        Double.valueOf(dadosNovosAntDb.getPrecofechaant()));

                        ontology.createStatementAndAddToModel(ticketEDataPregao, precofechaProperty,
                                        precoFechamentoLiteral);

                        ontology.createStatementAndAddToModel(empresa, temValorMobiliarioNegociadoProperty, Valor);
                        ontology.createStatementAndAddToModel(Valor1, representadoPorProperty, Codigo);
                        ontology.createStatementAndAddToModel(Valor2, negociadoProperty, negociadoEm);
                        ontology.createStatementAndAddToModel(Valor3, negociadoPregoesProperty, negociadoEmDiferente);
                        ontology.createStatementAndAddToModel(negociadoEmDiferente2, absolutaProperty, variacaoAbs);
                        ontology.createStatementAndAddToModel(negociadoEmDiferente3, percentualProperty, variacaoPerc);
                        ontology.createStatementAndAddToModel(negociadoEm2, duranteProperty, pregao);
                        ontology.createStatementAndAddToModel(pregao2, dataProperty, data);
                        ontology.createStatementAndAddToModel(negociadoEm3, precoFechaProperty, precoFecha);
                        ontology.createStatementAndAddToModel(negociadoEm4, precoAbreProperty, precoAbre);
                        ontology.createStatementAndAddToModel(negociadoEm5, precoMaximoProperty, precoMax);
                        ontology.createStatementAndAddToModel(negociadoEm6, precoMinimoProperty, precoMin);
                        ontology.createStatementAndAddToModel(negociadoEm7, totalNegociadoProperty, totalNegociado);
                        ontology.createStatementAndAddToModel(negociadoEm8, volumeNegociadoProperty, volumeNegociado);
                        ontology.createStatementAndAddToModel(negociadoEm9, variacaoIntraAbsProperty, variacaoIntraAbs);
                        ontology.createStatementAndAddToModel(negociadoEm10, variacaoIntraPerProperty,
                                        variacaoIntraPerc);
                        ontology.createStatementAndAddToModel(negociadoEm11, intervaloIntraAbsProperty,
                                        intervaloIntraAbs);
                        ontology.createStatementAndAddToModel(negociadoEm12, intervaloIntraPercProperty,
                                        intervaloIntraPerc);
                        ontology.createStatementAndAddToModel(Codigo2, temIdentificacaoProperty, valorMobiliario);
                        ontology.createStatementAndAddToModel(valorMobiliario2, isAProperty, acao);
                        ontology.createStatementAndAddToModel(acao2, isA2Property, preferencial);
                        ontology.createStatementAndAddToModel(acao3, isA3Property, ordinario);

                }

                // Planilha DadosNovos
                List<DadosNovosDb> dadosNovosList = new DadosNovosRepository().getAll();
                for (DadosNovosDb dadosNovosDb : dadosNovosList) {

                        String negocio = dadosNovosDb.getNegocio();

                        // Triplas
                        Resource empresa = ontology.createEmpresa(dadosNovosDb.getEmpresas());
                        Property temValorMobiliarioNegociadoProperty = ontology
                                        .getProperty("temValorMobiliarioNegociado");
                        Resource Valor = ontology.createValor(dadosNovosDb.getValorMobiliario());

                        Resource Valor1 = ontology.createValor(dadosNovosDb.getValorMobiliario());
                        Property representadoPorProperty = ontology.representadoPorProperty("representadoPor");
                        Resource Codigo = ontology.createCodNegociacao(dadosNovosDb.getTicker());

                        Resource Valor2 = ontology.createValor(dadosNovosDb.getValorMobiliario());
                        Property negociadoProperty = ontology.negociadoProperty("negociado");
                        Resource negociadoEm = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());

                        Resource negociadoEm2 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property duranteProperty = ontology.duranteProperty("negociadoDurante");
                        Resource pregao = ontology.createPregao(dadosNovosDb.getPregao());

                        Resource pregao2 = ontology.createPregao(dadosNovosDb.getPregao());
                        Property dataProperty = ontology.dataProperty("ocorreEmData");
                        Resource data = ontology.createData(dadosNovosDb.getData());

                        Resource negociadoEm3 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property precoFechaProperty = ontology.precofechaProperty("precoFechamento");
                        Resource precoFecha = ontology.createPrecoFecha(dadosNovosDb.getPrecofecha());

                        Resource negociadoEm4 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property precoAbreProperty = ontology.precoAberturaProperty("precoAbertura");
                        Resource precoAbre = ontology.createPrecoAbre(dadosNovosDb.getPrecoabre());

                        Resource negociadoEm5 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property precoMaximoProperty = ontology.precoMaximoProperty("precoMaximo");
                        Resource precoMax = ontology.createPrecoMax(dadosNovosDb.getPrecoMax());

                        Resource negociadoEm6 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property precoMinimoProperty = ontology.precoMinimoProperty("precoMinimo");
                        Resource precoMin = ontology.createPrecoMin(dadosNovosDb.getPrecoMin());

                        Resource negociadoEm7 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property totalNegociadoProperty = ontology.totalNegociadoProperty("totalNegociado");
                        Resource totalNegociado = ontology.createTotal(dadosNovosDb.getTotal());

                        Resource negociadoEm8 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property volumeNegociadoProperty = ontology.volumeNegociadoProperty("volumeNegociado");
                        Resource volumeNegociado = ontology.createVolume(dadosNovosDb.getVolume());

                        Resource Codigo2 = ontology.createCodNegociacao(dadosNovosDb.getTicker());
                        Property temIdentificacaoProperty = ontology.temIdentificacaoProperty("temIdentificacao");
                        Resource valorMobiliario = ontology.createValor(dadosNovosDb.getValor_Mobiliario());

                        Resource valorMobiliario2 = ontology.createCodNegociacao(dadosNovosDb.getValor_Mobiliario());
                        Property isAProperty = ontology.isAProperty("isA");
                        Resource acao = ontology.createAcao(dadosNovosDb.getAcao());

                        Resource acao2 = ontology.createAcao(dadosNovosDb.getAcao());
                        Property isA2Property = ontology.isAProperty("isA");
                        Resource preferencial = ontology.createPreferencial(dadosNovosDb.getTipo());

                        Resource acao3 = ontology.createAcao(dadosNovosDb.getAcao());
                        Property isA3Property = ontology.isAProperty("isA");
                        Resource ordinario = ontology.createOrdinaria(dadosNovosDb.getTipo());

                        Resource negociadoEm9 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property variacaoIntraAbsProperty = ontology
                                        .variacaoIntraAbsProperty("variacaoIntradiariaAbsoluta");
                        Resource variacaoIntraAbs = ontology
                                        .createvariacaoAbsoluta(dadosNovosDb.getVariacaoIntradiariaAbsoluta());

                        Resource negociadoEm10 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property variacaoIntraPerProperty = ontology
                                        .variacaoIntraPercProperty("variacaoIntradiariaPercentual");
                        Resource variacaoIntraPerc = ontology
                                        .createvariacaoPercentual(dadosNovosDb.getVariacaoIntradiariaPercentual());

                        Resource negociadoEm11 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property intervaloIntraAbsProperty = ontology
                                        .intervaloIntraAbsProperty("intervaloIntradiarioAbsoluto");
                        Resource intervaloIntraAbs = ontology
                                        .createintervaloAbsoluto(dadosNovosDb.getIntervaloIntradiarioAbsoluto());

                        Resource negociadoEm12 = ontology.createNegociadoEm(dadosNovosDb.getNegociadoEm());
                        Property intervaloIntraPercProperty = ontology
                                        .intervaloIntraPercProperty("intervaloIntradiarioPercentual");
                        Resource intervaloIntraPerc = ontology
                                        .createintervaloPercentual(dadosNovosDb.getIntervaloIntradiarioPercentual());

                        Resource Valor3 = ontology.createValor(dadosNovosDb.getValorMobiliario());
                        Property negociadoPregoesProperty = ontology.negociadoPregaoProperty("negociadoPregoes");
                        Resource NegociadoEmDiferente = ontology.createTicketEDataPregao(dadosNovosDb.getTicker(),
                                        dadosNovosDb.getData());

                        // Begin diferenca de precos de fechamento
                        Resource ticketEDataPregao2 = ontology.createTicketEDataPregao(dadosNovosDb.getTicker(),
                                        dadosNovosDb.getData());
                        Literal precoFechamentoLiteral = ontology.createLiteral(dadosNovosDb.getPrecofecha());
                        ontology.createStatementAndAddToModel(NegociadoEmDiferente, precoFechaProperty,
                                        precoFechamentoLiteral);

                        Double precoFechamentoAnterior = precoFechamentoAnteriorMap.get(dadosNovosDb.getTicker());
                        if (precoFechamentoAnterior != null) {

                                Property variacaoabsProperty = ontology.getProperty("VariacaoAbsoluta");
                                Double diferencaAbsoluta = Double.parseDouble(dadosNovosDb.getPrecofecha())
                                                - precoFechamentoAnterior;
                                Literal variacaoAbsolutaLiteral = ontology.createLiteral(diferencaAbsoluta.toString());

                                Property variacaopercProperty = ontology.getProperty("VariacaoPercentual");
                                Double diferencaPercentual = ((Double.parseDouble(dadosNovosDb.getPrecofecha())
                                                / precoFechamentoAnterior) - 1) * 100;
                                Literal variacaoPercentualLiteral = ontology
                                                .createLiteral(diferencaPercentual.toString());

                                ontology.createStatementAndAddToModel(ticketEDataPregao2, variacaoabsProperty,
                                                variacaoAbsolutaLiteral);
                                ontology.createStatementAndAddToModel(ticketEDataPregao2, variacaopercProperty,
                                                variacaoPercentualLiteral);

                        }

                        ontology.createStatementAndAddToModel(empresa, temValorMobiliarioNegociadoProperty, Valor);
                        ontology.createStatementAndAddToModel(Valor1, representadoPorProperty, Codigo);
                        ontology.createStatementAndAddToModel(Valor2, negociadoProperty, negociadoEm);
                        ontology.createStatementAndAddToModel(Valor3, negociadoPregoesProperty, NegociadoEmDiferente);
                        ontology.createStatementAndAddToModel(negociadoEm2, duranteProperty, pregao);
                        ontology.createStatementAndAddToModel(pregao2, dataProperty, data);
                        ontology.createStatementAndAddToModel(negociadoEm3, precoFechaProperty, precoFecha);
                        ontology.createStatementAndAddToModel(negociadoEm4, precoAbreProperty, precoAbre);
                        ontology.createStatementAndAddToModel(negociadoEm5, precoMaximoProperty, precoMax);
                        ontology.createStatementAndAddToModel(negociadoEm6, precoMinimoProperty, precoMin);
                        ontology.createStatementAndAddToModel(negociadoEm7, totalNegociadoProperty, totalNegociado);
                        ontology.createStatementAndAddToModel(negociadoEm8, volumeNegociadoProperty, volumeNegociado);
                        ontology.createStatementAndAddToModel(Codigo2, temIdentificacaoProperty, valorMobiliario);
                        ontology.createStatementAndAddToModel(negociadoEm9, variacaoIntraAbsProperty, variacaoIntraAbs);
                        ontology.createStatementAndAddToModel(negociadoEm10, variacaoIntraPerProperty,
                                        variacaoIntraPerc);
                        ontology.createStatementAndAddToModel(negociadoEm11, intervaloIntraAbsProperty,
                                        intervaloIntraAbs);
                        ontology.createStatementAndAddToModel(negociadoEm12, intervaloIntraPercProperty,
                                        intervaloIntraPerc);
                        ontology.createStatementAndAddToModel(valorMobiliario2, isAProperty, acao);
                        ontology.createStatementAndAddToModel(acao2, isA2Property, preferencial);
                        ontology.createStatementAndAddToModel(acao3, isA3Property, ordinario);

                }

                // Planilha InformacoesEmpresas
                List<InformacoesEmpresasDb> informacoesEmpresasList = new InformacoesEmpresasRepository().getAll();
                for (InformacoesEmpresasDb informacoesEmpresasDb : informacoesEmpresasList) {

                        Resource empresa = ontology.createEmpresa(informacoesEmpresasDb.getEmpresas());
                        Property atuaEmProperty = ontology.atuaEmProperty("atuaEm");
                        Resource setor = ontology.createSetor(informacoesEmpresasDb.getSetores1());

                        // Bens Industriais

                        Resource bens2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA1Property = ontology.isAProperty("isA");
                        Resource transporte = ontology.createTransporte(informacoesEmpresasDb.getSetores2());

                        Resource transporte2 = ontology.createBensIndustriais(informacoesEmpresasDb.getSetores2());
                        Property isA2Property = ontology.isAProperty("isA");
                        Resource aereo = ontology.createAereo(informacoesEmpresasDb.getSetores3());

                        Resource transporte3 = ontology.createBensIndustriais(informacoesEmpresasDb.getSetores2());
                        Property isA3Property = ontology.isAProperty("isA");
                        Resource rodoviario = ontology.createRodoviario(informacoesEmpresasDb.getSetores3());

                        // Comunicações

                        Resource comunicacoes2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA5Property = ontology.isAProperty("isA");
                        Resource telecomunicacoes = ontology
                                        .createTelecomunicacoes(informacoesEmpresasDb.getSetores2());

                        // Consumo não-Ciclico

                        Resource consumo_nao_ciclico2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA7Property = ontology.isAProperty("isA");
                        Resource bebidas = ontology.createBebidas(informacoesEmpresasDb.getSetores2());

                        Resource consumo_nao_ciclico3 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA8Property = ontology.isAProperty("isA");
                        Resource alimentos = ontology.createAlimentos(informacoesEmpresasDb.getSetores2());

                        Resource bebidas2 = ontology.createBebidas(informacoesEmpresasDb.getSetores2());
                        Property isA9Property = ontology.isAProperty("isA");
                        Resource cervejas = ontology.createCerveja(informacoesEmpresasDb.getSetores3());

                        Resource alimentos2 = ontology.createAlimentos(informacoesEmpresasDb.getSetores2());
                        Property isA10Property = ontology.isAProperty("isA");
                        Resource carnes = ontology.createCarnes(informacoesEmpresasDb.getSetores3());

                        // Financeiro

                        Resource financeiro2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA12Property = ontology.isAProperty("isA");
                        Resource intermediarios = ontology.createIntermediario(informacoesEmpresasDb.getSetores2());

                        Resource intermediarios2 = ontology.createIntermediario(informacoesEmpresasDb.getSetores2());
                        Property isA13Property = ontology.isAProperty("isA");
                        Resource bancos = ontology.createBancos(informacoesEmpresasDb.getSetores3());

                        // Materiais Básicos

                        Resource materiais2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA15Property = ontology.isAProperty("isA");
                        Resource mineracao = ontology.createMineracao(informacoesEmpresasDb.getSetores2());

                        Resource mineracao2 = ontology.createMineracao(informacoesEmpresasDb.getSetores2());
                        Property isA16Property = ontology.isAProperty("isA");
                        Resource minerais = ontology.createMinerais(informacoesEmpresasDb.getSetores3());

                        Resource materiais3 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA17Property = ontology.isAProperty("isA");
                        Resource siderurgia = ontology.createSiderurgiaMetal(informacoesEmpresasDb.getSetores2());

                        Resource siderurgia2 = ontology.createSiderurgiaMetal(informacoesEmpresasDb.getSetores2());
                        Property isA18Property = ontology.isAProperty("isA");
                        Resource siderurgia3 = ontology.createSiderurgia(informacoesEmpresasDb.getSetores3());

                        // Petroleo e Gás

                        Resource petroleo2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA20Property = ontology.isAProperty("isA");
                        Resource refino = ontology.createExploracao(informacoesEmpresasDb.getSetores2());

                        // Tecnologia

                        Resource tecnologia2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA22Property = ontology.isAProperty("isA");
                        Resource computadores = ontology.createComputadores(informacoesEmpresasDb.getSetores2());

                        // Utilidade Publica

                        Resource utilidade2 = ontology.createSetor(informacoesEmpresasDb.getSetores1());
                        Property isA24Property = ontology.isAProperty("isA");
                        Resource energia = ontology.createEnergia(informacoesEmpresasDb.getSetores2());

                        ontology.createStatementAndAddToModel(empresa, atuaEmProperty, setor);

                        ontology.createStatementAndAddToModel(bens2, isA1Property, transporte);
                        ontology.createStatementAndAddToModel(transporte2, isA2Property, aereo);
                        ontology.createStatementAndAddToModel(transporte3, isA3Property, rodoviario);

                        ontology.createStatementAndAddToModel(comunicacoes2, isA5Property, telecomunicacoes);

                        ontology.createStatementAndAddToModel(consumo_nao_ciclico2, isA7Property, bebidas);
                        ontology.createStatementAndAddToModel(consumo_nao_ciclico3, isA8Property, alimentos);
                        ontology.createStatementAndAddToModel(bebidas2, isA9Property, cervejas);
                        ontology.createStatementAndAddToModel(alimentos2, isA10Property, carnes);

                        ontology.createStatementAndAddToModel(financeiro2, isA12Property, intermediarios);
                        ontology.createStatementAndAddToModel(intermediarios2, isA13Property, bancos);

                        ontology.createStatementAndAddToModel(materiais2, isA15Property, mineracao);
                        ontology.createStatementAndAddToModel(mineracao2, isA16Property, minerais);
                        ontology.createStatementAndAddToModel(materiais3, isA17Property, siderurgia);
                        ontology.createStatementAndAddToModel(siderurgia2, isA18Property, siderurgia3);

                        ontology.createStatementAndAddToModel(petroleo2, isA20Property, refino);

                        ontology.createStatementAndAddToModel(tecnologia2, isA22Property, computadores);

                        ontology.createStatementAndAddToModel(utilidade2, isA24Property, energia);

                }

                String pesquisa = "", result = "", targetKey = "";
                Query query = null;           

                if(pergunta.contains("Qual foi o preço de fechamento da ação da CSN em 08/05/2023?")){

                        String empresa = "CSNMINERACAO";
                        targetKey = "precoFechamento";

                        pesquisa = "SELECT DISTINCT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?precoFechamento "
                        +
                        " WHERE { " +
                        "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                        +
                        "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                        +
                        "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                        +
                        "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#precoFechamento> ?precoFechamento . "
                        +
                        "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                        +
                        "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                        +
                        "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#" + empresa
                        + ">) ." +
                        " }";           
                }
                else if(pergunta.contains("Qual foi o preço de abertura da CBAV3 em 08/05/2023?")){

                        targetKey = "precoAbertura";

                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?precoAbertura "
                        +
                        " WHERE { " +
                        "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                        +
                        "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                        +
                        "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                        +
                        "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#precoAbertura> ?precoAbertura . "
                        +
                        "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                        +
                        "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                        +
                        "FILTER (?codigo = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#CBAV3>) ." +
                        " }";

                }
                else if(pergunta.contains("Qual o código de negociação da ação da Gerdau?")){

                        targetKey = "codigo";

                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?codigo  " +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#GERDAU>) ." +
                                " }";
                }
                else if(pergunta.contains("Qual foi o volume negociado nas ações do setor bancário em 05/05/2023?")){

                        targetKey = "volume";

                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?volume " +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Financeiro.> . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#volumeNegociado> ?volume . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230505> . "
                                +
                                " }";

                }
                else if(pergunta.contains("Qual foi o volume negociado nas ações de Banco em 05/05/2023?")){

                        targetKey = "volume";
                        pesquisa = "SELECT ?empresa ?setor_atuacao ?setor ?valor_mobiliario ?negociado ?pregao ?codigo ?volume "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> ?setor_atuacao . "
                                +
                                "?setor_atuacao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> ?setor . " +
                                "?setor <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Bancos.>    . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#volumeNegociado> ?volume . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230505> . "
                                +
                                " }";
                }
                else if(pergunta.contains("Qual foi a quantidade de ações do Itau negociadas no pregão de 05/05/2023? ")){

                        targetKey = "totalNegociado";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?totalNegociado "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#totalNegociado> ?totalNegociado . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230505> . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ITAUUNIBANCO>) ."
                                +
                                " }";

                }
                else if(pergunta.contains("Qual foi o preço mínimo da ação da Vale em 08/05/2023?")){

                        targetKey = "?precoMinimo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?precoMinimo "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#precoMinimo> ?precoMinimo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VALE>) ." +
                                " }";
                }
                else if(pergunta.contains("Qual foi o preço mínimo da ação preferencial do Itau em 05/05/2023? ")){

                        targetKey = "precoMinimo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?valor ?acao ?precoMinimo "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#precoMinimo> ?precoMinimo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230505> . "
                                +
                                "?codigo <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temIdentificacao> ?valor . "
                                +
                                "?valor <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> ?acao . " +
                                "?acao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#PREFERENCIAL> . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ITAUUNIBANCO>) ."
                                +
                                " }";
                }
                else if(pergunta.contains("Qual foi o preço máximo da ação ordinária da Gerdau em 08/05/2023?")){

                        targetKey = "precoMaximo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?valor ?acao ?precoMaximo "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#precoMaximo> ?precoMaximo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230505> . "
                                +
                                "?codigo <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temIdentificacao> ?valor . "
                                +
                                "?valor <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> ?acao . " +
                                "?acao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ORDINARIA> . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#GERDAU>) ." +
                                " }";
                }
                else if(pergunta.contains("Quais são as ações do setor elétrico?")){

                        targetKey = "valor_mobiliario";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?setor_atuacao ?negociado ?codigo ?pregao "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> ?setor_atuacao . "
                                +
                                "?setor_atuacao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Energia_Eletrica.> . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }";
                }
                else if(pergunta.contains("Qual foi a variação intradiária absoluta da ação da CSN no pregão de 08/05/2023? ")){

                        targetKey = "variacao_intra_abs";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?pregao ?codigo ?variacao_intra_abs "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#variacaoIntradiariaAbsoluta> ?variacao_intra_abs . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                "FILTER (?empresa = <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VALE>) ." +
                                " }";
                }
                else if(pergunta.contains("Qual ação do setor de mineração que teve a maior alta percentual no pregão do dia 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?setor_atuacao ?valor_mobiliario ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> ?setor_atuacao . "
                                +
                                "?setor_atuacao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Mineracao.> . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY DESC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Qual ação do setor de financeiro que teve a maior baixa percentual no pregão do dia 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?setor_atuacao ?setor1 ?valor_mobiliario ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> ?setor_atuacao . "
                                +
                                "?setor_atuacao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> ?setor1 . " +
                                "?setor1 <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#isA> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Bancos.>    . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY ASC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Qual ação do setor de consumo não ciclíco que teve a menor variação do dia 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#atuaEm> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#Consumo_Nao_Ciclico.> . "
                                +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY ASC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Qual ação com o maior percentual de alta no pregão de 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY DESC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Qual ação com o menor percentual no pregão de 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY ASC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Quais as cinco ações de maior percentual de alta no pregão de 08/05/2023?")){
                       
                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY DESC(?variacaoPercentual)" +
                                "LIMIT 5";
                }
                else if(pergunta.contains("Quais as cinco ações de maior percentual de baixa no pregão de 08/05/2023?")){

                        targetKey = "codigo";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?negociadoDiferente ?pregao ?codigo ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY ASC(?variacaoPercentual)" +
                                "LIMIT 5";
                }
                else if(pergunta.contains("Qual foi a variação intradiária absoluta da ação do IBovespa com o maior percentual de alta no pregão de 08/05/2023?")){

                        targetKey = "variacaoIntradiaAbs";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?negociadoDiferente ?pregao ?codigo ?variacaoIntradiaAbs ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#variacaoIntradiariaAbsoluta> ?variacaoIntradiaAbs . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY DESC(?variacaoPercentual)" +
                                "LIMIT 1";
                }
                else if(pergunta.contains("Qual foi o intervalo intradiário percentual da ação com o maior percentual de baixa entre as ações do IMAT no pregão de 08/05/2023?")){

                        targetKey = "intervaloIntradiaPerc";
                        pesquisa = "SELECT ?empresa ?valor_mobiliario ?negociado ?negociadoDiferente ?pregao ?codigo ?intervaloIntradiaPerc ?variacaoPercentual "
                                +
                                " WHERE { " +
                                "?empresa <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#temValorMobiliarioNegociado> ?valor_mobiliario  . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociado> ?negociado . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoPregoes> ?negociadoDiferente . "
                                +
                                "?negociadoDiferente <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#VariacaoPercentual> ?variacaoPercentual . "
                                +
                                "?valor_mobiliario <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#representadoPor> ?codigo . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#intervaloIntradiarioPercentual> ?intervaloIntradiaPerc . "
                                +
                                "?negociado <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#negociadoDurante> ?pregao . "
                                +
                                "?pregao <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#ocorreEmData> <https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#20230508> . "
                                +
                                " }" +
                                "ORDER BY ASC(?variacaoPercentual)" +
                                "LIMIT 1";
                }

                query= QueryFactory.create(pesquisa);
                result = ontology.queryAndExtract(query, targetKey);
                System.out.println("Resultado: " + result);

                return result;

        }
}
