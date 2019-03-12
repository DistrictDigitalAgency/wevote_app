package districtdigital.agency.votingPackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;

import districtdigital.agency.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private TapBarMenu tapBarMenu;
    private ArrayList<String> mQuestions;
    private ArrayAdapter<String> questionsAdapter;
    private Button  bCulture,bHighTech,bPolitics,bEconomics,bSocial,bSport,bArt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tapBarMenu = view.findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });


        //View settin'
        bCulture = view.findViewById(R.id.goToCulture);
        bHighTech = view.findViewById(R.id.goToHighTech);
        bPolitics = view.findViewById(R.id.goToPolitics);
        bEconomics = view.findViewById(R.id.goToEconomics);
        bSocial = view.findViewById(R.id.goToSocial);
        bSport = view.findViewById(R.id.goToSport);
        bArt = view.findViewById(R.id.goToArt);

        SwipeFlingAdapterView votingArea = (SwipeFlingAdapterView) view.findViewById(R.id.votingArea);

        grabQuestions("culture");

        bArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabQuestions("art");
            }
        });


        bCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabQuestions("culture");
            }
        });









        //Question Arrays
        votingArea.setAdapter(questionsAdapter);
        votingArea.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                mQuestions.remove(0);
                questionsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getActivity(), "You voted: For!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "You voted: Against!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                mQuestions.add("There's no more questions, come back later ! ".concat(String.valueOf(itemsInAdapter)));
                questionsAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                itemsInAdapter++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        votingArea.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(getActivity(), "You voted: Abstention!");
            }
        });


        return view;
    }



    private void grabQuestions(String field) {

//        mQuestions.clear();
        switch (field)
        {
            case "culture":
                mQuestions = new ArrayList<String>();
                mQuestions.add("culture 1");
                mQuestions.add("culture 2");
                mQuestions.add("culture 3");
                mQuestions.add("culture 4");
                break;

            case "art":
                mQuestions = new ArrayList<String>();
                mQuestions.add("art 1");
                mQuestions.add("art 2");
                mQuestions.add("art 3");
                mQuestions.add("art 4");
                break;

                default:
                    mQuestions = new ArrayList<String>();
                    mQuestions.add("default 1");
                    mQuestions.add("default 2");
                    mQuestions.add("default 3");
                    mQuestions.add("default 4");

        questionsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item,R.id.helloText, mQuestions );

        }


    }



    private void setViewByMenuButton(Button bLocal, String sLocal)
    {

    }



    private void makeToast(FragmentActivity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }



    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.votingArea, fragment).commit();
    }


}