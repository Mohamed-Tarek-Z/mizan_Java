package utils;

public interface ErrorListener {

    void onError(Exception e);

    void onWarning(String msg, String title);

    int onQuest(String msg, String title);

    Object onQuest(Object[] quest, String msg, String title);
}
