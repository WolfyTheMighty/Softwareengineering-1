package application;

import datamodel.Order;
import system.OrderBuilder;
import system.RTE;
import system.RTE.Runtime;
//import system.impl.OrderBuilderImpl;

import java.io.IOException;

import static system.RTE.Configuration.*;


/**
 * Class with main() function.
 * 
 * @author Arpad Horvath
 *
 */
public class Application_E34 {


	/**
	 * main() function.
	 * 
	 * @param args arguments passed to main() function
	 */
	public static void main( String[] args ) {
		//

		//idee
		//plan
		//design
		//implementierung
		//test
		System.out.println( "SE1 Bestellsystem" );

		Runtime runtime = RTE.getInstance()
			//
			.create( config -> {
				/*
				 * configure customer and article data imported from JSON
				 */
				config.put( KEY_DATASOURCE, JSON_DATASOURCE );
				config.put( KEY_DATASOURCE_CUSTOMER, "bestellsystem/src/data/customers_10.json" );
				config.put( KEY_DATASOURCE_ARTICLE, "bestellsystem/src/data/articles_9.json" );
			})
			//
			.launch( (config, rt) -> {
				/*
				 * launch runtime system, load JSON data
				 */
				System.out.println( "launching..." );
				rt.loadData();
				System.out.println( "system is running..." );
			});

//		OrderBuilder ob = OrderBuilder.getInstance( runtime );
		//
		OrderBuilder ob = runtime.getOrderBuilder();
		ob.build();		// build and save orders to OrderRepository

		Iterable<Order> orders = runtime.getOrderRepository().findAll();
		StringBuffer sb = runtime.getPrinter().printOrders( orders );

		System.out.println( sb.toString() );

		long count = runtime.getOrderRepository().count();
//		Iterable<Order> orders = runtime.getOrderRepository().findAll();

		String filepath = "src/output/orders.txt";  // path to save orders as table
		try {
			runtime.getPrinter().printOrdersToFile( orders, filepath );
			System.out.println( count + " orders printed to: " + filepath );
		} catch( IOException e ) {
			System.err.println( "Error printing orders to: " + filepath +
					", reason: " + e.getMessage() );
		}


		runtime.shutdown( rt -> { System.out.println( "...shutting down." ); } );
	}

}
