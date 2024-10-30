package chapter_8.assessment.question_03;

class Bird{
    protected transient String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Bird() {
        this.name = "Matt";
    }
}
