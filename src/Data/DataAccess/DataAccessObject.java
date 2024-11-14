package Data.DataAccess;

import java.util.List;

public interface DataAccessObject<Type, ID> {

    // Load all records associated with a specific ID
    List<Type> loadAll();

    // Save a specific record associated with a specific ID (supports create or update)
    void save(Type item);

    // Find a specific record by ID and an additional search key
    Type find(ID id, String searchKey);

    // Delete a specific record associated with a given ID
    void delete(ID id, String searchKey);

}

