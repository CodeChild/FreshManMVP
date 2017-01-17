package com.example.zhangyulong.freshmandemo.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyulong.freshmandemo.R;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhangyulong.freshmandemo.Artical;
import com.example.zhangyulong.freshmandemo.EndLessOnScrollListener;
import com.example.zhangyulong.freshmandemo.HomeAdapter;
import com.example.zhangyulong.freshmandemo.Presenter.ListPresenter;
import com.example.zhangyulong.freshmandemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements IShowItem{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private HomeAdapter homeAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ListPresenter listPresenter;
    private static int type = 1;
    private static int pageNum = 1;
    public static int tmpIndex;
    private static int tmpPage=2;
    // TODO: Rename and change types of parameters
    private static final String ARG_SECTION_NUMBER = "section_number";
   // private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(int sectionNumber) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.id_recyclerview);
        mRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.layout_swipe_refresh);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeAdapter = new HomeAdapter(getActivity());
        listPresenter=new ListPresenter(this);
        listPresenter.showList(type,pageNum);
        listPresenter.setHomeAdapter(homeAdapter);
        mRecyclerView.setAdapter(homeAdapter);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(

        ) {
            @Override
            public void onRefresh() {
                int tmp = pageNum;
                pageNum = 1;
                listPresenter.showList(type,pageNum);
                pageNum = tmp;
                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                homeAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        });
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            public void onLoadMore(int currentPage) {
                pageNum = tmpPage;
                listPresenter.showList(type,pageNum);
                tmpPage++;
                homeAdapter.notifyDataSetChanged();


            }
        });
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
public void showItem(int status) {

}
}
