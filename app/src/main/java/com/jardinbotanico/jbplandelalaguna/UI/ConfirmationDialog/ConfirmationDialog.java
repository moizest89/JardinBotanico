package com.jardinbotanico.jbplandelalaguna.UI.ConfirmationDialog;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ConfirmationDialog extends DialogFragment {

    private ConfirmationDialogListener listener;
    private static String PARAMS_MESSAGE = "message";
    private static String PARAMS_ORIGIN = "origin";

    public static ConfirmationDialog newInstance(String message, Integer origin) {
        ConfirmationDialog f = new ConfirmationDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(PARAMS_MESSAGE, message);
        args.putInt(PARAMS_ORIGIN, origin);
        f.setArguments(args);

        return f;
    }

    public static ConfirmationDialog newInstance(String message) {
        ConfirmationDialog f = new ConfirmationDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(PARAMS_MESSAGE, message);
        f.setArguments(args);

        return f;
    }

    private String mMessage = "";
    private Integer mOrigin = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.mMessage = getArguments().getString(PARAMS_MESSAGE);
            this.mOrigin = getArguments().getInt(PARAMS_ORIGIN);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(this.mMessage).setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(ConfirmationDialog.this, mOrigin);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(ConfirmationDialog.this, mOrigin);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ConfirmationDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListener");
        }
    }



    public interface ConfirmationDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Integer origin);
        void onDialogNegativeClick(DialogFragment dialog, Integer origin);
    }
}

