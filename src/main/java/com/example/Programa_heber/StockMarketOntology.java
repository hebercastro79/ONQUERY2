package com.example.Programa_heber;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.riot.Lang;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockMarketOntology {
    private static final String BASE_URI_ONTOLOGY = "https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#";
    private static final String BASE_URI_TRIPLES = "https://dcm.ffclrp.usp.br/lssb/stock-market-ontology#";


    Model model;

    public StockMarketOntology() {
        String uri = "src/main/resources/Stock-Market-Ongology-200.rdf";
        Model model = ModelFactory.createOntologyModel().read(uri);
        this.model = model;
    }

    public void print() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/output.txt"))) {
            //StmtIterator iter = model.listStatements();
            //while (iter.hasNext()) {
            //    Statement stmt = iter.next();
            //    RDFNode subject = stmt.getSubject();
            //    RDFNode predicate = stmt.getPredicate();
            //    RDFNode object = stmt.getObject();
            //    writer.write(subject.toString() + " " + predicate.toString() + " " + object.toString() + System.lineSeparator());
            //}
            //* Print in RDFJSON format *
            //org.apache.jena.riot.RDFDataMgr.write(writer, model, Lang.NTRIPLES);
        }
    }

    public Resource createEmpresa(String empresa) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Empresa_Capital_Aberto
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Empresa_Capital_Aberto");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + empresa, type);

        return resource;
    }



    public Property getProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #temValorMobiliarioNegociado
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createValor(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Valor_Mobiliario_Negociado
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Valor_Mobiliario_Negociado");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor , type);

        return resource;
    }


    public Property representadoPorProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #representadoPor
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createCodNegociacao(String codNegociacao) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Codigo_Negociacao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Codigo_Negociacao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + codNegociacao, type);

        return resource;
    }


    public Property negociadoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #negociado
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createNegociadoEm(String ticker) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Negociado_Em_Pregao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Negociado_Em_Pregao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + ticker, type);

        return resource;
    }

    public Resource createNegociadoEmDiferentes(String ticker) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Negociado_Em_Diferentes_Pregoes
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Negociado_Em_Diferentes_Pregoes");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + ticker, type);

        return resource;
    }

    public Property negociadoPregaoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #negociadoPregoes
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }


    public Property duranteProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #negociadoDurante
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createPregao(String pregao) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Pregao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Pregao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + pregao, type);

        return resource;
    }


    public Property dataProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #OcorreEmData
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createData(String data) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #OcorreEmData
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "OcorreEmData");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + data, type);

        return resource;
    }


    public Property precofechaProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #precoFechamento
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }


    public Resource createPrecoFecha(String preco) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #precoFechamento
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "precoFechamento");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + preco, type);

        return resource;
    }

    public Resource createPrecoAbre(String preco) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #precoAbertura
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "precoAbertura");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + preco, type);

        return resource;
    }

    public Property precoAberturaProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #precoAbertura
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Property precoMaximoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #precoMaximo
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createPrecoMax(String preco) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #precoMaximo
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "precoMaximo");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + preco, type);

        return resource;
    }

    public Property precoMinimoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #precoMinimo
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createPrecoMin(String preco) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #precoMinimo
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "precoMinimo");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + preco, type);

        return resource;
    }

    public Property totalNegociadoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #totalNegociado
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createTotal(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #totalNegociado
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "totalNegociado");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Property volumeNegociadoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #volumeNegociado
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createVolume(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #volumeNegociado
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "volumeNegociado");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Property atuaEmProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #atuaEm
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createSetor(String setor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Setor_Atuacao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Setor_Atuacao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + setor, type);

        return resource;
    }

    public Property temIdentificacaoProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #temIdentificacao
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createValor_Mobiliario(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Valor_Mobiliario
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Valor_Mobiliario");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Property isAProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #is_A
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Resource createPreferencial(String tipo) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Preferencial
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Preferencial");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + tipo, type);

        return resource;
    }

    public Resource createOrdinaria(String tipo) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Ordinaria
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Ordinaria");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + tipo, type);

        return resource;
    }

    public Resource createAcao(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Acao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Acao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createBensIndustriais(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Bens_Industriais
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Bens_Industriais");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createTransporte(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Transporte
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Transporte");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createRodoviario(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Rodoviario
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Rodoviario");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createAereo(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Aereo
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Aereo");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }



    public Resource createComunicacoes(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Comunicacoes
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Comunicacoes");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createTelecomunicacoes(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Telecomunicacoes
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Telecomunicacoes");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }


    public Resource createConsumoCiclico(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Consumo_Ciclico
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Consumo_Ciclico");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createConsumoNaoCiclico(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Consumo_Nao_Ciclico
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Consumo_Nao_Ciclico");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createAlimentos(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Alimentos_Processados
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Alimentos_Processados");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createCarnes(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Carnes_e_Derivados
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Carnes_e_Derivados");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createBebidas(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Bebidas
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Bebidas");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createCerveja(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Cerveja_e_Refrigerantes
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Cerveja_e_Refrigerantes");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createFinanceiro(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Financeiro
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Financeiro");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createIntermediario(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Intermediarios_Financeiros
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Intermediarios_Financeiros");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createBancos(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Bancos
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Bancos");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createMateriaisBasicos(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Materiais_Basicos
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Materiais_Basicos");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createMineracao(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Mineracao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Mineracao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createMinerais(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Minerais_Metalicos
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Minerais_Metalicos");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createSiderurgiaMetal(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Siderurgia_e_Metalurgia
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Siderurgia_e_Metalurgia");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createSiderurgia(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Siderurgia
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Siderurgia");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createOutros(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Outros
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Outros");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createPetroleo(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Petroleo_Gas_e_Biocombustivel
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Petroleo_Gas_e_Biocombustivel");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createExploracao(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Exploracao._Refino_e_Distribuicao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Exploracao._Refino_e_Distribuicao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createSaude(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Saude
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Saude");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createTecnologia(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Tecnologia_da_Informacao
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Tecnologia_da_Informacao");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createComputadores(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Computadores_e_Equipamentos
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Computadores_e_Equipamentos");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createUtilidadePublica(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Utilidade_Publica
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Utilidade_Publica");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createEnergia(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #Energia_Eletrica
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "Energia_Eletrica");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }


    public Resource createvariacaoAbsoluta(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #variacaoIntradiariaAbsoluta
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "variacaoIntradiariaAbsoluta");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createvariacaoPercentual(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #variacaoIntradiariaPercentual
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "variacaoIntradiariaPercentual");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createintervaloAbsoluto(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #intervaloIntradiarioAbsoluto
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "intervaloIntradiarioAbsoluto");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Resource createintervaloPercentual(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #intervaloIntradiarioPercentual
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "intervaloIntradiarioPercentual");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }

    public Property variacaoIntraAbsProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #variacaoIntradiariaAbsoluta
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Property variacaoIntraPercProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #variacaoIntradiariaPercentual
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Property intervaloIntraAbsProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #intervaloIntradiarioAbsoluto"
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }

    public Property intervaloIntraPercProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #intervaloIntradiarioPercentual"
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }


    public Resource createAbsoluta(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #variacaoAbsoluta
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "variacaoAbsoluta");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }



    public Resource createPercentual(String valor) {
        // Percorre o Model e busca o Resource que possui a URI definida que nesse caso é #variacaoPercentual
        Resource type = model.getResource(BASE_URI_ONTOLOGY + "variacaoPercentual");

        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + valor, type);

        return resource;
    }


    public Property percentualProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #variacaoPercentual"
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }



    public Property absolutaProperty(String propertyName) {
        // Percorre o Model e busca a Property que possui a URI definida que nesse caso é #variacaoAbsoluta"
        Property property = model.getProperty(BASE_URI_ONTOLOGY + propertyName);

        return property;
    }


    public Resource createTicketEDataPregao(String ticker, String dataPregao) {


        // Cria um elemento da tripla, definindo a URI desse recurso e definido o type dele.
        Resource resource = model.createResource(BASE_URI_TRIPLES + ticker + "_" + dataPregao);

        return resource;
    }



    public Literal createLiteral(String valor) {
        return model.createLiteral(valor);
    }


    public Model createStatementAndAddToModel(Resource subject, Property property, RDFNode object) {
        Statement stmt = new StatementImpl(subject, property, object);

        //System.out.println("Creating triple: " + subject + " -> " + property + " -> " + object);

        // Adiciona o statement no Model
        model.add(stmt);

        return model;
    }


    // public void queryAndPrint(Query query) {

    //     try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
    //         ResultSet results = qexec.execSelect();
    //         ResultSetFormatter.out(System.out, results, query);
    //     }

    // }

    // public String queryAndPrint(Query query) {
    //     String resultString = "";
    //     try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
    //         ResultSet results = qexec.execSelect();

    //         // Iterate over the ResultSet and capture the closing price
    //         if (results.hasNext()) {
    //             QuerySolution soln = results.nextSolution();
    //             if (soln.contains("precoFechamento")) {
    //                 resultString = soln.get("precoFechamento").toString();
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return resultString;
    // }


    public List<Map<String, String>> queryAndPrint(Query query) {
        List<Map<String, String>> resultsList = new ArrayList<>();
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Map<String, String> row = new HashMap<>();
                
                // Iterate over the variable names in the query result
                for (String varName : results.getResultVars()) {
                    if (soln.contains(varName)) {
                        row.put(varName, soln.get(varName).toString());
                    }
                }
                
                resultsList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultsList;
    }


    public String queryAndExtract(Query query, String targetVariable) {
        String resultValue = "";
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                
                // Check if the target variable exists in the solution and extract its value
                if (soln.contains(targetVariable)) {
                    String value = soln.get(targetVariable).toString();
                    // Check if the value is a literal or a URI and extract the actual value
                    if (value.startsWith("http")) {
                        // If it's a URI, extract the part after the last '#'
                        resultValue = value.substring(value.lastIndexOf("#") + 1);
                    } else {
                        resultValue = value;
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultValue;
    }


}
