package com.example.api_k4;

import android.os.AsyncTask;

public class MTask extends AsyncTask<Object, Object,Object> {
    private String key;
    private OnMTaskCallBack callBack;

    public MTask(String key, OnMTaskCallBack callBack) {
        this.key = key;
        this.callBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        // thực hiện tiền xử lý dữ liệu
        callBack.initFirst(key);
    }

    @Override
    protected Object doInBackground(Object... data) {
        return callBack.exCuteTask(key,data[0],this);
    }
    public void requestUIUpdate(Object data){
        publishProgress(data);
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        callBack.updateUI(key,values[0]);
    }

    @Override
    protected void onPostExecute(Object rs) {
        callBack.completeTask(key,rs);
    }
    public interface  OnMTaskCallBack{
        default void initFirst(String key) {

        }
        Object exCuteTask(String key, Object data, MTask mTask);
        default void updateUI(String key, Object data){

        }
        default void completeTask(String key, Object object){

        }
    }
    public void start(Object object){
        execute(object);
    }
    public void startAsync(Object object){
        executeOnExecutor(THREAD_POOL_EXECUTOR,object);
    }
    public void stop(){
        cancel(true);
    }
}
