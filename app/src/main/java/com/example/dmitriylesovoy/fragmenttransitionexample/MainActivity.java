package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionListenerAdapter;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        showScreen(new FragmentA());
    }

    private void showScreen(BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        attachScreen(transaction, fragment);
        transaction.commitAllowingStateLoss();
    }

    public void performTransition(Fragment previousFragment, Fragment nextFragment, View sharedView) {
        if (isDestroyed()) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition enterTransition = new Slide().setDuration(2500);
            Transition noTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition);
            Transition sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
            sharedElementEnterTransition.setDuration(2500);

            previousFragment.setExitTransition(noTransition);

            if (sharedView != null) {
                transaction.addSharedElement(sharedView, sharedView.getTransitionName());
            }




            nextFragment.setEnterTransition(noTransition);
            nextFragment.setSharedElementEnterTransition(sharedElementEnterTransition);
            nextFragment.setSharedElementReturnTransition(noTransition);
        }

        transaction.detach(previousFragment);
        attachScreen(transaction, nextFragment);
        transaction.addToBackStack(null);
//        postponeEnterTransition();
        transaction.commitAllowingStateLoss();
    }


    private Fragment attachScreen(FragmentTransaction transaction, Fragment nextFragment) {
        Fragment screen = getSupportFragmentManager().findFragmentById(nextFragment.getId());
        if (screen != null) {
            transaction.attach(screen);
        } else {
            screen = nextFragment;
            transaction.add(R.id.fragment_container, screen);
        }

        return screen;
    }



}
