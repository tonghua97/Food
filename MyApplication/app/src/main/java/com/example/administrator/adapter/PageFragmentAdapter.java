package com.example.administrator.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.List;

/**
 * Created by lijing on 2016/12/19.
 */
public class PageFragmentAdapter extends AppFragmentAdapter {
	private List<Fragment> fragmentList;

	public PageFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragmentList=fragmentList;

	}

	@Override
	public Fragment getItem(int idx) {
		// TODO Auto-generated method stub
		return fragmentList.get(idx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}

}