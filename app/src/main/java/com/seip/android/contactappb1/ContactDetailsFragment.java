package com.seip.android.contactappb1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seip.android.contactappb1.models.MyContact;

public class ContactDetailsFragment extends Fragment {

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MyContact contact = ContactDetailsFragmentArgs
                .fromBundle(getArguments())
                .getContact();
        final String name = ContactDetailsFragmentArgs
                .fromBundle(getArguments())
                .getName();

        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }
}