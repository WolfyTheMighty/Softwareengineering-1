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
            return Optional.empty();
        }

        @Override
        public Optional<T> findById(T,ID) {
            for (T entity : container) {
                    entity.getID;
            }
            return;
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


    public DataRepository.Repository<Article> getArticleRepository() {

        return new RepositoryImpl<Article>();
    }

    public DataRepository.Repository<Order> getOrderRepository() {

        return new RepositoryImpl<Order>();
    }

    public DataRepository.Repository<Customer> getCustomerRepository() {

        return new RepositoryImpl<Customer>();
    }

}
