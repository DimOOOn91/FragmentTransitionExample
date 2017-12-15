package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FragmentB extends BaseFragment {

    private ViewPager vpSmallCards;
    private AnimalsAdapter adapter;
    private int selectedAnimalId;
    private ArrayList<Integer> animals;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_b;
    }

    @Override
    protected void parseParameters(Bundle parameters) {
        if (parameters != null && !parameters.isEmpty()) {
            selectedAnimalId = parameters.getInt(ANIMAL_ID, 0);
            animals = parameters.getIntegerArrayList(ANIMAL_LIST);
        }
    }

    @Override
    protected void initViews(View rootView) {
        vpSmallCards = rootView.findViewById(R.id.vp_small_cards);
        adapter = new AnimalsAdapter();
        adapter.setAnimals(animals == null ? new ArrayList<Integer>() : animals);
        vpSmallCards.setAdapter(adapter);
        int animalPositionById = adapter.getAnimalPositionById(selectedAnimalId);
        vpSmallCards.setCurrentItem(animalPositionById, false);
    }


}
