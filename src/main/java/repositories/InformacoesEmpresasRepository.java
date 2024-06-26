package repositories;

import models.InformacoesEmpresasDb;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class InformacoesEmpresasRepository extends GenericRepository {

    public InformacoesEmpresasRepository() {
        LoadFirstSheet("src/main/resources/Informacoes_Empresas.xlsx");
    }


    public List<InformacoesEmpresasDb> getAll() {

        List<InformacoesEmpresasDb> dbList = new ArrayList<>();

        for (Row row : firstSheet) {
            if(row.getRowNum() > 0) {

                List<String> listCell = getListCell(row);

                InformacoesEmpresasDb db = new InformacoesEmpresasDb();
                db.setEmpresas(listCell.get(0));
                db.setCodigoCell(listCell.get(1));
                db.setSetores(listCell.get(2));
                db.setSetores1(listCell.get(3));
                db.setSetores2(listCell.get(4));
                db.setSetores3(listCell.get(5));

                dbList.add(db);
            }
        }
        return dbList;
    }
}
