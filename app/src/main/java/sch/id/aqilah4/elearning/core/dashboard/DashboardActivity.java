package sch.id.aqilah4.elearning.core.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.adapter.PageAdapter;
import sch.id.aqilah4.elearning.core.dashboard.account.AccountFragment;
import sch.id.aqilah4.elearning.core.dashboard.home.HomeFragment;
import sch.id.aqilah4.elearning.core.dashboard.other.OtherFragment;
import sch.id.aqilah4.elearning.core.dashboard.transactional.TransactionalFragment;

public class DashboardActivity extends AppCompatActivity {
    private int[] tab_icons = new int[]{
            R.drawable.ic_home,
            R.drawable.ic_shopping,
            R.drawable.ic_account,
            R.drawable.ic_more
    };

    TextView dashboard_toolbartitle;
    Toolbar dashboard_toolbar;
    ViewPager dashboard_pager;
    TabLayout dashboard_tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        ButterKnife.bind(this);

        dashboard_pager = findViewById(R.id.dashboard_pager);
        dashboard_tablayout = findViewById(R.id.dashboard_tablayout);
        dashboard_toolbar = findViewById(R.id.dashboard_toolbar);
        dashboard_toolbartitle = findViewById(R.id.dashboard_toolbartitle);

        initComponent();
    }

    private void initComponent() {
            setSupportActionBar(dashboard_toolbar);
            createViewPager(dashboard_pager);
            dashboard_tablayout.setupWithViewPager(dashboard_pager);
            createTabIcon();
    }

    private void createTabIcon() {
        dashboard_tablayout.getTabAt(0).setIcon(tab_icons[0]);
        dashboard_tablayout.getTabAt(1).setIcon(tab_icons[1]);
        dashboard_tablayout.getTabAt(2).setIcon(tab_icons[2]);
        dashboard_tablayout.getTabAt(3).setIcon(tab_icons[3]);
    }

    private void createViewPager(ViewPager viewPager){
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), getApplicationContext());
        pageAdapter.addFragment(new HomeFragment(), getResources().getString(R.string.tab_home));
        pageAdapter.addFragment(new TransactionalFragment(), getResources().getString(R.string.tab_transaction));
        pageAdapter.addFragment(new AccountFragment(), getResources().getString(R.string.tab_account));
        pageAdapter.addFragment(new OtherFragment(), getResources().getString(R.string.tab_setting));

        viewPager.setAdapter(pageAdapter);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DashboardActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}