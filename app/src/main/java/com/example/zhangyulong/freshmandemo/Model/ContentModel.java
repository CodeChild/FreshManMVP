package com.example.zhangyulong.freshmandemo.Model;

import com.example.zhangyulong.freshmandemo.Artical;
import com.example.zhangyulong.freshmandemo.News;
import com.example.zhangyulong.freshmandemo.Presenter.ContentPresenter;
import com.example.zhangyulong.freshmandemo.Presenter.IGetContentData;

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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyulong on 16-11-10.
 */
interface Request {
    @GET("api/v1/news/{index}")
    Observable<News> getResult(@Path("index") int index);


}
public class ContentModel implements IContent {
    private ContentPresenter contentPresenter;
    public  ContentModel(ContentPresenter contentPresenter){
        this.contentPresenter=contentPresenter;
    }
    @Override
    public void getContentData(final int index) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://open.twtstudio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        Request requestSerives = retrofit.create(Request.class);
        Observable<News> call = requestSerives.getResult(index);
      /*  call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                try {
                    contentPresenter.getContent(response.body());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });*/
        requestSerives.getResult(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onNext(News news) {
                        contentPresenter.getContent(news);
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
