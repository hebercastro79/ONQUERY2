package repositories;

import models.DadosNovosAntDb;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class DadosNovosAntRepository extends GenericRepository {

    public DadosNovosAntRepository() {
        LoadFirstSheet("src/main/resources/dados_novos_anterior.xlsx");
    }

    public List<DadosNovosAntDb> getAll() {

        List<DadosNovosAntDb> dbList = new ArrayList<>();

        for (Row row : firstSheet) {
            if(row.getRowNum() > 0) {

                List<String> listCell = getListCell(row);

                DadosNovosAntDb db = new DadosNovosAntDb();
                db.setData(listCell.get(2));
                db.setTicker(listCell.get(4));
                db.setEmpresas(listCell.get(6));
                db.setEmpresasant(listCell.get(6));
                db.setAcaoant(listCell.get(7));
                db.setPrecoabre(listCell.get(8));
                db.setPrecoMax(listCell.get(9));
                db.setPrecoMin(listCell.get(10));
                db.setPrecoMed(listCell.get(11));
                db.setPrecofechaant(listCell.get(12));
                db.setTotal(listCell.get(13));
                db.setVolume(listCell.get(15));
                db.setTipo(listCell.get(16));
                dbList.add(db);
            }
        }
        return dbList;
    }
}
