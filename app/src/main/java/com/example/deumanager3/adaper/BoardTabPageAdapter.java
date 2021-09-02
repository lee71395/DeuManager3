package com.example.deumanager3.adaper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.deumanager3.BoardAnonymousFragment;
import com.example.deumanager3.BoardClassEstimFragment;
import com.example.deumanager3.BoardLossFragment;
import com.example.deumanager3.BoardTradeFragment;


public class BoardTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public BoardTabPageAdapter( FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem( int position) {

        switch (position) {
            case 0:
                return new BoardClassEstimFragment();

            case 1:
                return new BoardTradeFragment();

            case 2:
                return new BoardAnonymousFragment();

            case 3:
                return new BoardLossFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
