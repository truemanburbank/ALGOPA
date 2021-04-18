package com.example.algopa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private QuestionsFragment questionsFragment;
    private ChattingFragment chattingFragment;
    private MentoFragment mentoFragment;
    private PointshopFragment pointshopFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        questionsFragment = new QuestionsFragment();
        chattingFragment = new ChattingFragment();
        mentoFragment = new MentoFragment();
        pointshopFragment = new PointshopFragment();
        settingFragment = new SettingFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(questionsFragment, "Questions");
        viewPagerAdapter.addFragment(chattingFragment, "Chatting");
        viewPagerAdapter.addFragment(mentoFragment, "Mentoring");
        viewPagerAdapter.addFragment(pointshopFragment, "PointShop");
        viewPagerAdapter.addFragment(settingFragment, "Setting");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_contact_support_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_question_answer_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_people_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_shopping_bag_24);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_baseline_settings_24);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}