package system.impl;

import datamodel.Article;
import datamodel.Currency;
import datamodel.Order;
import datamodel.OrderItem;
import system.DataRepository;
import system.Formatter;
import system.InventoryManager;

import java.util.*;
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
        return inventory.get(id);
    }

    @Override
    public void update(String id, int updatedUnitsInStock) {
        inventory.put(id, updatedUnitsInStock);
    }

    @Override
    public boolean isFillable(Order order) throws IllegalArgumentException {
        if (order == null) throw new IllegalArgumentException();
        for (OrderItem oi : order.getItemsAsArray()) {
            if (oi.getUnitsOrdered() > inventory.get(oi.getArticle().getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fill(Order order) {
        if (isFillable(order)) {
            for (OrderItem oi : order.getItemsAsArray()) {
                inventory.put(oi.getArticle().getId(), inventory.get(oi.getArticle().getId()) - oi.getUnitsOrdered());
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public StringBuffer printInventory() {
//        return printInventory(
//                StreamSupport.stream(aRep.findAll().spliterator(), false)
//        );
        return printInventory(
                StreamSupport.stream(aRep.findAll().spliterator(), false), 3, true
        );
    }

    @Override
    public StringBuffer printInventory(Stream<Article> articleStream, int sortedBy, boolean decending, Integer... limit) {
        Formatter formatter = new FormatterImpl();
        Formatter.TableFormatter tfmt = new TableFormatterImpl(formatter, new Object[][]{
                // five column table with column specs: width and alignment ('[' left, ']' right)
                {12, '['}, {32, '['}, {12, ']'}, {10, ']'}, {14, ']'}
        })
                .liner("+-+-+-+-+-+")        // print table header
                .hdr("||", "Inv.-Id", "Article / Unit", "Unit", "Units", "Value")
                .hdr("||", "", "", "Price", "in-Stock", "(in €)")
                .liner("+-+-+-+-+-+");
        //


        long totalValue = articleStream
                .limit(limit != null  && limit.length > 0 && limit[0] > 0 ? limit[0]: aRep.count())
                .sorted((a1, a2) -> {
                    boolean des = decending;
                            switch (sortedBy) {
                                case 1 -> {
                                    if(des){
                                        return Long.compare(a2.getUnitPrice(), a1.getUnitPrice());
                                    }
                                     return Long.compare(a1.getUnitPrice(), a2.getUnitPrice());
                                }

                                case 2 -> {
                                    if(des) {
                                        return Long.compare(a2.getUnitPrice() * this.inventory.get(a2.getId()), a1.getUnitPrice() * this.inventory.get(a1.getId()));

                                    }
                                    return Long.compare(a1.getUnitPrice() * this.inventory.get(a1.getId()), a2.getUnitPrice() * this.inventory.get(a2.getId()));
                                }
                                case 3 -> {
                                    if(des) {
                                        return Long.compare(this.inventory.get(a2.getId()), this.inventory.get(a1.getId()));

                                    }
                                    return Long.compare(this.inventory.get(a1.getId()), this.inventory.get(a2.getId()));
                                }
                                case 4 -> {
                                    if(des) {
                                        return CharSequence.compare(a2.getDescription(), a1.getDescription());

                                    }
                                    return CharSequence.compare(a1.getDescription(), a2.getDescription());
                                }
                                case 5 -> {
                                    if(des) {
                                        return CharSequence.compare(a2.getId(), a1.getId());

                                    }
                                    return CharSequence.compare(a1.getId(), a2.getId());
                                }

                            }
                            return 0;
                        }

                )
//                .sorted(() -> {
//                    decending ? Collections.reverseOrder(): 0;
//                })

//        if (decending) {
//           articleStream = articleStream.sorted(Collections.reverseOrder());
//        }
//
//        long totalValue = articleStream
        .map(a -> {
                    long unitsInStock = this.inventory.get(a.getId());
                    long value = a.getUnitPrice() * unitsInStock;
                    tfmt.hdr("||",
                            a.getId(),
                            a.getDescription(),
                            formatter.fmtPrice(a.getUnitPrice(), a.getCurrency()).toString(),
                            Long.toString(unitsInStock),
                            formatter.fmtPrice(value, a.getCurrency()).toString()
                    );
                    return value;
                })
                .reduce(0L, (a, b) -> a + b);
        //
        String inventoryValue = formatter.fmtPrice(totalValue, Currency.EUR).toString();
        tfmt
                .liner("+-+-+-+-+-+")
                .hdr("", "", "Invent", "ory Value:", inventoryValue)
        ;
        //
        return tfmt.getFormatter().getBuffer();
    }

//    private StringBuffer printInventory(Stream<Article> articleStream) {
////
//        Formatter formatter = new FormatterImpl();
//        Formatter.TableFormatter tfmt = new TableFormatterImpl(formatter, new Object[][]{
//                // five column table with column specs: width and alignment ('[' left, ']' right)
//                {12, '['}, {32, '['}, {12, ']'}, {10, ']'}, {14, ']'}
//        })
//                .liner("+-+-+-+-+-+")        // print table header
//                .hdr("||", "Inv.-Id", "Article / Unit", "Unit", "Units", "Value")
//                .hdr("||", "", "", "Price", "in-Stock", "(in €)")
//                .liner("+-+-+-+-+-+");
//        //
//        long totalValue = articleStream
//                .map(a -> {
//                    long unitsInStock = this.inventory.get(a.getId());
//                    long value = a.getUnitPrice() * unitsInStock;
//                    tfmt.hdr("||",
//                            a.getId(),
//                            a.getDescription(),
//                            formatter.fmtPrice(a.getUnitPrice(), a.getCurrency()).toString(),
//                            Long.toString(unitsInStock),
//                            formatter.fmtPrice(value, a.getCurrency()).toString()
//                    );
//                    return value;
//                })
//                .reduce(0L, (a, b) -> a + b);
//        //
//        String inventoryValue = formatter.fmtPrice(totalValue, Currency.EUR).toString();
//        tfmt
//                .liner("+-+-+-+-+-+")
//                .hdr("", "", "Invent", "ory Value:", inventoryValue)
//        ;
//        //
//        return tfmt.getFormatter().getBuffer();
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
        return aRep.findAll();
    }

    @Override
    public long count() {
        return aRep.count();
    }

    @Override
    public Object save(Object entity) {
        Article article = (Article) entity;
        if (article == null)
            throw new IllegalArgumentException("illegal article: null");
        //
        String id = article.getId();
        if (id == null)
            throw new IllegalArgumentException("illegal article.id: null");
        //
        aRep.save(article);    // save, make sure to avoid duplicates
        //
        if (!inventory.containsKey(id)) {
            this.inventory.put(id, Integer.valueOf(0));
        }
        return article;
    }
}
