package Model;

public class Lifestyle {
    private boolean vegetarian;
    private boolean smoker;
    private boolean alcohol;
    private boolean stimulants;
    private int teaCoffeeDay;
    private int softDrinksDay;
    private boolean regularMeals;
    private boolean homeFood;

    public Lifestyle(boolean vegetarian, boolean smoker, boolean alcohol, boolean stimulants, int teaCoffeeDay, int softDrinksDay, boolean regularMeals, boolean homeFood) {
        this.vegetarian = vegetarian;
        this.smoker = smoker;
        this.alcohol = alcohol;
        this.stimulants = stimulants;
        this.teaCoffeeDay = teaCoffeeDay;
        this.softDrinksDay = softDrinksDay;
        this.regularMeals = regularMeals;
        this.homeFood = homeFood;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public boolean isStimulants() {
        return stimulants;
    }

    public int getTeaCoffeeDay() {
        return teaCoffeeDay;
    }

    public int getSoftDrinksDay() {
        return softDrinksDay;
    }

    public boolean isRegularMeals() {
        return regularMeals;
    }

    public boolean isHomeFood() {
        return homeFood;
    }

    @Override
    public String toString() {
        return String.format("Lifestyle: \n\t" +
                "Vegetarian: %s \n\t" +
                "Smoker: %s \n\t" +
                "Consumes Alcohol: %s \n\t" +
                "Uses Stimulants: %s \n\t" +
                "Cups of Tea/Coffee per Day: %d \n\t" +
                "Amount of Soft Drinks per Day: %d \n\t" +
                "Has Regular Meals: %s \n\t" +
                "Eats Home Food: %s \n\t",
                vegetarian ? "yes" : "no", smoker ? "yes" : "no", alcohol ? "yes" : "no", stimulants ? "yes" : "no",
                teaCoffeeDay, softDrinksDay, regularMeals ? "yes" : "no", homeFood ? "yes" : "no");
    }
}
