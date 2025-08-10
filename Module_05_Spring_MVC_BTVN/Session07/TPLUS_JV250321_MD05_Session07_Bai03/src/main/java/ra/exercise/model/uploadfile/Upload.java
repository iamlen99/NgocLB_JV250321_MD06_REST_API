package ra.exercise.model.uploadfile;

import ra.exercise.service.storage.SimpleStorage;

public class Upload {
    private final SimpleStorage storage;

    public Upload(SimpleStorage storage) {
        this.storage = storage;
    }

    public String execute(byte[] file) throws Exception {
        return this.storage.upload(file);
    }
}
