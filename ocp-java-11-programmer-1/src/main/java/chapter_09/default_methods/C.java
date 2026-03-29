package chapter_09.default_methods;


/**
 * 5. Default Method — Conflito de herança
 */
class C implements A, B {

    @Override
    public void hello() {
        A.super.hello();
    }
}

