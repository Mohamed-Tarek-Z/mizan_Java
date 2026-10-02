package utils;

public interface ErrorListener {

    void onError(Exception e);

    void onWarning(String msg, String title);
    
    int onQuest(Object quest, String title);
}
