package chapter_1.interface_members.inheritance_duplicate;

public interface Walk {
    public default int getSpeed(){
        return 5;
    }
}
