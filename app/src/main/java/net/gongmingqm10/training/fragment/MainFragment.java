package net.gongmingqm10.training.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.activity.ActionTabActivity;
import net.gongmingqm10.training.activity.CapturePhotoActivity;
import net.gongmingqm10.training.activity.FragmentCommunicateActivity;
import net.gongmingqm10.training.activity.FragmentDynamicActivity;
import net.gongmingqm10.training.activity.InteractAppActivity;
import net.gongmingqm10.training.activity.MaterialCardActivity;
import net.gongmingqm10.training.activity.MaterialDialogActivity;
import net.gongmingqm10.training.activity.MaterialRecyclerActivity;
import net.gongmingqm10.training.activity.MaterialTabActivity;
import net.gongmingqm10.training.activity.ShareDataActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class MainFragment extends Fragment {

    @InjectView(R.id.list)
    protected ListView featureList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListView();
    }

    private void initListView() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.material_design_header, featureList, false);
        featureList.addHeaderView(headerView);

        String[] texts = new String[]{
                "ActionBar Tab Fragment",
                "Dynamic Fragment",
                "Communicate with Fragments",
                "Interact with Activity",
                "Share Simple Data",
                "Capturing Photos",
                "Material RecyclerView",
                "Material CardView",
                "Material TabLayout",
                "Material Dialog"
        };
        featureList.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, texts));
    }

    @OnItemClick(R.id.list)
    protected void openPage(int position) {
        switch (position - 1) {
            case 0:
                startActivity(new Intent(getActivity(), ActionTabActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), FragmentDynamicActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), FragmentCommunicateActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), InteractAppActivity.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), ShareDataActivity.class));
                break;
            case 5:
                startActivity(new Intent(getActivity(), CapturePhotoActivity.class));
                break;
            case 6:
                startActivity(new Intent(getActivity(), MaterialRecyclerActivity.class));
                break;
            case 7:
                startActivity(new Intent(getActivity(), MaterialCardActivity.class));
                break;
            case 8:
                startActivity(new Intent(getActivity(), MaterialTabActivity.class));
                break;
            case 9:
                startActivity(new Intent(getActivity(), MaterialDialogActivity.class));
                break;
            default:
                break;
        }
    }


}
