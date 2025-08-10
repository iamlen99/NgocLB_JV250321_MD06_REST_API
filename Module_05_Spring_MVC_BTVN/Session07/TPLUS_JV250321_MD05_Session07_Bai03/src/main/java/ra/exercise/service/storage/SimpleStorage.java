package ra.exercise.service.storage;


import java.io.IOException;

public interface SimpleStorage {
    String upload(byte[] file) throws Exception;
}
