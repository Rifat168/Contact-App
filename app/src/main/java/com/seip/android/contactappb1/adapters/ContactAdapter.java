package com.seip.android.contactappb1.adapters;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.seip.android.contactappb1.ContactDetailsFragment;
import com.seip.android.contactappb1.ContactListFragmentDirections;
import com.seip.android.contactappb1.R;
import com.seip.android.contactappb1.databinding.ContactRowBinding;
import com.seip.android.contactappb1.models.MyContact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyContactViewHolder> {
    private List<MyContact> contacts;

    public ContactAdapter(List<MyContact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ContactRowBinding binding = ContactRowBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false);

        return new MyContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactViewHolder holder, int position) {
        final MyContact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyContactViewHolder extends RecyclerView.ViewHolder {
        ContactRowBinding binding;

        public void bind(MyContact contact) {
            binding.rowContactName.setText(contact.getName());
        }

        public MyContactViewHolder(@NonNull ContactRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.rowMapBtn.setOnClickListener(v -> {
                final int pos = getAdapterPosition();
                Toast.makeText(v.getContext(), contacts.get(pos).getMobile(), Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q="+contacts.get(pos).getAddress()));
                Intent chooser = Intent.createChooser(intent, "Which app do you want to open?");
                try{
                    v.getContext().startActivity(chooser);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.rowCallBtn.setOnClickListener(v -> {
                final int pos = getAdapterPosition();
                //Toast.makeText(v.getContext(), contacts.get(pos).getMobile(), Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contacts.get(pos).getMobile()));
                Intent chooser = Intent.createChooser(intent, "Which app do you want to open?");
                try{
                    v.getContext().startActivity(chooser);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.rowSmsBtn.setOnClickListener(v -> {
                final int pos = getAdapterPosition();
                //Toast.makeText(v.getContext(), contacts.get(pos).getMobile(), Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+contacts.get(pos).getMobile()));
                intent.putExtra("sms_body", "hello brother");
                Intent chooser = Intent.createChooser(intent,
                        "Which app do you want to open?");

                try{
                    v.getContext().startActivity(chooser);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.rowEmailBtn.setOnClickListener(v -> {
                final int pos = getAdapterPosition();
                //Toast.makeText(v.getContext(), contacts.get(pos).getEmail(), Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{contacts.get(pos).getEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "test");
                Intent chooser = Intent.createChooser(intent,
                        "Which app do you want to open?");
                try{
                    v.getContext().startActivity(chooser);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            binding.rowInfoBtn.setOnClickListener(v -> {
                final int pos = getAdapterPosition();
                final Bundle bundle = new Bundle();
                //bundle.putSerializable("contact", contacts.get(pos));
                final ContactListFragmentDirections.DetailsAction action =
                        ContactListFragmentDirections.detailsAction(contacts.get(pos));
                action.setName(contacts.get(pos).getName());

                Navigation.findNavController(v).navigate(action);
            });
        }


    }
}
