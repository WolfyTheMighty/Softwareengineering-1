package system;

import java.util.Optional;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;


/**
 * Public interface of data repositories that store objects (entities)
 * of data model classes.
 *
 */

public interface DataRepository {

	/**
	 * Public interface of the Customer repository.
	 *
	 */
	interface Repository<T> {
		Optional<T> findById( long id );
		Optional<T> findById( String id );
		Iterable<T> findAll();
		long count();
		T save( T entity );
	}


}
