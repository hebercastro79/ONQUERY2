package models;

public class DadosNovosAntDb {

    private String data;            // Coluna 2 da planilha
    private String ticker;          // Coluna 4 da planilha
    private String empresas;        // Coluna 6 da planilha
    private String empresasant;     // Coluna 6 da planilha
    private String acaoant;         // Coluna 7 da planilha
    private String precoabre;       // Coluna 8 da planilha;
    private String precoMax;        // Coluna 9 da planilha;
    private String precoMin;        // Coluna 10 da planilha;
    private String precoMed;        // Coluna 11 da planilha;
    private String precofechaant;   // Coluna 12 da planilha;
    private String total;           // Coluna 13 da planilha;
    private String volume;          // Coluna 15 da planilha;
    private String tipo;            // Coluna 16 da planilha;

    public String getEmpresas() {
        return empresas;
    }

    public void setEmpresas(String empresas) {
        this.empresas = empresas.replace(" ", "_");
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCodNegociacaoant() {
        return getEmpresas() + "_" + getTicker();
    }

    public String getValorMobiliarioant() {
        return getEmpresas() + "_" + getTicker() + "_" + getAcaoant();
    }

    public String getValor_Mobiliario() {
        return getTicker() + "_" + getAcaoant();
    }

    public String getNegociadoEmant() {
        return getTicker() + "_" + getData();
    }

    public String getPregaoant() {
        return "Pregao_B3" + getData();
    }

    public String getNegocio() {
        return getTicker() + "_" + getData();
    }

    public String getAcaoant() {
        return acaoant;
    }

    public void setAcaoant(String acaoant) {
        this.acaoant = acaoant;
    }

    public String getValorant() {
        return getCodNegociacaoant() + "_" + getAcaoant();
    }

    public String getEmpresasant() {
        return empresasant;
    }

    public void setEmpresasant(String empresasant) {
        this.empresasant = empresasant;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrecofechaant() {
        return precofechaant;
    }

    public void setPrecofechaant(String precofechaant) {
        this.precofechaant = precofechaant.replace(",", ".");
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
        return Double.parseDouble(getPrecofechaant());
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
