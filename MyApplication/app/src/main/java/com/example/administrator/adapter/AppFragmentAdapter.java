package com.example.administrator.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lijing on 2016/12/19.
 */
public abstract class AppFragmentAdapter extends PagerAdapter {
        private static final String TAG = "FragmentPagerAdapter";
        private static final boolean DEBUG = false;

        private final FragmentManager mFragmentManager;
        private FragmentTransaction mCurTransaction = null;
        private Fragment mCurrentPrimaryItem = null;

        public AppFragmentAdapter(FragmentManager fm) {
            mFragmentManager = fm;
        }

        /**
         * Return the Fragment associated with a specified position.
         */
        public abstract Fragment getItem(int position);

        @Override
        public void startUpdate(ViewGroup container) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (mCurTransaction == null) {
                mCurTransaction = mFragmentManager.beginTransaction();
            }

            // Do we already have this fragment?
            String name = makeFragmentName(container.getId(), position);
            Fragment fragment = mFragmentManager.findFragmentByTag(name);
            if (fragment != null) {
                if (DEBUG) Log.v(TAG, "Attaching item #" + position + ": f=" + fragment);
                mCurTransaction.attach(fragment);
            } else {
                fragment = getItem(position);
                if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
                mCurTransaction.add(container.getId(), fragment,
                        makeFragmentName(container.getId(), position));
            }
            if (fragment != mCurrentPrimaryItem) {
                fragment.setMenuVisibility(false);
                fragment.setUserVisibleHint(false);
            }

            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mCurTransaction == null) {
                mCurTransaction = mFragmentManager.beginTransaction();
            }
            if (DEBUG) Log.v(TAG, "Detaching item #" + position + ": f=" + object
                    + " v=" + ((Fragment)object).getView());
            mCurTransaction.detach((Fragment)object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            Fragment fragment = (Fragment)object;
            if (fragment != mCurrentPrimaryItem) {
                if (mCurrentPrimaryItem != null) {
                    mCurrentPrimaryItem.setMenuVisibility(false);
                    mCurrentPrimaryItem.setUserVisibleHint(false);
                }
                if (fragment != null) {
                    fragment.setMenuVisibility(true);
                    fragment.setUserVisibleHint(true);
                }
                mCurrentPrimaryItem = fragment;
            }
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            if (mCurTransaction != null) {
                mCurTransaction.commitAllowingStateLoss();
                mCurTransaction = null;
                mFragmentManager.executePendingTransactions();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return ((Fragment)object).getView() == view;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        private  String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }
    }


