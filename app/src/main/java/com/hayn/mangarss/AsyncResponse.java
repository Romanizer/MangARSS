package com.hayn.mangarss;

public interface AsyncResponse {
    void processFinish(String result);
    void processFinish(String result, int index);
}
