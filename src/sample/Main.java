package sample;

import cucumber.api.java.cy_gb.A;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.miginfocom.layout.Grid;
import org.apache.log4j.Layout;

public class Main extends Application {

    Scene scene;
    Label  NO_OF_PPL_LABEL, OCC_PERCENTAGE_LABEL, DAYS_OF_SERVICE_LABEL, KG_OF_CLOTH_PER_PPL_LABEL, WORKING_HOURS_PER_WEEK_LABEL, R_VALUE_LABEL, AVG_H_PROD; //CHOOSE FROM LIST
    TextField  NO_OF_PPL_TEXT, OCC_PERCENTAGE_TEXT, DAYS_OF_SERVICE_TEXT, WORKING_HOURS_PER_WEEK_TEXT;
    ChoiceBox<String> KG_OF_CLOTH_PER_PPL_MENU, R_VALUE_MENU;
    Button compute;


    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Υπολογιστής μέσης παραγωγής ανά ώρα");


        GridPane grid = new GridPane();

        // Maximum hotel capacity
        NO_OF_PPL_LABEL = new Label("Μέγιστος αριθμός ατόμων που μπορείς να φιλοξενήσεις: ");
        NO_OF_PPL_TEXT = new TextField();

        grid.add(NO_OF_PPL_LABEL, 0, 1);
        grid.add(NO_OF_PPL_TEXT, 1, 1);

        // Average occupation percentage
        OCC_PERCENTAGE_LABEL = new Label("Μ.Ο. Πληρώτητας (ποσοστό) %");
        OCC_PERCENTAGE_TEXT = new TextField("");

        grid.add(OCC_PERCENTAGE_LABEL, 0, 2);
        grid.add(OCC_PERCENTAGE_TEXT, 1, 2);

        // Days of week that white sheets get changed
        DAYS_OF_SERVICE_LABEL = new Label("Πόσες μέρες τη βδομάδα θα αλλάζουν τα λευκά είδη: ");
        DAYS_OF_SERVICE_TEXT = new TextField();

        grid.add(DAYS_OF_SERVICE_LABEL, 0, 3);
        grid.add(DAYS_OF_SERVICE_TEXT, 1,3 );

        // Kg Of cloth per person per day
        KG_OF_CLOTH_PER_PPL_LABEL = new Label("Κιλά ρούχων ανα άτομο: ");
        KG_OF_CLOTH_PER_PPL_MENU = new ChoiceBox<>();
        KG_OF_CLOTH_PER_PPL_MENU.getItems().addAll("Ξενοδοχείο *", "Ξενοδοχείο **", "Ξενοδοχείο ***", "Ξενοδοχείο ****", "Ξενοδοχείο *****");
        KG_OF_CLOTH_PER_PPL_MENU.setValue("Ξενοδοχείο *");

        grid.add(KG_OF_CLOTH_PER_PPL_LABEL, 0, 4);
        grid.add(KG_OF_CLOTH_PER_PPL_MENU, 1, 4);

        // Working hours by week
        WORKING_HOURS_PER_WEEK_LABEL = new Label("Ώρες εργασίας προσωπικού ανα βδομάδα: ");
        WORKING_HOURS_PER_WEEK_TEXT = new TextField();

        grid.add(WORKING_HOURS_PER_WEEK_LABEL, 0, 5);
        grid.add(WORKING_HOURS_PER_WEEK_TEXT, 1, 5);

        // Constant R
        R_VALUE_LABEL = new Label("Σταθερά R");
        R_VALUE_MENU = new ChoiceBox<>();
        R_VALUE_MENU.getItems().addAll("Νοσοκομείο ή κλινική", "Άλλη εγκατάσταση");
        R_VALUE_MENU.setValue("Νοσοκομείο ή κλινική");

        grid.add(R_VALUE_LABEL, 0, 6);
        grid.add(R_VALUE_MENU, 1, 6);

        // Button and result
        AVG_H_PROD = new Label("");
        compute = new Button("Υπολόγισε");
        compute.setOnAction(e -> {

            int n = Integer.parseInt(NO_OF_PPL_TEXT.getText());
            int m = Integer.parseInt(OCC_PERCENTAGE_TEXT.getText());
            int d = Integer.parseInt(DAYS_OF_SERVICE_TEXT.getText());

            double k = 0.0;

            switch(KG_OF_CLOTH_PER_PPL_MENU.getSelectionModel().getSelectedIndex()){

                case 0: k = 1.0; break;
                case 1: k = 1.2; break;
                case 2: k = 1.5; break;
                case 3: k = 2.5; break;
                case 4: k = 3.0; break;
                default: System.err.println("Error on k");
            }

            int h = Integer.parseInt(WORKING_HOURS_PER_WEEK_TEXT.getText());

            double r = 0.0;
            switch(R_VALUE_MENU.getSelectionModel().getSelectedIndex()){

                case 0: r = 1.3; break;
                case 1: r = 1.15; break;
                default: System.err.println("Error on r");
            }

            System.out.println("N = " + n);
            System.out.println("M = " + m);
            System.out.println("D = " + d);
            System.out.println("K = " + k);
            System.out.println("H = " + h);
            System.out.println("R = " + r);

            double nominator = n * m * d * k;
            double denominator = 100 * h;
            double p = (nominator / denominator) * r;

            int result = Math.round((int) p);
            AVG_H_PROD.setText("Μέση παραγωγή ανά ώρα: " + result);
        });

        grid.add(compute, 0, 7);
        grid.add(AVG_H_PROD, 1, 7);

        scene = new Scene(grid, 700,300);


        grid.setPadding(new Insets(10,10,10,10));



        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
