package com.example.zhangyulong.freshmandemo.Model;

import com.example.zhangyulong.freshmandemo.Artical;
import com.example.zhangyulong.freshmandemo.Presenter.ListPresenter;

import java.security.spec.ECField;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyulong on 16-11-8.
 */
interface RequestSerives {
    @GET("api/v1/news/{type}/page/{pageNum}")
    Observable<Artical> getString(@Path("type") String type, @Path("pageNum") String pageNum);


}
public class ListModel implements IList {
    ListPresenter listPresenter;
    public ListModel(ListPresenter listPresenter){
        this.listPresenter=listPresenter;
    }
    @Override


    public void getListData(int type, int pageNum) {
      Retrofit retrofit = new Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .baseUrl("http://open.twtstudio.com/")
              .build();


        RequestSerives requestSerives = retrofit.create(RequestSerives.class);
       /*   Call<Artical> call = requestSerives.getString(Integer.toString(type),Integer.toString(pageNum));
        call.enqueue(new Callback<Artical>() {
            @Override
            public void onResponse(Call<Artical> call, Response<Artical> response) {

              try {
                  listPresenter.getData(response.body().getData());

              }
              catch (Exception e){
                  e.printStackTrace();
              }
            }

            @Override
            public void onFailure(Call<Artical> call, Throwable t) {

            }
        }); */
        requestSerives.getString(Integer.toString(type),Integer.toString(pageNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artical>() {
                    @Override
                    public void onNext(Artical artical) {
                        listPresenter.getData(artical.getData());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable error) {
                        // Error handling
                    }
                });
    }


}
