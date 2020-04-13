package com.example.android.participantsClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProjectTimelineAdapter extends FragmentStateAdapter {

    public ProjectTimelineAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ProjectTimeLineFragment();

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
