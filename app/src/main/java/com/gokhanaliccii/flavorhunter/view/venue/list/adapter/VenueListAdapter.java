package com.gokhanaliccii.flavorhunter.view.venue.list.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.flavorhunter.databinding.ItemVenueBinding;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;
import com.gokhanaliccii.flavorhunter.util.recyclerview.RecyclerViewItemClickListener;

import java.util.List;

import static com.gokhanaliccii.flavorhunter.util.CollectionUtil.isEmpty;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.ViewHolder> {

    private List<Venue> mVenus;
    private RecyclerViewItemClickListener<Venue> mItemClickListener;

    public VenueListAdapter(List<Venue> venus) {
        mVenus = venus;
    }

    public VenueListAdapter(List<Venue> venus, RecyclerViewItemClickListener<Venue> clickListener) {
        this.mVenus = venus;
        this.mItemClickListener = clickListener;
    }

    public void updateVenues(List<Venue> venus) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new VenueDiffUtil(mVenus, venus));
        mVenus = venus;

        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemVenueBinding venueBinding = ItemVenueBinding.inflate(inflater, parent, false);

        return new ViewHolder(venueBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Venue venue = mVenus.get(position);
        holder.bind(venue);

        if (mItemClickListener != null) {
            holder.rootView().setOnClickListener(v -> mItemClickListener.onItemClicked(position, venue));
        }
    }

    @Override
    public int getItemCount() {
        return isEmpty(mVenus) ? 0 : mVenus.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ItemVenueBinding mLayoutAdapter;

        public ViewHolder(ItemVenueBinding itemVenueBinding) {
            super(itemVenueBinding.getRoot());

            mLayoutAdapter = itemVenueBinding;
        }

        public void bind(Venue venue) {
            mLayoutAdapter.setVenue(venue);
            mLayoutAdapter.executePendingBindings();
        }

        public View rootView() {
            return mLayoutAdapter.getRoot();
        }

    }
}