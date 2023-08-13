package rs.ac.bg.etf.pki.zoopandica.tickets;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.data.Promo;
import rs.ac.bg.etf.pki.zoopandica.data.PurchaseNotification;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentBuyTicketBinding;

public class TicketDialog extends DialogFragment {

    private FragmentBuyTicketBinding binding;
    private String title;
    private String price;
    private String username;
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuyTicketBinding.inflate(inflater, container, false);

        String data = getArguments().getString("data");
        String[] dataArr = data.split("#");
        title = dataArr[0];
        price = dataArr[1];
        username = dataArr[2];

        binding.title.setText(title);
        binding.price.setText("Cena: " + price + " RSD");

        if (!title.equals("Grupna ulaznica")) {
            binding.quantity.setEnabled(false);
        }

        String[] arraySpinner = new String[] {"5", "10", "15", "20+" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner);
        binding.spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.date.getEditText().setOnClickListener(
                view -> {
                    new DatePickerFragment().show(getChildFragmentManager(), null);});
        getChildFragmentManager().setFragmentResultListener("ticket-date", this,
                (requestKey, result) -> {
                    Date date = (Date) result.getSerializable("set-date");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.");
                    String dateForEditText = simpleDateFormat.format(date);
                    binding.date.getEditText().setText(dateForEditText);
                });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String val = binding.spinner.getSelectedItem().toString();
                switch (val) {
                    case "5":
                        price = "1000";
                        break;
                    case "10":
                        price = "2000";
                        break;
                    case "15":
                        price = "1800";
                        break;
                    case "20+":
                        price = "3000";
                        break;
                }
                binding.price.setText("Cena: " + price + " RSD");
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        
        binding.buttonDiscount.setOnClickListener(l -> {
            String promo = binding.discount.getEditText().getText().toString();
            if (promo.equals("")) {
                Toast.makeText(getContext(), "Unesite promo kod.", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Promo p : mainActivity.promos) {
                if (p.getCode().equals(promo)) {
                    Double pr =  (Integer.parseInt(price) * p.getDiscount());
                    price  = pr.toString();
                    binding.price.setText("Cena: " + price + " RSD");
                }
            }
            // get promos
        });
        binding.buttonBuy.setOnClickListener(l -> {
            String date = binding.date.getEditText().getText().toString();
            String qty = binding.spinner.getSelectedItem().toString();

            if (date.equals("")) {
                Toast.makeText(getContext(), "Polje datum je obavezno.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (title.equals("Grupna ulaznica") && qty.equals("")) {
                Toast.makeText(getContext(), "Polje količina je obavezno.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                qty = "1";
            }
            PurchaseNotification pn = new PurchaseNotification(date, "na čekanju", username, title);
            mainActivity.addNotification(pn);
            System.out.println("OK");
            dismiss();
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setLayout(1000, 1500);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }
}
