package districtdigital.agency.votingPackage;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import districtdigital.agency.R;
import districtdigital.agency.menuFragments.aboutFragment;
import districtdigital.agency.menuFragments.helpFragment;
import districtdigital.agency.menuFragments.privacyFragment;
import districtdigital.agency.menuFragments.settingsFragment;
import districtdigital.agency.menuFragments.walletFragment;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;


public class mainVotingActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private DuoMenuAdapter mMenuAdapter;
    //private ViewHolder mViewHolder;
    private DuoDrawerLayout mDuoDrawerLayout;
    private DuoMenuView mDuoMenuView;
    private Toolbar mToolbar;
    private TextView weCoinAmount;

    private ArrayList<String> mTitles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voting);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        weCoinAmount = (TextView) findViewById(R.id.duo_view_header_text_sub_title);

        // Handle toolbar actions
        setSupportActionBar(mToolbar);
        // Handle menu actions
       mMenuAdapter = new DuoMenuAdapter(mTitles);
       mDuoMenuView.setOnMenuClickListener(mainVotingActivity.this);
       mDuoMenuView.setAdapter(mMenuAdapter);
        // Handle drawer actions
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mDuoDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();
         //Show main fragment in container
        goToFragment(new MainFragment(), false);
        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));



        //WeCoin Amount Handling
        weCoinAmount.setText("5.0");



    }





    @Override
    public void onFooterClicked() {
        Toast.makeText(this, "Logout clicked! function will be handling later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderClicked() {
        Toast.makeText(this, "Profile clicked! function will be handling later", Toast.LENGTH_SHORT).show();
    }



    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.replace(R.id.container, fragment).commit();
    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position)+"-"+position);

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
        switch (position) {
            case 0:
                goToFragment(new MainFragment(), false);
                break;
            case 1:
                goToFragment(new walletFragment(), false);
                break;
            case 2:
                goToFragment(new settingsFragment(), false);
                break;
            case 3:
                goToFragment(new helpFragment(), false);
                break;
            case 4:
                goToFragment(new privacyFragment(), false);
                break;
            case 5:
                goToFragment(new aboutFragment(), false);
                break;
            default:
                goToFragment(new MainFragment(), false);
                break;
        }

        // Close the drawer
        mDuoDrawerLayout.closeDrawer();
    }

}