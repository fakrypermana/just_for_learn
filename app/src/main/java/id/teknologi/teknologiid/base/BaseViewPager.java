package id.teknologi.teknologiid.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by galihgasur on 05/10/17.
 */

public class BaseViewPager<AFragment extends BaseFragment> extends FragmentStatePagerAdapter {

    private AFragment[] fragmentList;

    public BaseViewPager(FragmentManager fm, AFragment[] fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public AFragment getItem(int position) {
        return fragmentList[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList[position].getTitle();
    }

    @Override
    public int getCount() {
        return fragmentList.length;
    }
}
