package hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class list {
Stage p;
    list(Stage p)
    {
        this.p=p;
    }
    public void get(){
        ListView<String> list = new ListView<String>();
ObservableList<String> items =FXCollections.observableArrayList (
    "Single", "Double", "Suite", "Family App");
    list.setItems(items);
    Scene s = new Scene(list);
        p.setScene(s);
        p.show();
    }
    
}
