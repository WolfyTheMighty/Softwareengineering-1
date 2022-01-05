package system.impl;

import datamodel.Article;
import datamodel.Order;
import system.DataRepository;
import system.InventoryManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryManagerImpl implements InventoryManager {

    /**
     * internal data structure to manage inventory (unitsInStore) by Article‐id's.
     */
    private final Map<String, Integer> inventory = new HashMap<>();
    DataRepositoryImpl.RepositoryImpl aRep;

    private InventoryManagerImpl(DataRepositoryImpl.RepositoryImpl aRep) {
        this.aRep = aRep;
    }

    @Override
    public int getUnitsInStock(String id) {
        return 0;
    }

    @Override
    public void update(String id, int updatedUnitsInStock) {

    }

    @Override
    public boolean isFillable(Order order) {
        return false;
    }

    @Override
    public boolean fill(Order order) {
        return false;
    }

    @Override
    public StringBuffer printInventory() {
        return null;
    }

    @Override
    public StringBuffer printInventory(int sortedBy, boolean decending, Integer... limit) {
        return null;
    }

    public void save(Article article) {
        inventory.put(article.getId(), 0);
    }

    private static InventoryManager inventoryManagerInstance = null;

    public static InventoryManager getInstance(DataRepositoryImpl.RepositoryImpl aRep) {
        if (inventoryManagerInstance == null) {
            inventoryManagerInstance = new InventoryManagerImpl(aRep);
        }
        return inventoryManagerInstance;
    }

    @Override
    public Optional findById(long id) {
        return aRep.findByID(id);
    }

    @Override
    public Optional findById(String id) {
        return aRep.findByID(id);
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
