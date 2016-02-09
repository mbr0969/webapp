package loc.linux.webapp;

import loc.linux.webapp.model.Resume;

/**
 * Created by papa on 24.01.2016.
 */
public class WebAppExeption extends RuntimeException{
    private Resume resume = null;
    private String uuid = null;

    public WebAppExeption(String message) {
        super(message);
    }

    public WebAppExeption(String message, Throwable e) {
        super(message, e);
    }

    public WebAppExeption(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebAppExeption(String message, Resume resume, Throwable e) {
        super(message, e);
        this.resume = resume;
    }

    public WebAppExeption(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }

}
