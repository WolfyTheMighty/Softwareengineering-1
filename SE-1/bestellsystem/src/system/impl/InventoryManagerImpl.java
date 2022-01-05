package system.impl;

import datamodel.Article;
import datamodel.Currency;
import datamodel.Order;
import system.DataRepository;
import system.Formatter;
import system.InventoryManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        return printInventory(
                StreamSupport.stream( aRep.findAll().spliterator(), false )
        );
    }

//    @Override
    private StringBuffer printInventory( Stream<Article> articleStream ) {
//    public StringBuffer printInventory(int sortedBy, boolean decending, Integer... limit) {
        //
        Formatter formatter = new FormatterImpl();
        Formatter.TableFormatter tfmt = new TableFormatterImpl( formatter, new Object[][] {
                // five column table with column specs: width and alignment ('[' left, ']' right)
                { 12, '[' }, { 32, '[' }, { 12, ']' }, { 10, ']' }, { 14, ']' }
        })
                .liner( "+-+-+-+-+-+" )		// print table header
                .hdr( "||", "Inv.-Id", "Article / Unit", "Unit", "Units", "Value" )
                .hdr( "||", "", "", "Price", "in-Stock", "(in €)" )
                .liner( "+-+-+-+-+-+" )
                ;
        //
        long totalValue = articleStream
                .map( a -> {
                    long unitsInStock = this.inventory.get( a.getId() ).intValue();
                    long value = a.getUnitPrice() * unitsInStock;
                    tfmt.hdr( "||",
                            a.getId(),
                            a.getDescription(),
                            formatter.fmtPrice( a.getUnitPrice(), a.getCurrency()).toString(),
                            Long.toString( unitsInStock ),
                            formatter.fmtPrice( value, a.getCurrency() ).toString()
                    );
                    return value;
                })
                .reduce( 0L, (a, b) -> a + b );
        //
        String inventoryValue = formatter.fmtPrice( totalValue, Currency.EUR ).toString();
        tfmt
                .liner( "+-+-+-+-+-+" )
                .hdr( "", "", "Invent", "ory Value:", inventoryValue )
        ;
        //
        return tfmt.getFormatter().getBuffer();
    }

//    public Article save(Article article) {
//
//    }

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
        Article article = (Article) entity;
        if( article == null )
            throw new IllegalArgumentException( "illegal article: null" );
        //
        String id = article.getId();
        if( id == null )
            throw new IllegalArgumentException( "illegal article.id: null" );
        //
        aRep.save( article );	// save, make sure to avoid duplicates
        //
        if( ! inventory.containsKey( id ) ) {
            this.inventory.put( id, Integer.valueOf( 0 ) );
        }
        return article;
    }
}
