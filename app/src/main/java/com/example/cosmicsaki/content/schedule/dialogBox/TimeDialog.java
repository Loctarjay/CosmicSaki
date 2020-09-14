package com.example.cosmicsaki.content.schedule.dialogBox;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cosmicsaki.R;

public class TimeDialog extends AppCompatDialogFragment {

    private EditText editStart, editStop;
    private timeDialogListener listener;
    private int viewID;

    public TimeDialog(int viewID) {
        this.viewID = viewID;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sample_time_dialog, null);

        editStart = view.findViewById(R.id.startTime);
        editStop = view.findViewById(R.id.stopTime);

        builder.setView(view)
                .setTitle("Edit Stream Time")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String start = editStart.getText().toString();
                        String stop = editStop.getText().toString();
                        listener.applyText(start, stop, viewID);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (timeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement timeDialogListener");
        }
    }

    public interface timeDialogListener{
        void applyText(String startTime, String stopTime, int viewID);
    }
}
