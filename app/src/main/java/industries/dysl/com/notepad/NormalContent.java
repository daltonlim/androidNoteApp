package industries.dysl.com.notepad;

public class NormalContent extends Content {
    private String content;

    public NormalContent(String content) {
        this.content = content;
    }

    @Override
    public String getContents() {
        return content;
    }
}
