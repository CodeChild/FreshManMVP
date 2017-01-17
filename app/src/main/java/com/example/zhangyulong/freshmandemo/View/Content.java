package com.example.zhangyulong.freshmandemo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.zhangyulong.freshmandemo.News;
import com.example.zhangyulong.freshmandemo.Presenter.ContentPresenter;
import com.example.zhangyulong.freshmandemo.R;

import static com.example.zhangyulong.freshmandemo.View.BlankFragment.tmpIndex;

public class Content extends AppCompatActivity implements IShowContent {
    public static News content;
    private TextView title,newscome;
    private WebView webView;
    private ContentPresenter contentPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        contentPresenter=new ContentPresenter(tmpIndex,this);

    }

    @Override
    public void showContent(News content) {
        this.content=content;
        title=(TextView)findViewById(R.id.title);
        newscome=(TextView)findViewById(R.id.newscome);
        webView=(WebView)findViewById(R.id.content);
        title.setText(content.getData().getSubject());
        newscome.setText("来源："+content.getData().getNewscome()+"\n"+"供稿："+content.getData().getGonggao()+"  审稿："+
                content.getData().getShengao()+"  摄影:"+content.getData().getSheying());
        String html=content.getData().getContent();
        webView.setBackgroundColor(0x00000000);
        webView.loadDataWithBaseURL(null,html,"text/html", "utf-8",
                null);
    }
}
