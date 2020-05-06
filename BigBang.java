package test;
import test.MemoryRepositoryTest;

public class BigBang {

    public static void main(String[] args )
    {
        MemoryRepositoryTest test = new MemoryRepositoryTest();
        test.setUp();
        test.save();
        test.findOne();
    }
}
