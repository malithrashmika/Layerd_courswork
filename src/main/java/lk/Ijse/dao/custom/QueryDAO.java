package lk.Ijse.dao.custom;



import lk.Ijse.dao.SuperDAO;
import lk.Ijse.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> searchOrder(String oid) throws SQLException, ClassNotFoundException;
}
