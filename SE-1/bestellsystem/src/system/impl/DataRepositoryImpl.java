package system.impl;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import system.DataRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

class DataRepositoryImpl {

    class RepositoryImpl<T> implements DataRepository.Repository<T> {
        private ArrayList<T> container = new ArrayList<>();

        @Override
        public Optional<T> findById(long id) {
            for (T entity : container) {
                Customer c = (Customer) entity;
                if (c.getId() == id) return Optional.of(entity);
            }

            return Optional.empty();
        }

        @Override
        public Optional<T> findById(String id) {

            for (T entity : container) {
                   Article a = (Article) entity;
                   if (a.getId().equals(id)) return Optional.of(entity);
            }
            return Optional.empty();
        }

        @Override
        public Iterable<T> findAll() {
            return container;
        }

        @Override
        public long count() {
            return container.size();
        }

        @Override
        public T save(T entity) {
            container.add(entity);
            return entity;
        }
    }

    private static DataRepository.Repository<Article> articleRepositoryInstance = null;
    public DataRepository.Repository<Article> getArticleRepository() {

        if(articleRepositoryInstance == null){
            articleRepositoryInstance = new RepositoryImpl<Article>();
        }
        return articleRepositoryInstance;
    }

    private static DataRepository.Repository<Order> orderRepositoryInstance = null;
    public DataRepository.Repository<Order> getOrderRepository() {
        if(orderRepositoryInstance == null){
            orderRepositoryInstance = new RepositoryImpl<Order>();
        }
        return orderRepositoryInstance;
    }


    private static DataRepository.Repository<Customer> customerRepositoryInstance = null;
    public DataRepository.Repository<Customer> getCustomerRepository() {
        if(customerRepositoryInstance == null){
            customerRepositoryInstance = new RepositoryImpl<Customer>();
        }
        return customerRepositoryInstance;
    }

}
