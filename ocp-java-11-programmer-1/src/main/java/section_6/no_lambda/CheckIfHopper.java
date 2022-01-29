package section_6.no_lambda;

import section_6.model.Animal;

public class CheckIfHopper implements CheckTrait{
    @Override
    public boolean test(Animal a) {
        return a.canHop();
    }
}
