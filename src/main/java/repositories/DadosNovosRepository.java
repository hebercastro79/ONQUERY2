package repositories;

import models.DadosNovosDb;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class DadosNovosRepository extends GenericRepository {

    public DadosNovosRepository() {
        LoadFirstSheet("src/main/resources/dados_novos_atual.xlsx");
    }

    public List<DadosNovosDb> getAll() {

        List<DadosNovosDb> dbList = new ArrayList<>();

        for (Row row : firstSheet) {
            if(row.getRowNum() > 0) {

                List<String> listCell = getListCell(row);

                DadosNovosDb db = new DadosNovosDb();
                db.setData(listCell.get(2));
                db.setTicker(listCell.get(4));
                db.setEmpresas(listCell.get(6));
                db.setEmpresas(listCell.get(6));
                db.setAcao(listCell.get(7));
                db.setPrecoabre(listCell.get(8));
                db.setPrecoMax(listCell.get(9));
                db.setPrecoMin(listCell.get(10));
                db.setPrecoMed(listCell.get(11));
                db.setPrecofecha(listCell.get(12));
                db.setTotal(listCell.get(14));
                db.setVolume(listCell.get(15));
                db.setTipo(listCell.get(16));
                dbList.add(db);
            }
        }
        return dbList;
    }
}
