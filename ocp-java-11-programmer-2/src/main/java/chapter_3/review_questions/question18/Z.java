package chapter_3.review_questions.question18;


/**
 * Question 18
 *
 * Correct A and B
 *
 * Y is both a class and a type parameter. This means that within the class Z, when we refer to Y,
 * it uses the type parameter. All of the choices that mention class Y are incorrect because it
 * no longer means the class Y
 *
 */
public class Z<Y> {

    W w1 = new W(); //OPTION A

    W w2 = new X(); //OPTION B

    //W w3 = new Y(); //OPTION C

    //Y y1 = new W(); //OPTION D

    //Y y2 = new X(); //OPTION E

    //Y y1 = new Y();







}
