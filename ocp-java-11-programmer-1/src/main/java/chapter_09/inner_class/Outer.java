package chapter_09.inner_class;

public class Outer {
    private int x = 10;

    class Inner {
        private int x = 20;

        public void greet() {
            int x = 30;
            System.out.println(x);        // 1. 30
            System.out.println(this.x);   // 2. qual 20
            System.out.println(Outer.this.x); // 3. qual 10
        }
    }
}


