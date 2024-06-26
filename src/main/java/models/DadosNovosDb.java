package models;

public class DadosNovosDb {

    private String data;            // Coluna 2 da planilha
    private String ticker;          // Coluna 4 da planilha
    private String nomeEmpresa;     // Coluna 6 da planilha
    private String empresas;        // Coluna 6 da planilha
    private String acao;            // Coluna 7 da planilha
    private String precoabre;       // Coluna 8 da planilha;
    private String precoMax;        // Coluna 9 da planilha;
    private String precoMin;        // Coluna 10 da planilha;
    private String precoMed;        // Coluna 11 da planilha;
    private String precofecha;      // Coluna 12 da planilha;
    private String total;           // Coluna 14 da planilha;
    private String volume;          // Coluna 15 da planilha;
    private String tipo;            // Coluna 16 da planilha;


    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setEmpresa(String empresas) {
        this.empresas = empresas.replace(" ", "_");
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCodNegociacao() {
        return getNomeEmpresa() + "_" + getTicker();
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getValor() {
        return getCodNegociacao() + "_" + getAcao();
    }

    public String getValorMobiliario() {
        return getEmpresas() + "_" + getTicker() + "_" + getAcao();
    }

    public String getValor_Mobiliario() {
        return getTicker() + "_" + getAcao();
    }

    public String getPregao() {
        return "Pregao_B3_" + getData();
    }

    public String getNegocio() {
        return getTicker() + "_" + getData();
    }

    public String getNegociadoEm() {
        return getTicker() + "_" + getData();
    }

    public String getEmpresas() {
        return empresas;
    }

    public void setEmpresas(String empresas) {
        this.empresas = empresas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrecofecha() {
        return precofecha;
    }

    public void setPrecofecha(String precofecha) {
        this.precofecha = precofecha.replace(",", ".");
    }

    public String getPrecoabre() {
        return precoabre;
    }

    public void setPrecoabre(String precoabre) {
        this.precoabre = precoabre.replace(",", ".");
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(String precoMax) {
        this.precoMax = precoMax.replace(",", ".");
    }

    public String getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(String precoMin) {
        this.precoMin = precoMin.replace(",", ".");
    }

    public String getPrecoMed() {
        return precoMed;
    }

    public void setPrecoMed(String precoMed) {
        this.precoMed = precoMed;
    }

    public double getPrecoAbertura() {
        return Double.parseDouble(getPrecoabre());
    }

    public double getPrecoFechamento() {
        return Double.parseDouble(getPrecofecha());
    }

    public double getPrecoMaximo() {
        return Double.parseDouble(getPrecoMax());
    }

    public double getPrecoMinimo() {
        return Double.parseDouble(getPrecoMin());
    }

    public String getVariacaoIntradiariaAbsoluta() {
        return String.valueOf(getPrecoFechamento() - getPrecoAbertura());
    }

    public String getIntervaloIntradiarioAbsoluto() {
        return String.valueOf(getPrecoMaximo() - getPrecoMinimo());
    }

    public double getVariacaoIntradiariaAbs() {
        return Double.parseDouble(getVariacaoIntradiariaAbsoluta());
    }

    public String getVariacaoIntradiariaPercentual() {
        return String.valueOf(getVariacaoIntradiariaAbs() / getPrecoAbertura());
    }

    public double getIntervaloIntradiariaAbs() {
        return Double.parseDouble(getIntervaloIntradiarioAbsoluto());
    }

    public String getIntervaloIntradiarioPercentual() {
        return String.valueOf(getIntervaloIntradiariaAbs() / getPrecoAbertura());
    }
}