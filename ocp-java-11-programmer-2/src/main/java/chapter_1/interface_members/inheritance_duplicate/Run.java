package chapter_1.interface_members.inheritance_duplicate;

public interface Run {
    public default int getSpeed(){
        return 10;
    }
}
