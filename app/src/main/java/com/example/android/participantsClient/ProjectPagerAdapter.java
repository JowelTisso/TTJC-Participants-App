package com.example.android.participantsClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class ProjectPagerAdapter extends FragmentStateAdapter {


    public ProjectPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return march.newInstance("march","Instance 1");
            case 1:
                return april.newInstance("april","Instance 2");
            case 2:
                return may.newInstance("may","Instance 3");
            case 3:
                return june.newInstance("june","Instance 4");
            default:
                return march.newInstance("march","Default");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
