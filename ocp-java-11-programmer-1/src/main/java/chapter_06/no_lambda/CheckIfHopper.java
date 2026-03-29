package chapter_06.no_lambda;

import chapter_06.model.Animal;

public class CheckIfHopper implements CheckTrait{
    @Override
    public boolean test(Animal a) {
        return a.canHop();
    }
}
