package com.gokhanaliccii.flavorhunter.util.recyclerview;

/**
 * Created by gokhan on 28/12/17.
 */

public interface RecyclerViewItemClickListener<Model> {

    void onItemClicked(int position, Model model);
}
