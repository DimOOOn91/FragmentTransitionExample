package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentA extends BaseFragment implements CardsAdapter.AnimalClickListener {

    private ViewPager vpBigCards;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_a;
    }

    @Override
    protected void parseParameters(Bundle parameters) {

    }

    @Override
    protected void initViews(View rootView) {
        vpBigCards = rootView.findViewById(R.id.vp_big_cards);
        CardsAdapter adapter = new CardsAdapter();
        adapter.setAnimals(1,2,3,4,5,6);
        adapter.setClickListener(this);
        vpBigCards.setAdapter(adapter);

    }

    @Override
    public void onAnimalClick(ImageView animalImage, int animalId) {
        FragmentB nextFragment = new FragmentB();
        Bundle parameters = new Bundle();
        parameters.putInt(ANIMAL_ID, animalId);
        nextFragment.setArguments(parameters);
        getMainActivity().performTransition(this, nextFragment, animalImage);
    }
}
