package com.rsschool.android2021;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements OpenFragments {
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            openFirstFragment(0);
        }
    }
    @Override
     public void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment, "FirstFragment");
        transaction.commit();
    }
    @Override
    public void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment,"SecondFragment");
        transaction.addToBackStack("Second");
        transaction.commit();
    }

    @Override
    public void deleteFragmentStack() {
        fm.popBackStack();
    }

    /**
     * Переопределение нажатие системной кнопки "<"
     */
    @Override
    public void onBackPressed() {
          if(fm.getBackStackEntryCount() == 1) {
               Fragment fragment = fm.findFragmentByTag("SecondFragment");
              if (fragment != null) {
                  View view = fragment.getView();
                  assert view != null;
                  TextView result = view.findViewById(R.id.result);
                  String previousNumber = result.getText().toString();
                  deleteFragmentStack();
                  openFirstFragment(Integer.parseInt(previousNumber));
              }
          }else {
              super.onBackPressed();
          }
    }
}
