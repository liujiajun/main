package duke.model.product;

import duke.model.inventory.Ingredient;
import duke.model.commons.Item;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class IngredientItemList extends ArrayList<Item<Ingredient>> {

    public Double getIngredientCost() {
        Double ingredientCost = 0.0;
        for(int i = 0; i < this.size(); i++) {
            ingredientCost += this.get(i).getTotalPrice();
        }
        return ingredientCost;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.size(); i++) {
            Item<Ingredient> ingredientItem = this.get(i);
            s += "[" + ingredientItem.getItem().getName() + "," + ingredientItem.getQuantity() + "]" + "\n";
        }
        return s;
    }
}