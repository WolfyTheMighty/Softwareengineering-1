package application;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Application extends javafx.application.Application {

    @Override
    public void start( Stage stage ) {
        try {
            stage.setTitle( "Hello JavaFX" );
            VBox root = new VBox();
            Scene scene = new Scene( root, 400, 400 );
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene( scene );
            Button btn = new Button( "Hello World!" );
            btn.setOnAction( e -> System.out.println( "Hello World!" ) );
            Button btn2 = new Button("EXIT");
            btn2.setOnAction( e -> Platform.exit());
            root.getChildren().addAll( btn,btn2 );
           // root.setId("root");
            stage.show();


        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        launch( args );
    }
}
