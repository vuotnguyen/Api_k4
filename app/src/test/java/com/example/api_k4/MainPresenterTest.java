package com.example.api_k4;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


public class MainPresenterTest {

    private MockWebServer webServer;
    private MainPresenter mainPresenter;

    @Before
    public void setUp() {
        webServer = new MockWebServer();
        MainPresenter.BASE_URL = webServer.url("/").toString();
        mainPresenter = Mockito.spy(MainPresenter.class);
    }

    @Test
    public void testGetAllAlbum() {
        webServer.enqueue(new MockResponse().setBody(getText("list_album.json")));

    }

    private String getText(String s) {
        try {
            InputStream in = getClass().getResourceAsStream("/file_api/"+s);
            StringBuilder txt = new StringBuilder();
            byte[] buff = new byte[1024];
            int len = in.read(buff);
            while (len>0){
                txt.append(new String[])
            }
        }
    }

}