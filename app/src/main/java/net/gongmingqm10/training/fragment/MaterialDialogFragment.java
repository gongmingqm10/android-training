package net.gongmingqm10.training.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class MaterialDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("Network is disconnected")
                .setPositiveButton("OK", null)
                .setNegativeButton("CANCEL", null)
                .create();

    }
}
