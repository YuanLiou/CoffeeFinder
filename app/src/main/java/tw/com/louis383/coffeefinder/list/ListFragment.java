package tw.com.louis383.coffeefinder.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tw.com.louis383.coffeefinder.BaseFragment;
import tw.com.louis383.coffeefinder.R;
import tw.com.louis383.coffeefinder.model.domain.CoffeeShop;
import tw.com.louis383.coffeefinder.utils.RecyclerViewDividerHelper;
import tw.com.louis383.coffeefinder.view.CoffeeListAdapter;

/**
 * Created by louis383 on 2017/2/21.
 */

public class ListFragment extends BaseFragment implements ListPresenter.ViewHandler {

    private ListPresenter presenter;
    private RecyclerView recyclerView;
    private CoffeeListAdapter adapter;

    public ListFragment() {}

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CoffeeListAdapter(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        RecyclerViewDividerHelper dividerHelper = new RecyclerViewDividerHelper(getActivity(), RecyclerViewDividerHelper.VERTICAL_LIST, false, false);
        recyclerView.addItemDecoration(dividerHelper);
        recyclerView.setAdapter(adapter);

        presenter = new ListPresenter();
        presenter.attachView(this);
    }

    @Override
    public void prepareCoffeeShops(List<CoffeeShop> coffeeShops) {
        presenter.prepareToShowCoffeeShops(coffeeShops);
    }

    @Override
    public void showNoCoffeeShopMessage() {
        String message = getResources().getString(R.string.dialog_no_coffeeshop_message);
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void setItems(List<CoffeeShop> items) {
        adapter.setItems(items);
    }
}
