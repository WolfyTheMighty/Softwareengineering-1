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
	 * Public interface of the generic repository.
	 *
	 */
	interface Repository<T> {

		/**
		 * finds Object with given long id
		 * @param id id to be searched for
		 * @return Optional object with given id or empty optional
		 */
		Optional findById( long id );
		/**
		 * finds Object with given long id
		 * @param id id to be searched for
		 * @return Optional object with given id or empty optional
		 */
		Optional findById( String id );

		/**
		 * Gives a list with all objets in the repository
		 * @return Iterable with all objects in rep
		 */
		Iterable<T> findAll();

		/**
		 * Amount of entries in repository
		 * @return Amount of entries in repository
		 */
		long count();

		/**
		 * Saves a entity to the repository
		 * @param entity Generic entety to be added to repository
		 * @return entity which was saved
		 */
		T save( T entity );
	}


}
