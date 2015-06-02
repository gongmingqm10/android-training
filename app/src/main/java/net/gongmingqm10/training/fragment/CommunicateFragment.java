package net.gongmingqm10.training.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.gongmingqm10.training.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CommunicateFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    @InjectView(R.id.send_message_edit)
    protected EditText communicateEdit;

    @InjectView(R.id.receive_message)
    protected TextView receiveMessage;
    private String mParam1;
    private String mParam2;

    public static CommunicateFragment newInstance(String param1, String param2) {
        CommunicateFragment fragment = new CommunicateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_communicate, container, false);
        ButterKnife.inject(this, view);
        receiveMessage.setText("Hello " + mParam1 + " " + mParam2);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.send_message_btn)
    protected void sendMessageToHost(View view) {
        String message = communicateEdit.getText().toString();
        mListener.onFragmentInteraction(message);
    }


    public void receiveMessage(String message) {
        receiveMessage.setText(message);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String message);
    }

}
