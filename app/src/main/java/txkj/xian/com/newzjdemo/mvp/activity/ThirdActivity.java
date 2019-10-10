package txkj.xian.com.newzjdemo.mvp.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import gpf.xian.com.commonlibrary.base.BaseActivity;
import txkj.xian.com.newzjdemo.R;
import txkj.xian.com.newzjdemo.mvp.adapter.FragmentTabAdapter;
import txkj.xian.com.newzjdemo.mvp.fragment.Afragment;
import txkj.xian.com.newzjdemo.mvp.fragment.Bfragment;
import txkj.xian.com.newzjdemo.mvp.fragment.Cfragment;
import txkj.xian.com.newzjdemo.mvp.fragment.Dfragment;
import txkj.xian.com.newzjdemo.mvp.fragment.Efragment;

public class ThirdActivity extends BaseActivity {

    @BindView(R.id.fl_layout)
    FrameLayout flLayout;
    @BindView(R.id.iv_tab_one)
    ImageView ivTabOne;
    @BindView(R.id.tv_tab_one)
    TextView tvTabOne;
    @BindView(R.id.iv_tab_two)
    ImageView ivTabTwo;
    @BindView(R.id.tv_tab_two)
    TextView tvTabTwo;
    @BindView(R.id.iv_tab_three)
    ImageView ivTabThree;
    @BindView(R.id.tv_tab_three)
    TextView tvTabThree;
    @BindView(R.id.iv_tab_four)
    ImageView ivTabFour;
    @BindView(R.id.tv_tab_four)
    TextView tvTabFour;
    @BindView(R.id.iv_tab_five)
    ImageView ivTabFive;
    @BindView(R.id.tv_tab_five)
    TextView tvTabFive;


    private ArrayList<Fragment> fragments;
    private FragmentTabAdapter tabAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_third;
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(new Afragment());
        fragments.add(new Bfragment());
        fragments.add(new Cfragment());
        fragments.add(new Dfragment());
        fragments.add(new Efragment());
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fl_layout);
        // 默认选中首页
        tvTabOne.setTextColor(getResources().getColor(R.color.colorPrimary));
        ivTabOne.setImageResource(R.drawable.comui_tab_home_selected);
    }


    @OnClick({R.id.ll_tab_one,R.id.ll_tab_two, R.id.ll_tab_three, R.id.ll_tab_four, R.id.ll_tab_five})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_one:
                tabAdapter.setCurrentFragment(0);
                break;
            case R.id.ll_tab_two:
                tabAdapter.setCurrentFragment(1);
                break;
            case R.id.ll_tab_three:
                tabAdapter.setCurrentFragment(2);
                break;
            case R.id.ll_tab_four:
                tabAdapter.setCurrentFragment(3);
                break;
            case R.id.ll_tab_five:
                tabAdapter.setCurrentFragment(4);
                break;
        }
    }

    @Override
    protected void setListener() {
        tabAdapter.setOnTabChangeListener(new FragmentTabAdapter.OnTabChangeListener() {
            @Override
            public void OnTabChanged(int index) {

                tvTabOne.setTextColor(getResources().getColor(R.color.black));
                tvTabTwo.setTextColor(getResources().getColor(R.color.black));
                tvTabThree.setTextColor(getResources().getColor(R.color.black));
                tvTabFour.setTextColor(getResources().getColor(R.color.black));
                tvTabFive.setTextColor(getResources().getColor(R.color.black));

                ivTabOne.setImageResource(R.drawable.comui_tab_home);
                ivTabTwo.setImageResource(R.drawable.comui_tab_pond);
                ivTabThree.setImageResource(R.drawable.comui_tab_post2);
                ivTabFour.setImageResource(R.drawable.comui_tab_message);
                ivTabFive.setImageResource(R.drawable.comui_tab_person);
                switch (index) {
                    case 0:
                        tvTabOne.setTextColor(getResources().getColor(R.color.colorPrimary));
                        ivTabOne.setImageResource(R.drawable.comui_tab_home_selected);
                        break;
                    case 1:
                        tvTabTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                        ivTabTwo.setImageResource(R.drawable.comui_tab_pond_selected);
                        break;
                    case 2:
                        tvTabThree.setTextColor(getResources().getColor(R.color.colorPrimary));
                        ivTabThree.setImageResource(R.drawable.comui_tab_post);
                        break;
                    case 3:
                        tvTabFour.setTextColor(getResources().getColor(R.color.colorPrimary));
                        ivTabFour.setImageResource(R.drawable.comui_tab_message_selected);
                        break;
                    case 4:
                        tvTabFive.setTextColor(getResources().getColor(R.color.colorPrimary));
                        ivTabFive.setImageResource(R.drawable.comui_tab_person_selected);
                        break;
                }
            }
        });
    }

}
