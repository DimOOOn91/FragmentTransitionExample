package com.example.dmitriylesovoy.fragmenttransitionexample;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

    public static final String ANIMAL_ID = "animal_id";
    public static final String ANIMAL_LIST = "animal_list";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getRootLayout(), container, false);

        parseParameters(getArguments());


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViews(getView());
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    protected abstract void parseParameters(Bundle parameters);

    protected abstract @LayoutRes int getRootLayout();

    protected abstract void initViews(View rootView);

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
