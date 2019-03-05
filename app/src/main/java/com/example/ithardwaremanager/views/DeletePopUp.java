package com.example.ithardwaremanager.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.ithardwaremanager.R;

public class DeletePopUp extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_delete_screen)
                .setPositiveButton(R.string.delete, (dialog, id) -> {
                    Log.i("id", "" + id);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                    Log.i("id", "" + id);
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
