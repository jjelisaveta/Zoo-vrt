package rs.ac.bg.etf.pki.zoopandica.tickets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int initYear = calendar.get(Calendar.YEAR);
        int initMonth = calendar.get(Calendar.MONTH);
        int initDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                (view, setYear, setMonth, setDay) -> {
                    Bundle result = new Bundle();
                    result.putSerializable(
                            "set-date",
                            getDate(setYear, setMonth, setDay));
                    getParentFragmentManager().setFragmentResult(
                            "ticket-date",
                            result);
                },
                initYear, initMonth, initDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 5000);
        return datePickerDialog;
    }

    public Serializable getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
