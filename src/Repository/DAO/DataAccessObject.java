package Repository.DAO;

import java.util.List;

public interface DataAccessObject <Type, ID> {
    
    List<Type> loadAll(ID hosID);
    void saveAll(List<Type> items, ID id);
    Type find(ID hosID, String searchKey);
    void delete(Type item, ID hosID);

}
