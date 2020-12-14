package com.example.api_k4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnActionCallBack {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        MainPresenter presenter = new MainPresenter();
        presenter.setCallBack(this);
        //presenter.getAllALbum();
        presenter.getAllbumByID("2");
        AlbumEntity entity = new AlbumEntity();
        entity.setId("5");
        entity.setName("xe");
        entity.setDesc("aasdad");
        entity.setNumber(10);
        entity.setDate("19/9/2020");
        //presenter.createAlbum(entity);

        String json = new Gson().toJson(entity);
        Toast.makeText(this,json,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void callBack(String key, Object data) {
        switch (key){
            case MainPresenter.KEY_GETALL_ALBUM:
                List<AlbumEntity> listdata = (List<AlbumEntity>) data;
                Toast.makeText(this,listdata.toString(),Toast.LENGTH_SHORT).show();
                break;
            case MainPresenter.KEY_GETALL_ALBUM_ID:
                AlbumEntity album = (AlbumEntity) data;
                Toast.makeText(this,album.toString(),Toast.LENGTH_SHORT).show();
                break;
            case MainPresenter.KEY_CREATE_ALBUM:
                Toast.makeText(this,data .toString(),Toast.LENGTH_SHORT).show();
                break;

        }
    }
}