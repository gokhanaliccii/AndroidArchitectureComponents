package com.gokhanaliccii.flavorhunter.view.main.adapter;

import android.support.v7.util.DiffUtil;

import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

import static com.gokhanaliccii.flavorhunter.util.CollectionUtil.hasItem;
import static com.gokhanaliccii.flavorhunter.util.NullChecker.isNull;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueDiffUtil extends DiffUtil.Callback {

    private List<Venue> oldList;
    private List<Venue> newList;

    public VenueDiffUtil(List<Venue> oldList, List<Venue> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return hasItem(oldList) ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return hasItem(newList) ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Venue oldVenue = oldList.get(oldItemPosition);
        Venue newVenue = newList.get(newItemPosition);

        if (isNull(oldVenue) && isNull(newVenue)) {
            return true;
        }

        if (isNull(oldVenue) || isNull(newVenue)) {
            return false;
        }

        return oldVenue.getId().equals(newVenue.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Venue oldVenue = oldList.get(oldItemPosition);
        Venue newVenue = newList.get(newItemPosition);

        boolean contentsSame = true;
        contentsSame &= oldVenue.getName().equals(newVenue.getName());
        contentsSame &= oldVenue.getAddress().equals(newVenue.getAddress());

        return contentsSame;
    }
}