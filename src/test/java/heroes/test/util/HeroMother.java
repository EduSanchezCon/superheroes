package heroes.test.util;

import heroes.model.Hero;
import heroes.model.Power;

import java.util.Arrays;

public class HeroMother {

    public static Hero heroWith2Powers(){
        return new Hero("Super Fulanito", "Fulanito", "a nada",
                Arrays.asList(
                        new Power("Super velocidad", "cuando tú vas, el vuelve"),
                        new Power("Ultra confusión", "pues... no sé")));
    }

    public static Hero heroWith1Power(){
        return new Hero("Ultra Menganito", "Menganito", "a todo",
                Arrays.asList(
                        new Power("Poder meh", "molar, ma non troppo")));
    }
}
