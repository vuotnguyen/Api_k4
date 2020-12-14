package com.example.api_k4;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MTask.OnMTaskCallBack {
    public static String BASE_URL = "https://5f65ba5443662800168e6ec6.mockapi.io/";
    public static final String KEY_GETALL_ALBUM = "KEY_GETALL_ALBUM";
    public static final String KEY_GETALL_ALBUM_ID = "KEY_GETALL_ALBUM_ID";
    public static final String KEY_CREATE_ALBUM = "KEY_CREATE_ALBUM";
    private OnActionCallBack callBack;

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }

    public Retrofit getWS(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                client(new OkHttpClient().newBuilder().callTimeout(30, TimeUnit.SECONDS).build())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    public void getAllALbum(){
        MTask mTask = new MTask(KEY_GETALL_ALBUM,this);
        mTask.start(null);
    }
    public void getAllbumByID(String id){
        MTask mTask = new MTask(KEY_GETALL_ALBUM_ID,this);
        mTask.start(id);
    }

    @Override
    public Object exCuteTask(String key, Object data, MTask mTask) {
        switch (key){
            case KEY_GETALL_ALBUM:
                return dogetALlbum();

            case KEY_GETALL_ALBUM_ID:
                return dogetALbumid((String) data);
            case KEY_CREATE_ALBUM:
                return doCreateAlbum((AlbumEntity) data);
            default:break;
        }
        return null;
    }

    private Object doCreateAlbum(AlbumEntity data) {
        IAlbumAPI api = getWS().create(IAlbumAPI.class);
        try {
            Response<Object> res = api.createAlbum(data).execute();
            if(res.code() == 201){
                return "tao thanh cong";
            }
            else {
               return "error: "+ res.code()+" - "+ res.message();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AlbumEntity dogetALbumid(String id) {
        IAlbumAPI api = getWS().create(IAlbumAPI.class);
        try {
            Response<AlbumEntity> res = api.getAllALbumByID(id).execute();
            if(res.code() == 200){
                Log.i("TAG", "id: "+res.body());
                return res.body();

            }
            else {
                Log.i("TAG",res.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void completeTask(String key, Object object) {
            callBack.callBack(key,object);

    }

    private List<AlbumEntity> dogetALlbum() {
        IAlbumAPI api = getWS().create(IAlbumAPI.class);
        try {
            Response<List<AlbumEntity>> res = api.getAllAlbum().execute();
            if(res.code() == 200){
                return res.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createAlbum(AlbumEntity albumEntity) {
        MTask mTask = new MTask(KEY_CREATE_ALBUM, this);
        mTask.start(albumEntity);
    }
}
