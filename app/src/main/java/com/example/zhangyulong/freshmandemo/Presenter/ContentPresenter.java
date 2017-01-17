package com.example.zhangyulong.freshmandemo.Presenter;

import com.example.zhangyulong.freshmandemo.Model.ContentModel;
import com.example.zhangyulong.freshmandemo.Model.IContent;
import com.example.zhangyulong.freshmandemo.News;
import com.example.zhangyulong.freshmandemo.View.Content;
import com.example.zhangyulong.freshmandemo.View.IShowContent;

/**
 * Created by zhangyulong on 16-11-10.
 */

public class ContentPresenter implements IGetContentData {
    private IShowContent iShowContent;
    private ContentModel getContentData;
    public ContentPresenter(int index,IShowContent iShowContent ){
        this.getContentData=new ContentModel(this) ;
        this.iShowContent=iShowContent;
        getContentData.getContentData(index);

    }
    @Override
    public void getContent(News content) {
      Content.content=content;
        iShowContent.showContent(content);
    }
}
