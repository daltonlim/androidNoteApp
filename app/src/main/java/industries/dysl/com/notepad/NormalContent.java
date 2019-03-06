package industries.dysl.com.notepad;

public class NormalContent extends Sting {
    private String content;

    public NormalContent(String content) {
        this.content = content;
    }

    @Override
    public String getContents() {
        return content;
    }
}
