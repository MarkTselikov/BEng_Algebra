package CustomControlls;

import Model.Lifestyle;

import javax.swing.*;
import java.awt.*;

public class LifestyleForm extends JPanel {

    private JCheckBox cbVegetarian;
    private JCheckBox cbSmoker;
    private JCheckBox cbAlcohol;
    private JCheckBox cbStimulants;
    private JCheckBox cbRegMeals;
    private JCheckBox cbHomeFood;
    private JSpinner numCoffeeTea;
    private JSpinner numSoftDrinks;

    public LifestyleForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        cbVegetarian = new JCheckBox("Vegetarian / Non-Vegetarian: ");
        cbSmoker = new JCheckBox("Smoker: ");
        cbAlcohol = new JCheckBox("Consumes Alcohol: ");
        cbStimulants = new JCheckBox("Uses Stimulants: ");
        cbRegMeals = new JCheckBox("Has regular meals: ");
        cbHomeFood = new JCheckBox("Eats home-cooked food: ");

        JPanel coffeePan = new JPanel();
        JLabel lblCoffee = new JLabel("Coffee / Tea consumption per day:");
        numCoffeeTea = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numCoffeeTea.setPreferredSize(new Dimension(150, 20));
        coffeePan.add(lblCoffee);
        coffeePan.add(numCoffeeTea);

        JPanel softPan = new JPanel();
        JLabel lblSoft = new JLabel("Soft Drink consumption per day:");
        numSoftDrinks = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numSoftDrinks.setPreferredSize(new Dimension(150, 20));
        softPan.add(lblSoft);
        softPan.add(numSoftDrinks);

        this.add(cbVegetarian);
        this.add(cbSmoker);
        this.add(cbAlcohol);
        this.add(cbStimulants);
        this.add(cbRegMeals);
        this.add(cbHomeFood);
        this.add(coffeePan);
        this.add(softPan);
    }

    public boolean getVegetarianInput() {
        return cbVegetarian.isSelected();
    }

    public boolean getSmokerInput() {
        return cbSmoker.isSelected();
    }

    public boolean getAlcoholInput() {
        return cbAlcohol.isSelected();
    }

    public boolean getStimulantsInput() {
        return cbStimulants.isSelected();
    }

    public boolean getRegMealsInput() {
        return cbRegMeals.isSelected();
    }

    public boolean getHomeFoodInput() {
        return cbHomeFood.isSelected();
    }

    public int getNumCoffeeTeaInput() {
        return (int)numCoffeeTea.getValue();
    }

    public int getNumSoftDrinksInput() {
        return (int)numSoftDrinks.getValue();
    }

    public void fillForm(Lifestyle l) {
        cbVegetarian.setSelected(l.isVegetarian());
        cbSmoker.setSelected(l.isSmoker());
        cbAlcohol.setSelected(l.isAlcohol());
        cbStimulants.setSelected(l.isStimulants());
        cbRegMeals.setSelected(l.isRegularMeals());
        cbHomeFood.setSelected(l.isHomeFood());
        numCoffeeTea.setValue(l.getTeaCoffeeDay());
        numSoftDrinks.setValue(l.getSoftDrinksDay());
    }

    public Lifestyle getLifestyle() {
        return new Lifestyle(
                getVegetarianInput(),
                getSmokerInput(),
                getAlcoholInput(),
                getStimulantsInput(),
                getNumCoffeeTeaInput(),
                getNumSoftDrinksInput(),
                getRegMealsInput(),
                getHomeFoodInput()
        );
    }
}
